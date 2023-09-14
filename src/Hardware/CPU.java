package Hardware;

import Processes.Process;
import Processes.PCB;
import Scheduler.*;
import utilities.Event;
import utilities.NumberGenerator;
import utilities.Event.EventType;

public class CPU {
    private static Integer[] registers;
    private static Integer instructionPointer = 0;
    private static Integer dataPointer = 0; // adresse des donnees en memoire
    private static Process p;
    private Event myEvent;
    private boolean eventSet = false;
    private int index = 0;

    public static void execute(Process p1) {
        p = p1;

        if (p.getCurrentStep() < p.getExecutionTime()) {
            p.setState(Process.ProcessState.RUNNING);
            System.out.println("\nLe processus " + p.getProcessName() + " est en cours d'execution.");

            registers = new Integer[4];

            for (int i = p.getCurrentStep(); i < p.getExecutionTime(); i++) {
                Integer[] instruction = p.getInstructions();

                switch (instruction[i]) {
                    case 0:
                        // Enregistrer le PCB, arreter l'execution du processus, met le processus dans
                        // la waiting liste
                        // liberer l'espace memoire occupe par le processus
                        p.setState(Process.ProcessState.WAITING);
                        p.setCurrentStep(p.getCurrentStep() + 1);

                        instructionPointer = p.getCurrentStep();
                        PCB processControlBlock = new PCB(registers, instructionPointer, dataPointer, p);

                        ShortTermShceduler.putProcessOnWait(processControlBlock);
                        LongTermSheduler.freeAllocatedMemorySpace(p);
                        break;
                    case 1:
                        // Division par zero. On termine le processus
                        System.out.println(
                                "\nException dans le processus " + p.getProcessName()
                                        + ". Il y a une division par zero");
                        terminate(p);

                        break;
                    default:
                        // Instruction ordinaire. Execution normale
                        instructionPointer = p.getCurrentStep() + 1;
                        p.executeStep(instructionPointer);

                        for (int j = 0; j < 4; j++) {
                            registers[j] = NumberGenerator.generateNumber(100);
                        }

                        break;
                }
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                }
                // Si le processus a ete mis en attente ou s'il y a une division
                // par zero, on sort de la boucle d'execution du processus.
                if (instruction[i] == 0 || instruction[i] == 1) {
                    break;
                }
            }

            if (p.getState() == Process.ProcessState.RUNNING) {
                terminate(p);
            }
        }

    }

    public static void terminate(Process p2) {
        p2.setCurrentStep(p2.getExecutionTime());
        p2.setState(Process.ProcessState.TERMINATED);
        System.out.println("\nLe processus " + p2.getProcessName() + " est termine.");
        LongTermSheduler.freeAllocatedMemorySpace(p2);
    }

    public void runtime(int interruptNumber) {
        switch (interruptNumber) {
            case 2:
                Keyboard.getValue();
                PCB pcb = ShortTermShceduler.retreiveProcess();

                // Verifie s'il y a un processus en attente d'une entree
                if (pcb != null) {
                    // Verifie si l'allocation de memoire a reussie
                    if (LongTermSheduler.allocateMemorySpace(pcb.getProcess())) {
                        CPU.execute(pcb.getProcess());
                    } else {
                        System.out.println(
                                "L'allocation de memoire pour le processus " + pcb.getProcessName() + " a echoue.");
                    }
                } else {
                    System.out.println("Aucun processus ne necessite une entree pour le moment.");
                }
                break;

            case 3:
                // Verifie s'il y a un processus stocker dans le CPU
                if (p != null) {
                    System.out.println("ID du processus: " + p.getProcessID());
                    System.out.println("Nom du processus:" + p.getProcessName());
                    System.out.println("Etat du processus: " + p.getState());

                } else {
                    System.out.println("Attente de la creation d'un processus.");
                }

                break;
        }
    }

    public synchronized void HandleEvent() {
        // le thread principal attend que l'interrution soit genere
        while (!eventSet) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Handle event
        if (myEvent.getEventType() == EventType.CREATION) {
            index++;
            String pName = Keyboard.processName();
            int time = Keyboard.processTime();

            Process p = new Process(index, pName, time);
            if (LongTermSheduler.allocateMemorySpace(p)) {
                execute(p);
            } else {
                System.out.println("L'allocation de memoire pour le processus " + p.getProcessName() + " a echoue.");
            }
        } else {
            runtime(myEvent.getEventNumber());
        }

        System.out.println("\n");

        // Donne le controle au thread generateur d'interruption
        eventSet = false;
        notify();
    }

    public synchronized void setCpuEvent(int number, EventType etype) {
        // Le generateur d'evenement attend que l'interruption soit recuperer
        while (eventSet) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        myEvent = new Event();
        myEvent.setEventNumber(number);
        myEvent.setEvenType(etype);
        if (etype == Event.EventType.CREATION) {
            System.out.println("EVENEMENT GENERE: Creation de processus.\n");
        } else {
            // Verifie le numero d'interruption generer pour afficher un message.
            if (number == 2) {
                System.out.println("EVENEMENT GENERE: Interruption d'entree.\n");
            } else {
                System.out.println("EVENEMENT GENERE: Interruption de sortie.\n");
            }
        }

        // Donne le control au thread principal
        eventSet = true;
        notify();
    }

}

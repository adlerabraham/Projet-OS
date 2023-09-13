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

    public static void execute(Process p1) {
        p = p1;

        if (p.getCurrentStep() < p.getExecutionTime()) {
            p.setState(Process.ProcessState.RUNNING);
            System.out.println("Le processus " + p.getProcessName() + " est en cours d'execution.");

            registers = new Integer[4];

            for (int i = p.getCurrentStep(); i < p.getExecutionTime(); i++) {
                Integer[] instruction = p.getInstructions();

                // System.out.println(instruction[i]);
                switch (instruction[i]) {
                    case 0:
                        // Enregistrer le PCB, arreter l'execution du processus, met le processus dans
                        // la waiting liste
                        // liberer l'espace memoire occupe par le processus
                        p.setState(Process.ProcessState.WAITING);

                        instructionPointer = p.getCurrentStep();
                        PCB processControlBlock = new PCB(registers, instructionPointer, dataPointer, p);

                        ShortTermShceduler.putProcessOnWait(processControlBlock);
                        LongTermSheduler.freeAllocatedMemorySpace(p);
                        break;

                    default:
                        instructionPointer = p.getCurrentStep() + 1;
                        p.executeStep(instructionPointer);

                        for (int j = 0; j < 4; j++) {
                            registers[j] = NumberGenerator.generateNumber(100);
                        }

                        break;
                }
                Thread.yield(); // Yield control to other threads
                if (instruction[i] == 0) {
                    break;
                }
            }
            if (p.getState() == Process.ProcessState.RUNNING) {
                terminate(p);
            }
        }

    }

    public static void terminate(Process p2) {
        p2.setState(Process.ProcessState.TERMINATED);
        System.out.println("Le processus " + p2.getProcessName() + " est terminate.");
        LongTermSheduler.freeAllocatedMemorySpace(p2);
    }

    public void runtime(int interruptNumber) {
        switch (interruptNumber) {
            case 2:
                Keyboard.getValue(); // Make sur you have somme values in the keyboard
                PCB pcb = ShortTermShceduler.retreiveProcess();
                LongTermSheduler.addProcess(pcb);

                break;

            case 3:
                System.out.println("Le processus en cours d'execution est: " + p.getProcessName());
                break;

            case 4:
                if (p != null) {
                    terminate(p);
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

            }
        }

        // Handle event
        if (myEvent.getEventType() == EventType.CREATION) {
            System.out.println("Geting:");
            System.out.println("Type: " + myEvent.getEventType());
            System.out.println("Priority: " + myEvent.getPriority());
            System.out.println("Number: " + myEvent.getEventNumber());
        } else {
            System.out.println("Geting:");
            System.out.println("Type: " + myEvent.getEventType());
            System.out.println("Priority: " + myEvent.getPriority());
            System.out.println("Number: " + myEvent.getEventNumber());
        }

        System.out.println("\n");

        // Donne le controle au thread generateur d'interruption
        eventSet = false;
        notify();
    }

    public synchronized void setCpuEvent(int number, boolean priority, EventType etype) {
        // Le generateur d'evenement attend que l'interruption soit recuperer
        while (eventSet) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println("Setting " + number);
        myEvent = new Event();
        myEvent.setEventNumber(number);
        myEvent.setEvenType(etype);
        myEvent.setPriority(priority);

        // Donne le control au thread principal
        eventSet = true;
        notify();
    }

}

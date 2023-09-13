package Hardware;

import Processes.Process;
import Processes.PCB;
import Scheduler.*;
import utilities.NumberGenerator;

public class CPU {
    private static Integer[] registers;
    private static Integer instructionPointer = 0;
    private static Integer dataPointer = 0; // adresse des donnees en memoire
    private static Process p;

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

}

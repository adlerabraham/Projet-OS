package Hardware;

import java.util.Random;
import Processes.Process;
import Processes.PCB;

public class CPU {
    private static Integer[] registers;
    private static Integer instructionPointer = 0;
    private static Integer dataPointer = 0; // adresse des donnees en memoire

    public static void execute(Process p) {

        p.setState(Process.ProcessState.RUNNING);
        System.out.println("Process " + p.getProcessID() + " started.");

        registers = new Integer[4];

        for (int i = p.getCurrentStep(); i < p.getExecutionTime(); i++) {
            Integer[] instruction = p.getInstructions();

            // System.out.println(instruction[i]);
            switch (instruction[i]) {
                case 0:
                    p.setState(Process.ProcessState.WAITING);
                    System.out.println("Process " + p.getProcessID() + " is waiting");
                    instructionPointer = p.getCurrentStep();
                    PCB processControlBlock = new PCB(registers, instructionPointer, dataPointer, p);
                    // save PCB,stop process' execution and put the proccess in waiting list
                    break;

                default:
                    instructionPointer = p.getCurrentStep() + 1;
                    p.executeStep(instructionPointer);

                    registers[0] = generateNomber();
                    registers[1] = generateNomber();
                    registers[2] = generateNomber();
                    registers[3] = generateNomber();
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

    public static void terminate(Process p) {
        p.setState(Process.ProcessState.TERMINATED);
        System.out.println("Process " + p.getProcessID() + " terminated.");
    }

    private static int generateNomber() {
        Random random = new Random();
        return random.nextInt(100); // Adjust range as needed
    }

}

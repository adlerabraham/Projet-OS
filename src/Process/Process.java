package Process;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Process implements Runnable {
    private int processID;
    private ProcessState state;
    private int dataPointer = 0;
    private int Ipointer = 0;
    private int executionTime;
    private int priority;
    private int currentStep;
    // private ProcessManager processManager;

    public Process(int id, int priority, int executionTime) {
        this.processID = id;
        this.state = ProcessState.NEW;
        this.priority = priority;
        this.executionTime = executionTime;
        this.currentStep = 0;
    }

    public int getProcessID() {
        return processID;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public void run() {
        setState(ProcessState.RUNNING);
        System.out.println("Process " + processID + " started.");

        while (currentStep < executionTime) {
            executeStep();
            Thread.yield(); // Yield control to other threads
        }

        setState(ProcessState.TERMINATED);
        System.out.println("Process " + processID + " terminated.");
    }

    private void executeStep() {
        System.out.println("Process " + processID + " executing step " + (currentStep + 1) + "/" + executionTime);
        currentStep++;
    }

    class ProgramCounter {
        private int currentInstruction;
        private int totalInstructions;

        public ProgramCounter() {
            this.currentInstruction = 0;
            this.totalInstructions = generateTotalInstructions();
        }

        public int getCurrentInstruction() {
            return currentInstruction;
        }

        public void setCurrentInstruction(int instruction) {
            this.currentInstruction = instruction;
        }

        public int getTotalInstructions() {
            return totalInstructions;
        }

        private int generateTotalInstructions() {
            long seed = System.currentTimeMillis(); // Use current time as seed
            Random random = new Random(seed);
            return random.nextInt(100) + 1; // Adjust range as needed
        }

    }

    enum ProcessState {
        NEW, READY, RUNNING, WAITING, TERMINATED
    }
}
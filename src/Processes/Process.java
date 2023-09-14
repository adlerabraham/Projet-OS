package Processes;

import utilities.NumberGenerator;

public class Process {
    private int processID;
    private ProcessState state;
    private String processName;
    private int executionTime; // utilise aussi comme l'espace memoire requis
    private Integer[] instructions;
    private Integer[] allocatedMemory;
    private int currentStep;
    private boolean isMemoryAllocated;

    public Process(int id, String processName, int executionTime) {
        this.processID = id;
        this.processName = processName;
        this.state = ProcessState.NEW;
        this.executionTime = executionTime;
        this.instructions = new Integer[executionTime]; // a remplir avec des nombres aleatoires entre 0 et 5
        for (int i = 0; i < instructions.length; i++)
            instructions[i] = NumberGenerator.generateNumber(10);
        this.allocatedMemory = new Integer[executionTime];
        this.currentStep = 0;
    }

    public int getProcessID() {
        return processID;
    }

    public String getProcessName() {
        return processName;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int index) {
        currentStep = index;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public Integer[] getInstructions() {
        return this.instructions;
    }

    public Integer[] getAllocatedMemory() {
        return this.allocatedMemory;
    }

    public void setAllocatedMemory(int[] tableValue) {
        for (int i = 0; i < executionTime; i++) {
            this.allocatedMemory[i] = tableValue[i];
        }
    }

    public boolean isMemoryAllocated() {
        return isMemoryAllocated;
    }

    public void executeStep(int step) {
        System.out.println("Le process " + processName + " execute l'etape " + step + "/" +
                executionTime);
        currentStep++;
    }

    public enum ProcessState {
        NEW,
        READY,
        RUNNING,
        WAITING,
        TERMINATED
    }
}
package Processes;

import java.util.Random;

public class Process {
    private int processID;
    private ProcessState state;
    private int executionTime; // utilise aussi comme l'espace memoire requis
    private Integer[] instructions;
    private int currentStep;

    public Process(int id, int executionTime) {
        this.processID = id;
        this.state = ProcessState.NEW;
        this.executionTime = executionTime;
        this.instructions = new Integer[executionTime]; // a remplir avec des nombres aleatoires entre 0 et 5
        for (int i = 0; i < instructions.length; i++)
            instructions[i] = generateValue();

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

    public int getCurrentStep() {
        return currentStep;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public Integer[] getInstructions() {
        return this.instructions;
    }

    public void executeStep(int step) {
        System.out.println("Process " + processID + " executing step " + step + "/" +
                executionTime);
        currentStep++;
    }

    public enum ProcessState {
        NEW, READY, RUNNING, WAITING, TERMINATED
    }

    private int generateValue() { // genere un nombre entre 0 et 5
        Random rand = new Random();
        return (rand.nextInt(6));
    }

}
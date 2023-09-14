package Processes;

public class PCB {
    private Integer[] CPURegisters;
    private Integer programCounter;
    private Integer dataPointer;
    private Process p;

    // informations d'E/S

    public PCB(Integer[] CPURegisters, int programCounter, int dataPointer, Process p) {
        this.CPURegisters = CPURegisters;
        this.programCounter = programCounter;
        this.dataPointer = dataPointer;
        this.p = p;
    }

    public Integer[] getCPUIntegers() {
        return CPURegisters;
    }

    public void setCPURegisters(Integer[] registers, int length) {
        this.CPURegisters = new Integer[length];
        this.CPURegisters = registers;
    }

    public Integer getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(Integer programCounter) {
        this.programCounter = programCounter;
    }

    public Integer getDataPointer() {
        return dataPointer;
    }

    public void setDataPointer(Integer dataPointer) {
        this.dataPointer = dataPointer;
    }

    public Process.ProcessState getState() {
        return p.getState();
    }

    public Integer getProcessID() {
        return p.getProcessID();
    }

    public Process getProcess() {
        return p;
    }

    public String getProcessName() {
        return p.getProcessName();
    }

    public int getCurrentStep() {
        return p.getCurrentStep();
    }

    public int getExecutionTime() {
        return p.getExecutionTime();
    }

}

package Hardware;

public class Frame {
    private boolean isFrameInUse;

    public Frame(boolean value) {
        this.isFrameInUse = value;
    }

    public void setFrameState(boolean value) {
        this.isFrameInUse = value;
    }

    public boolean getFrameState() {
        return isFrameInUse;
    }
}

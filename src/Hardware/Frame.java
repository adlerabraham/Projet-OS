package Hardware;

import utilities.Couple;

public class Frame {
    private Couple debut;
    private Couple fin;
    private boolean isFrameInUse;

    // public Frame(Couple debut, Couple fin) {
    // this.debut = debut;
    // this.fin = fin;
    // }

    public Frame(boolean value) {
        this.isFrameInUse = value;
    }

    public Couple getDebut() {
        return debut;
    }

    public void setDebut(Couple debut) {
        this.debut = debut;
    }

    public Couple getFin() {
        return fin;
    }

    public void setFin(Couple fin) {
        this.fin = fin;
    }

    public void setFrameState(boolean value) {
        this.isFrameInUse = value;
    }

    public boolean getFrameState() {
        return isFrameInUse;
    }

}

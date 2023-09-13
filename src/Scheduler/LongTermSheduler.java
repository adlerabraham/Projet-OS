package Scheduler;

import java.util.LinkedList;
import java.util.Queue;

import Hardware.Ram;
import Processes.Process;
import Processes.PCB;

public class LongTermSheduler {

    private static Queue<PCB> readyQueue = new LinkedList<>();

    public LongTermSheduler() {

    }

    public static void addProcess(PCB pcb) {

        readyQueue.add(pcb);
    }

    public static PCB retreiveProcess() {

        return readyQueue.poll();
    }

    // Cette methode alloue de l'espace memoire a un processus
    public static boolean allocateMemorySpace(Process p) {
        int missedNumberOfFrame = p.getExecutionTime();
        int[] allocatedFrames = new int[p.getExecutionTime()];
        int index = 0; // index de la table allocatedFrames
        int searchedNumber = missedNumberOfFrame; // Le nombre de frame que la methode contiguousEmptyframe
                                                  // va chercher
        while (missedNumberOfFrame != 0 && searchedNumber != 0) {

            int result = Ram.contiguousEmptyFrame(searchedNumber);
            if (result == -1) {
                searchedNumber--;
            } else {

                for (int i = index; i < (index + searchedNumber); i++) {// Ajout des frames libres trouves
                    allocatedFrames[i] = result;
                    result++;
                }

                index += searchedNumber;
                missedNumberOfFrame -= searchedNumber;
                searchedNumber = missedNumberOfFrame;
            }
        }

        if (missedNumberOfFrame == 0) {
            p.setAllocatedMemory(allocatedFrames); // La methode retourne vrai si l'allocation de memoire
            return true; // a reussi sinon il retourne faux.
        } else {
            return false;
        }
    }

    // Cette methode libere l'espace memoire alloue a un processus
    public static void freeAllocatedMemorySpace(Process p) {
        for (int frame : p.getAllocatedMemory()) {
            Ram.freeFrame(frame);
        }
    }
}

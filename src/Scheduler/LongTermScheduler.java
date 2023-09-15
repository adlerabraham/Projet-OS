package Scheduler;

import java.util.LinkedList;
import java.util.Queue;

import Hardware.Ram;
import Processes.Process;
import Processes.PCB;

public class LongTermScheduler {

    private static Queue<PCB> readyQueue = new LinkedList<>();

    public LongTermScheduler() {

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
            // La methode retourne vrai si l'allocation de memoire
            // a reussi sinon il retourne faux.
            p.setAllocatedMemory(allocatedFrames);
            System.out.println("\nL'espace memoire a ete alloue au processus " + p.getProcessName() + " avec succes.");
            return true;
        } else {
            return false;
        }
    }

    // Cette methode libere l'espace memoire alloue a un processus
    public static void freeAllocatedMemorySpace(Process p) {
        for (int frame : p.getAllocatedMemory()) {
            Ram.freeFrame(frame);
        }
        System.out.println(
                "\nL'espace memoire alloue au processus " + p.getProcessName() + " a ete liberee avec succes.");
    }
}

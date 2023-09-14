package Hardware;

import java.util.Scanner;

import utilities.NumberGenerator;

public class Keyboard {
    private static String StringValue;
    public static int intValue;

    public static String getValue() {
        StringValue = String.valueOf(NumberGenerator.generateNumber(20));
        return StringValue;
    }

    public static void setValue(String v) {
        StringValue = v;
    }

    public static String processName() {
        Scanner clavier = new Scanner(System.in);
        String pName;
        System.out.println("Un processus va etre cree.");
        System.out.println("Entrer un nom pour le processus.");

        do {
            System.out.println("Il doit avoir moins de onze(11) caracteres.");
            pName = clavier.nextLine();

        } while (pName.length() > 10);

        return pName;

    }

    public static int processTime() {
        Scanner clavier = new Scanner(System.in);
        int executionTime;
        System.out.println("Entrer un temps d'execution pour le processus");
        do {
            System.out.println("Entrer un nombre inferieur a 21.");
            executionTime = clavier.nextInt();

        } while (executionTime > 20);

        return executionTime;
    }
}

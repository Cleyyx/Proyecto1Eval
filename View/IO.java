package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {
    public static int readInt(String msg) {
        Scanner teclado = new Scanner(System.in);
        boolean isValidInput = false;
        int value = 0;

        while (!isValidInput) {
            try {
                System.out.print(msg + " ");
                value = teclado.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error, Ingresa un número válido.");
                teclado.nextLine();
            }
        }
        return value;
    }

    public static String readString(String msg) {
        Scanner teclado = new Scanner(System.in);
        String value = "";
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.print(msg + " ");
                value = teclado.nextLine();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error, Ingresa una cadena válida.");
                teclado.nextLine();
            }
        }
        return value;
    }
}

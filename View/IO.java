package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IO {

    /**
     * Lee un entero desde la entrada ingresada por el usuario con manejo de errores.
     *
     * @param msg Mensaje que solicita la entrada del usuario.
     * @return Entero ingresado por el usuario.
     */
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

    /**
     * Lee una cadena desde la entrada ingresada por el usuario con manejo de errores.
     *
     * @param msg Mensaje que solicita la entrada del usuario.
     * @return Cadena ingresada por el usuario.
     */
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

package View;

import java.util.Scanner;

public class IO {
    public int readInt(String msg) {
        Scanner teclado = new Scanner(System.in);
        int value = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.print(msg);
                value = Integer.parseInt(teclado.nextLine());
                isValidInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Error, Ingresa un número válido.");
            }
        }
        return value;
    }
    public int readString(String msg) {
        Scanner teclado = new Scanner(System.in);
        int value = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.print(msg);
                value = Integer.parseInt(teclado.nextLine());
                isValidInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Error, Ingresa un número válido.");
            }
        }
        return value;
    }
}

package View;

import java.util.Scanner;

public class Menu {
    public int step1_SelectNPlayers() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Ingresa el número de jugadores: ");
        return teclado.nextInt();
    }
    public String[] step2_SelectPlayerNames(int n) {
        Scanner teclado = new Scanner(System.in);
        String[] playerNames = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Escribe el nombre del jugador " + (i + 1) + ": ");
            playerNames[i] = teclado.nextLine();
        }
        return playerNames;
    }
    public void step3_PlayGame() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Pulsa Enter para iniciar el juego...");
        teclado.nextLine();
    }
}
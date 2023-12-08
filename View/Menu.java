package View;

import java.util.Scanner;
import View.IO;

public class Menu {
    public int step1_SelectNPlayers() {
        return IO.readInt("Ingresa el número de jugadores: ");
    }
    public String[] step2_SelectPlayerNames(int n) {
        String[] playerNames = new String[n];
        for (int i = 0; i < n; i++) {
            playerNames[i] = IO.readString("Escribe el nombre del jugador " + (i + 1) + ": ");
        }
        return playerNames;
    }
    public void step3_PlayGame() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Pulsa una tecla para iniciar el juego...");
        teclado.nextLine();
    }
}
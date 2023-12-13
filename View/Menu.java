package View;

import java.util.Scanner;

public class Menu {
    public int step1_SelectNPlayers() {
        int numPlayers;
        do {
            numPlayers = IO.readInt("Ingresa el número de jugadores: ");
            if (numPlayers > 4) {
                System.out.println("El número de jugadores se limita a 4. Por favor, ingresa un valor válido.");
            }
        } while (numPlayers > 4);
        return numPlayers;
    }

    public String[] step2_SelectPlayerNames(int n) {
        String[] playerNames = new String[n];
        for (int i = 0; i < n; i++) {
            String playerName;
            while (true) {
                playerName = IO.readString("Escribe el nombre del jugador " + (i + 1) + ": ");
                if (!playerName.trim().isEmpty() && !playerName.trim().equalsIgnoreCase("IA") && isUniquePlayerName(playerNames, playerName)) {
                    break;
                }
                if (playerName.trim().isEmpty()) {
                    System.out.println("Nombre inválido. Por favor, ingresa un nombre válido.");
                } else if (playerName.trim().equalsIgnoreCase("IA")) {
                    System.out.println("El nombre no puede ser 'IA'. Por favor, ingresa otro nombre.");
                } else {
                    System.out.println("El nombre ya está en uso. Por favor, ingresa un nombre único.");
                }
            }
            playerNames[i] = playerName;
        }
        return playerNames;
    }

    public boolean isUniquePlayerName(String[] existingNames, String newName) {
        boolean isUnique = true;
        for (String name : existingNames) {
            if (newName.equalsIgnoreCase(name)) {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }
}
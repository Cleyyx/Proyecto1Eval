package Controller;

import Model.Game;
import Model.Player;
import Model.Card;

public class MainController {

    public static void displayPlayerHand(Player player) {
        System.out.println("Cartas iniciales de " + player.getName() + ": ");
        for (Card card : player.getHand()) {
            if (card != null) {
                System.out.println(card + " ");
            }
        }
        System.out.println("\nPuntos de " + player.getName() + ": " + player.getPoints());
        System.out.println("-------------------------------------");
    }

    public static void dealInitialCards(Game game, Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = game.getDeck().drawRandomCard();
            player.addCardToHand(card);
        }
        player.calculatePoints();
    }

    public static void determineWinner(Game game, Player[] players) {
        boolean allBust = true;
        for (Player player : players) {
            if (player.getPoints() <= 21) {
                allBust = false;
                break;
            }
        }
        if (allBust) {
            System.out.println("Todos los jugadores se han pasado de 21. Nadie gana esta ronda.");
            return;
        }
        Player roundWinner = null;
        int winningPoints = -1;
        for (Player player : players) {
            int playerPoints = player.getPoints();

            if (playerPoints <= 21 && playerPoints > winningPoints) {
                roundWinner = player;
                winningPoints = playerPoints;
            }
        }
        for (Player player : players) {
            if (player.getPoints() == winningPoints && player != roundWinner) {
                // Empate, ambos jugadores tienen la misma puntuación
                System.out.println("Empate en esta ronda entre " + roundWinner.getName() + " y " + player.getName() + ". Nadie gana.");
                return;
            }
        }
        if (roundWinner != null) {
            System.out.println("El ganador de la ronda es: " + roundWinner.getName());
            roundWinner.incrementRoundWins();
        } else {
            System.out.println("Empate en esta ronda. Nadie gana.");
        }
    }

    public static Player determineOverallWinner(Player[] players) {
        Player overallWinner = null;
        int maxRoundWins = 0;

        for (Player player : players) {
            if (player.getRoundWins() > maxRoundWins) {
                overallWinner = player;
                maxRoundWins = player.getRoundWins();
            }
        }

        return overallWinner;
    }
}
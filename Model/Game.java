package Model;

import View.IO;
import View.Menu;

public class Game {
    private Player[] players;
    private Deck deck;
    private int currentRound;

    public Game(Player[] players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public Game() {

    }

    public void startGame() {
        System.out.println("Bienvenido al juego de blackjack!");

        Menu menu = new Menu();

        int numPlayers = menu.step1_SelectNPlayers();
        String[] playerNames = menu.step2_SelectPlayerNames(numPlayers);

        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i]);
        }

        Deck deck = new Deck(new Card[52]);
        deck.getCard();

        Game game = new Game(players, deck);

        game.playRounds();

        game.endGame();
    }


    public void playRounds() {
        currentRound = 0;
        while (!isGameOver()) {
            currentRound++;
            playSingleRound();

            displayGameStatus();

            determineWinner();
        }
    }

    public void playSingleRound() {
        for (Player player : players) {
            playRound(player);
        }
    }

    public void playRound(Player currentPlayer) {
        System.out.println("Comienza el turno para " + currentPlayer.getName());
        currentPlayer.resetHand();

        dealInitialCards(currentPlayer);
        displayPlayerHand(currentPlayer);

        playerTurn(currentPlayer);
        currentPlayer.calculatePoints();
    }

    public void playerTurn(Player currentPlayer) {
        boolean ValidEntrance = true;

        do {
            String choice = IO.readString(currentPlayer.getName() + ", ¿quieres pedir carta (hit) o quedarte (stand)?");
            if (choice.equals("hit")) {
                Card card = deck.drawRandomCard();
                currentPlayer.addCardToHand(card);
                System.out.println(currentPlayer.getName() + " recibe la carta: ");
                System.out.println(card);
                currentPlayer.calculatePoints();
                System.out.println(currentPlayer.getName() + " tiene ahora " + currentPlayer.getPoints() + " puntos.");
                if (currentPlayer.getPoints() > 21) {
                    System.out.println(currentPlayer.getName() + " se pasa de 21. ¡Perdiste el turno!");
                    ValidEntrance = false;
                }
            } else if (choice.equals("stand")) {
                ValidEntrance = false;
            } else {
                System.out.println("Por favor, ingresa 'hit' o 'stand'.");
            }
        } while (ValidEntrance && currentPlayer.getPoints() <= 21);
    }

    public void displayPlayerHand(Player player) {
        System.out.println("Cartas iniciales de " + player.getName() + ": ");
        for (Card card : player.getHand()) {
            if (card != null) {
                System.out.println(card + " ");
            }
        }
        System.out.println("\nPuntos de " + player.getName() + ": " + player.getPoints());
        System.out.println("-------------------------------------");
    }

    public void dealInitialCards(Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawRandomCard();
            player.addCardToHand(card);
        }
        player.calculatePoints();
    }

    public void determineWinner() {
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

    public boolean isGameOver() {
        return currentRound >= 3;
    }

    public Player determineOverallWinner() {
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

    public void displayGameStatus() {
        System.out.println("Estado actual del juego:");

        for (Player player : players) {
            System.out.println("Mano de " + player.getName() + ": ");
            for (Card card : player.getHand()) {
                if (card != null) {
                    System.out.println(card + " ");
                }
            }
            System.out.println("\nPuntos de " + player.getName() + ": " + player.getPoints());
            System.out.println("-------------------------------------");
        }
    }

    public void endGame() {
        for (Player player : players) {
            System.out.println("Los puntos finales de " + player.getName() + " son: " + player.getPoints());
        }
        Player overallWinner = determineOverallWinner();
        if (overallWinner != null) {
            System.out.println("¡" + overallWinner.getName() + " es el ganador del juego!");
        } else {
            System.out.println("Es un empate. ¡Nadie gana!");
        }

        System.out.println("Gracias por jugar Blackjack!");
    }
}
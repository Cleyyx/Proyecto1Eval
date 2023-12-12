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
    public Game(){

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
            for (Player player : players) {
                playRound(player);
            }
        }
    }

    private void playRound(Player player) {
        System.out.println("Comienza la ronda para " + player.getName());
        player.resetHand();

        dealInitialCards(player);
        displayPlayerHand(player);

        playerTurn(player);
        player.calculatePoints();

        displayGameStatus();
        determineWinner();

    }

    public void playerTurn(Player player) {
        boolean entradaValida = true;

        do {
            String choice = IO.readString(player.getName() + ", ¿quieres pedir carta (hit) o quedarte (stand)?");
            if (choice.equals("hit")) {
                Card card = deck.drawRandomCard();
                player.addCardToHand(card);
                System.out.println(player.getName() + " recibe la carta: " + card);
                player.calculatePoints();
                System.out.println(player.getName() + " tiene ahora " + player.getPoints() + " puntos.");
                if (player.getPoints() > 21) {
                    System.out.println(player.getName() + " se pasa de 21. ¡Perdiste el turno!");
                    entradaValida = false;
                }
            } else if (choice.equals("stand")) {
                entradaValida = false;
            } else {
                System.out.println("Por favor, ingresa 'hit' o 'stand'.");
            }
        } while (entradaValida && player.getPoints() <= 21);
    }

    private void dealInitialCards(Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawRandomCard();
            player.addCardToHand(card);
        }
        player.calculatePoints();
    }

    public void determineWinner() {

    }


    public boolean isGameOver() {
        return currentRound >=2;
    }

    public Player determineOverallWinner() {
        return null;
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

    private void displayPlayerHand(Player player) {
        System.out.println("Cartas iniciales de " + player.getName() + ": ");
        for (Card card : player.getHand()) {
            if (card != null) {
                System.out.println(card + " ");
            }
        }
        System.out.println("\nPuntos de " + player.getName() + ": " + player.getPoints());
        System.out.println("-------------------------------------");
    }
    public void endGame() {

        for (Player player : players) {
            System.out.println("Los puntos finales de " +player.getName() +" son: "+ player.getPoints());
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
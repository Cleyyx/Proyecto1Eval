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

        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i]);
        }

        Deck deck = new Deck(new Card[52]);
        deck.getCard();

        Game game = new Game(players, deck);

        game.playRounds();

        endGame();
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
        playerTurn(player);
        if (player.getPoints() <= 21) {
            player.calculatePoints();
            determineWinner(player);
        } else {
            System.out.println(player.getName() + " se pasa de 21. ¡Pierdes la ronda!");
        }
        displayGameStatus();
    }

    public void playerTurn(Player player) {
        boolean entradaValida = false;
        while (!entradaValida && player.getPoints() <= 21) {
            String choice = IO.readString(player.getName() + ", ¿quieres pedir carta (hit) o quedarte (stand)?");
            if (choice.equals("hit")) {
                Card card = deck.drawRandomCard();
                player.addCardToHand(card);
                System.out.println(player.getName() + " recibe la carta: " + card);
            } else if (choice.equals("stand")) {
                entradaValida = true;
            } else {
                System.out.println("Por favor, ingresa 'hit' o 'stand'.");
            }
        }
        if (player.getPoints() > 21) {
            System.out.println(player.getName() + " se pasa de 21. ¡Perdiste el turno!");
        }
    }

    public void dealInitialCards(Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawRandomCard();
            player.addCardToHand(card);
        }
    }

    public void determineWinner(Player currentPlayer) {
        int currentPlayerPoints = currentPlayer.getPoints();
        System.out.println("Player Points: " + currentPlayerPoints);
        Player winner = null;
        boolean isTie = false;
        for (Player otherPlayer : players) {
            if (!currentPlayer.equals(otherPlayer)) {
                int otherPlayerPoints = otherPlayer.getPoints();
                System.out.println("Puntos de " + otherPlayer.getName() + ": " + otherPlayerPoints);

                if (otherPlayerPoints > 21) {
                    System.out.println(otherPlayer.getName() + " se pasa de 21. ¡Pierde!");
                } else if (otherPlayerPoints == 21) {
                    System.out.println(otherPlayer.getName() + " tiene Blackjack. ¡Gana!");
                } else {
                    if (currentPlayerPoints > otherPlayerPoints) {
                        winner = currentPlayer;
                        System.out.println(currentPlayer.getName() + " gana contra " + otherPlayer.getName() + "!");
                    } else if (currentPlayerPoints == otherPlayerPoints) {
                        isTie = true;
                        System.out.println("Es un empate entre " + currentPlayer.getName() + " y " + otherPlayer.getName() + "!");
                    } else {
                        winner = otherPlayer;
                        System.out.println(otherPlayer.getName() + " Gana contra " + currentPlayer.getName() + "!");
                    }
                }
            }
        }
        if (isTie && winner == null) {
            System.out.println("Es un empate entre " + currentPlayer.getName() + " y otros jugadores.");
        } else if (isTie) {
            System.out.println("Es un empate entre " + currentPlayer.getName() + " y " + winner.getName() + ".");
        } else if (winner != null) {
            System.out.println(winner.getName() + " es el ganador de la ronda.");
        }
    }

    public boolean isGameOver() {
        return false;
    }

    public Player determineOverallWinner() {
        Player overallWinner = null;
        int highestPoints = 0;
        for (Player player : players) {
            if (player.getPoints() > highestPoints) {
                highestPoints = player.getPoints();
                overallWinner = player;
            } else if (player.getPoints() == highestPoints) {
                overallWinner = null;
            }
        }

        return overallWinner;
    }

    public void displayGameStatus() {
        System.out.println("Estado actual del juego:");

        for (Player player : players) {
            System.out.print("Mano de " + player.getName() + ": ");
            for (Card card : player.getHand()) {
                if (card != null) {
                    System.out.print(card + " ");
                }
            }
            System.out.println("\nPuntos de " + player.getName() + ": " + player.getPoints());
            System.out.println("-------------------------------------");
        }

        System.out.println("Estado del mazo:");
        for (Card card : deck.getCards()) {
            if (card != null) {
                System.out.print(card + " ");
            }
        }
        System.out.println("\n-------------------------------------");
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
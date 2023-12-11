package Model;

import View.Menu;
public class Game {
    private Player[] players;
    private Deck deck;

    public Game(Player[] players, Deck deck) {
        this.players = players;
        this.deck = deck;
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

    private void playRounds() {
        while (!isGameOver()) {
            for (Player player : players) {
                playRound(player);
            }
        }
    }

    private void playRound(Player player) {

    }


    private void dealInitialCards(Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawRandomCard();
            player.addCardToHand(card);
        }
    }

    private void determineWinner(Player currentPlayer) {
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

    private boolean isGameOver() {
        return false;
    }

    private Player determineOverallWinner() {
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

    private void endGame() {

        // Mostrar resultados de cada jugador en la partida

        Player overallWinner = determineOverallWinner();
        if (overallWinner != null) {
            System.out.println("¡" + overallWinner.getName() + " es el ganador del juego!");
        } else {
            System.out.println("Es un empate. ¡Nadie gana!");
        }

        System.out.println("Gracias por jugar Blackjack!");
    }
}
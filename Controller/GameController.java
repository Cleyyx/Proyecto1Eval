package Controller;

import Model.Card;
import Model.Deck;
import Model.Player;
import View.IO;
import View.Menu;

public class GameController {
    private Player[] players;
    private Deck deck;
    private int currentRound;

    public GameController(Player[] players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public GameController() {

    }

    /**
     * Inicia el juego de blackjack.
     */
    public void startGame() {
        System.out.println("Bienvenido al juego de blackjack!");
        int numPlayers = Menu.step1_SelectNPlayers();
        String[] playerNames = Menu.step2_SelectPlayerNames(numPlayers);
        IO.readString("Presiona cualquier tecla para continuar...");
        players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(playerNames[i]);
        }

        Deck deck = new Deck(new Card[52]);
        deck.getCard();
        GameController gameController = new GameController(players, deck);

        gameController.playRounds();
        gameController.endGame();
    }

    /**
     * Juega múltiples rondas hasta que se cumple la condición de finalización del juego.
     */
    public void playRounds() {
        currentRound = 0;
        while (!isGameOver()) {
            currentRound++;
            playSingleRound();

            displayGameStatus();

            determineWinner();
            IO.readString("Presiona cualquier tecla para continuar...");
        }
    }

    /**
     * Cada jugador juega un turno cada ronda
     */
    public void playSingleRound() {
        for (Player player : players) {
            playRound(player);
        }
    }

    /**
     * Juega un turno para un jugador específico.
     *
     * @param currentPlayer Jugador para el cual se juega el turno.
     */
    public void playRound(Player currentPlayer) {
        System.out.println("Comienza el turno para " + currentPlayer.getName());
        currentPlayer.resetHand();

        dealInitialCards(currentPlayer);
        displayPlayerHand(currentPlayer);

        playerTurn(currentPlayer);
        currentPlayer.calculatePoints();
        IO.readString("Presiona cualquier tecla para continuar...");
    }

    /**
     * Maneja el turno de un jugador, permitiéndole pedir cartas o quedarse.
     *
     * @param currentPlayer Jugador cuyo turno se está manejando.
     */
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

    /**
     * Muestra la mano actual de un jugador, incluyendo las cartas iniciales y la puntuación.
     *
     * @param player Jugador cuya mano se va a mostrar.
     */
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

    /**
     * Reparte cartas iniciales a un jugador.
     *
     * @param player Jugador al que se le reparten las cartas iniciales.
     */
    public void dealInitialCards(Player player) {
        for (int i = 0; i < 2; i++) {
            Card card = deck.drawRandomCard();
            player.addCardToHand(card);
        }
        player.calculatePoints();
    }

    /**
     * Determina el ganador de la ronda.
     */
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

    /**
     * Verifica si el juego ha llegado a su fin.
     *
     * @return true si el juego ha terminado, false de lo contrario.
     */
    public boolean isGameOver() {
        return currentRound >= 3;
    }

    /**
     * Determina el ganador general del juego.
     *
     * @return Jugador que ha ganado el juego, o null en caso de empate.
     */
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

    /**
     * Muestra el estado actual del juego, incluyendo la mano de cada jugador y sus puntos.
     */
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

    /**
     * Finaliza el juego, muestra los puntos finales de cada jugador y anuncia al ganador o empate.
     */
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
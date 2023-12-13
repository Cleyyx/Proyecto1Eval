package Model;

import java.util.Objects;

public class Player {
    private String name;
    private Card[] hand;
    private int points;
    private int roundWins;

    public Player(String name) {
        this.name = name;
        this.hand = new Card[10];
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRoundWins() {
        return roundWins;
    }

    public void setRoundWins(int roundWins) {
        this.roundWins = roundWins;
    }

    /**
     * Añade una carta a la mano del jugador.
     *
     * @param card Carta a añadir a la mano del jugador.
     */
    public void addCardToHand(Card card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = card;
                break;
            }
        }
    }

    /**
     * Calcula la puntuación del jugador teniendo en cuenta el valor de una carta
     */
    public void calculatePoints() {
        points = 0;
        int numAces = 0;
        for (Card card : hand) {
            if (card != null) {
                int cardValue = card.getValue();
                if (cardValue > 10) {
                    cardValue = 10;
                }
                points += cardValue;
                if (cardValue == 1) {
                    if (points + 10 <= 21) {
                        points += 10;
                        numAces++;
                    }
                }
            }
        }
    }

    /**
     * Reinicia la mano del jugador, eliminando todas las cartas.
     */
    public void resetHand() {
        hand = new Card[10];
    }

    /**
     * Incrementa el contador de victorias de rondas del jugador.
     */
    public void incrementRoundWins() {
        roundWins++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }
}
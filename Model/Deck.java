package Model;

import java.util.Random;

public class Deck {
    private Card[] cards = new Card[52];

    public Deck(Card[] cards) {
        this.cards = cards;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    /**
     * Llena el mazo con 52 cartas, una por cada combinación de valor y palo.
     */
    public void getCard() {
        for (int i = 0; i < cards.length; i++) {
            String[] palos = {"♦", "♥", "♠", "♣"};
            int meter = 0;
            for (String palo : palos) {
                for (int j = 1; j <= 13; j++) {
                    Card card = new Card(j, palo);
                    cards[meter] = card;
                    meter++;
                }
            }
        }
    }

    /**
     * Extrae una carta aleatoria del mazo.
     *
     * @return La carta extraída.
     */
    public Card drawRandomCard() {
        if (isEmpty()) {
            System.out.println("El mazo está vacio, no puedes extraer mas cartas.");
        }
        int randomIndex;
        Card card;
        do {
            randomIndex = new Random().nextInt(cards.length);
            card = cards[randomIndex];
        } while (card == null);

        cards[randomIndex] = null;
        return card;
    }

    /**
     * Verifica si el mazo está vacío.
     *
     * @return true si el mazo está vacío, false en caso contrario.
     */
    public boolean isEmpty() {
        for (Card card : cards) {
            if (card != null) {
                return false;
            }
        }
        return true;
    }
}
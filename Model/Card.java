package Model;

import java.util.Objects;

public class Card {
    private int value;
    private String suit;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    public Card() {
        this(-1, "");
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        String faceValue;
        switch (value) {
            case 1:
                faceValue = "A";
                break;
            case 11:
                faceValue = "Q";
                break;
            case 12:
                faceValue = "J";
                break;
            case 13:
                faceValue = "K";
                break;
            default:
                faceValue = String.valueOf(value);
        }

        return "┌─────────┐\n" +
                String.format("│ %-2s      │\n", faceValue) +
                "│         │\n" +
                String.format("│    %s    │\n", suit) +
                "│         │\n" +
                String.format("│      %-2s │\n", faceValue) +
                "└─────────┘";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && Objects.equals(suit, card.suit);
    }
}

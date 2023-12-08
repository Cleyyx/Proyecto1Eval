package Model;

public class Player {
    private String name;
    private Card[] hand;
    private int points;

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

    public void addCardToHand(Card card) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = card;
                break;
            }
        }
    }

    public void calculatePoints() {
        points = 0;
        for (Card card : hand) {
            if (card != null) {
                points += card.getValue();
            }
        }
    }
}
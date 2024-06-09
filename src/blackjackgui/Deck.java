package blackjackgui;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    //public Card card;
    public Sum sum;
    public String value;
    public String suit;
    public ArrayList<String> cards;
    public int p_sum;
    public int d_sum;
    //public static ArrayList<String> playerHand, dealerHand;

    public Deck() {
        this.d_sum = 0;
        this.p_sum = 0;
        sum = new Sum();
    }

    public Deck(int DeckNum) {
        this.d_sum = 0;
        this.p_sum = 0;
        sum = new Sum();
        this.value = "";
        this.suit = "";
        MakeDeck(DeckNum);
        Shuffle();
    }

    private void MakeDeck(int DeckNum) {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for (int i = 0; i < DeckNum; i++) {
            for (String suit1 : suits) {
                for (String value1 : values) {
                    cards.add(suit1 + "." + value1);
                }
            }
        }
    }

    public void Shuffle() {
        Collections.shuffle(cards);
    }

    public ArrayList<String> getCards() {
        return cards;
    }

    public void setCards(ArrayList<String> cards) {
        this.cards = cards;
    }

    public int DealerDraw(ArrayList<String> cards) {
        return 0;
    }

    public int DrawCard(ArrayList<String> cards, boolean t) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(cards.size());
        String card = cards.get(randomNumber);
        System.out.println("Removing card at index " + randomNumber);
        System.out.println("It is a " + card);
        String[] parts = card.split("\\.");
        suit = parts[0];
        value = parts[1];
        if (t) {
            sum.Checks(value, true);
            p_sum = sum.getSum();
            cards.remove(randomNumber);
        } else {
            sum.Checks(value, false);
            d_sum = sum.getdSum();
            cards.remove(randomNumber);
        }
        return randomNumber;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;

    }

    public int DeckSize() {
        return cards.size();
    }

    /*    public static void main(String[] args) {
    
    //        Deck print = new Deck(2);
    //
    //        for (int i = 0; i < print.getCards().size(); i++) {
    //            System.out.println(i + " " + print.cards.get(i));
    //        }
    //        print.DrawCard(print.cards);
    //        System.out.println(print.DeckSize());
    }*/

    public int getSum() {
        return p_sum;
    }

}

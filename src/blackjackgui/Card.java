package blackjackgui;

import java.util.ArrayList;

public class Card {


    ArrayList<String> cards;
    public ArrayList<String> ImageCards;

    public Card(ArrayList<String> cards1) {
        this.cards = cards1;
        this.ImageCards = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            String[] parts = cards.get(i).split("\\.");
            String suit = parts[0];
            String value = parts[1];
            suit=suit.toLowerCase();
            value=value.toLowerCase();
            ImageCards.add(value + "_of_" + suit);
        }
    }

    public ArrayList<String> getImageCards()
    {
        return ImageCards;
    }

    public static void main(String[] args) {
//        Card c = new Card();
//        for (int i = 0; i < c.cards.size(); i++) {
//            System.out.println(c.cards.get(i));
//        }
//        for (int i = 0; i < c.ImageCards.size(); i++) {
//            System.out.println(c.ImageCards.get(i));
//        }
    }
}

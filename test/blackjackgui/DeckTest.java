/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package blackjackgui;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xndmo
 */
public class DeckTest {
    
    @Test 
    public void testDeckCreation(){
        Deck deck = new Deck(1); //test creating one deck
        assertNotNull(deck.getCards()); //check that the deck isnt null
        assertEquals(52, deck.getCards().size()); //check for 52 cards
    }
    
    @Test
    public void testShuffle(){
        Deck deck1 = new Deck(1);
        Deck deck2 = new Deck(1);//two decks to compare
        
        //assertEquals(deck1.getCards(), deck2.getCards());
        //when the deck is created the constructor causes it to shuffle
   //     deck1.Shuffle();
     //   deck2.Shuffle();
        
        assertNotEquals(deck1.getCards(), deck2.getCards());
        //check if they are equal,
        //apparently a 1 in trillion trillion chance according to google
    }
    
    @Test 
    public void testDrawCard(){
        Deck deck = new Deck(1);
        int initialSize = deck.DeckSize();//get initial size 
        ArrayList<String> cards = deck.getCards();
        
        int index = deck.DrawCard(cards, true);//draw a card
        assertTrue(index >= 0 && index < initialSize);//check that the index is valid
        assertEquals(initialSize - 1, deck.DeckSize());//check for -1
        
        index = deck.DrawCard(cards, false);//draw for dealer
        assertTrue(index >= 0 && index < initialSize - 1);
        assertEquals(initialSize - 2, deck.DeckSize());//check for-2
    }
    
   
}
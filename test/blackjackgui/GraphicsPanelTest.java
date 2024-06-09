/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package blackjackgui;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author xndmo
 */
public class GraphicsPanelTest {

    private GraphicsPanel panel;
    private ArrayList<String> cards;

    @Before
    public void setUp() {
        cards = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            cards.add(String.valueOf(i));
        }
        panel = new GraphicsPanel(cards);
    }

    @Test
    public void testConstructor() {
        assertEquals(52, panel.getcardImages().size());
        assertNotNull(panel.getcardImages().get(0));
    }

    @Test
    public void testPaintComponent() {
    }

    @Test
    public void testGetcardImages() {
    }

    @Test
    public void testDHit() {
        int initialSize = panel.getcardImages().size();
        panel.dHit(0, 14, "Queen", "Hearts");
        assertEquals("Dealer sum is 14", panel.getMessage3());
        assertEquals("Dealer has drawn a Queen of Hearts", panel.getMessage4());
        assertEquals(initialSize - 1, panel.getcardImages().size());
        assertEquals(1, panel.printedImages2.size());
    }

    @Test
    public void testHit() {
        int intitialSize = panel.getcardImages().size();
        panel.hit(0, 1, "Ace", "Spades");
        assertEquals("Your sum is 1", panel.getMessage());
        assertEquals("You have drawn a Ace of Spades", panel.getMessage2());
        assertEquals(intitialSize - 1, panel.getcardImages().size());
        assertEquals(1, panel.printedImages.size());
    }

    @Test
    public void testSetMessage4() {
        panel.setMessage4("Test message");
        assertEquals("Test message", panel.getMessage4());
    }

    @Test
    public void testSetMessage3() {
        panel.setMessage3("Test message");
        assertEquals("Test message", panel.getMessage3());
    }

    @Test
    public void testSetMessage2() {
        panel.setMessage2("Test message");
        assertEquals("Test message", panel.getMessage2());
    }

    @Test
    public void testSetMessage() {
        panel.setMessage("Test message");
        assertEquals("Test message", panel.message);
    }

    @Test
    public void testReset() {
        panel.hit(0, 15, "Jack", "Diamonds");
        panel.dHit(1, 17, "2", "Clubs");
        panel.Reset();
        assertTrue(panel.printedImages.isEmpty());
        assertTrue(panel.printedImages2.isEmpty());
        assertEquals(0, panel.message.length());
        assertEquals(0, panel.getMessage2().length());
        assertEquals(0, panel.getMessage3().length());
        assertEquals(0, panel.getMessage4().length());
    }

    @Test
    public void testDisplayStats() {
        panel.displayStats("paulPaulTest", 10, 10, 10, 10, 10, 10, 10);
        assertEquals("paulPaulTest", panel.username);
        assertEquals(10, panel.plays);
        assertEquals(10, panel.money);
        assertEquals(10, panel.moneyBet);
        assertEquals(10, panel.moneyWon);
        assertEquals(10, panel.moneyLost);
        assertEquals(10, panel.gamesWon);
        assertEquals(10, panel.gamesLost);

    }

}

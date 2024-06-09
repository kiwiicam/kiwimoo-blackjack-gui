/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package blackjackgui;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author xndmo
 */
public class SumTest {

    @Test
    public void testInitialSums() {
        Sum sum = new Sum();
        assertEquals(0, sum.getSum());
        assertEquals(0, sum.getdSum());
    }

    @Test
    public void testAceValue() {
        Sum sum = new Sum();

        sum.Checks("Ace", true);
        assertEquals(11, sum.getSum());

        sum = new Sum();
        sum.PlayerSum(10); // Add 10 to simulate existing sum
        sum.Checks("Ace", true);
        assertEquals(21, sum.getSum());
        /*
        sum = new Sum();
        sum.PlayerSum(10);
        sum.Checks("Ace", true);
        sum.PlayerSum(10);
        assertEquals(21, sum.getSum());*/

        sum = new Sum();
        sum.Checks("Ace", true);
        assertEquals(11, sum.getSum());
        sum.Checks("Ace", true);
        assertEquals(12, sum.getSum());
        sum.Checks("Ace", true);
        assertEquals(13, sum.getSum());
    }

    @Test
    public void testPictureCards() {
        Sum sum = new Sum();

        sum.Checks("King", true);
        assertEquals(10, sum.getSum());
        sum.Checks("Queen", true);
        assertEquals(20, sum.getSum());
        sum.Checks("Jack", true);
        assertEquals(30, sum.getSum());
    }

    @Test
    public void testNumberCards() {
        Sum sum = new Sum();

        sum.Checks("2", true);
        assertEquals(2, sum.getSum());
        sum.Checks("10", true);
        assertEquals(12, sum.getSum());
    }

    @Test
    public void testDealerAndPlayerSums() {
        Sum sum = new Sum();

        sum.Checks("2", true);
        sum.Checks("King", false);
        assertEquals(2, sum.getSum());
        assertEquals(10, sum.getdSum());
    }

}

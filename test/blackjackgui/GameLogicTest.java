/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package blackjackgui;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameLogicTest {

    GameLogic gameLogic = new GameLogic();

    @Test
    public void testGameEndLogic_PlayerWins() {
        int result = gameLogic.GameEndLogic(22, 0);
        assertEquals(0, result);
    }

    @Test
    public void testGameEndLogic_DealerWins() {
        int result = gameLogic.GameEndLogic(18, 21);
        assertEquals(1, result);
    }

    @Test
    public void testGameEndLogic_Draw() {
        int result = gameLogic.GameEndLogic(19, 19);
        assertEquals(2, result);
    }

    @Test
    public void testBustLogic_Bust() {
        boolean result = gameLogic.BustLogic(22);
        assertTrue(result);
    }

    @Test
    public void testBustLogic_NotBust() {
        boolean result = gameLogic.BustLogic(18);
        assertFalse(result);
    }
}

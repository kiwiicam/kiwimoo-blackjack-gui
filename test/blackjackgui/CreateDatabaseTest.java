/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package blackjackgui;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.sql.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class CreateDatabaseTest {

    private static CreateDatabase createDB;
    private static final String TEST_USERNAME = "testUser";
    private static final int TEST_PLAYS = 10;
    private static final int TEST_BET = 20;
    private static final int TEST_MONEY_BET = 30;
    private static final int TEST_MONEY_WON = 40;
    private static final int TEST_MONEY_LOST = 50;
    private static final int TEST_GAMES_WON = 60;
    private static final int TEST_GAMES_LOST = 70;
    public static final String DATABASE_PATH = "./GamblerStats";
    public static final String DATABASE_NAME = "GamblerStats";
    public static final String URL = "jdbc:derby:" + DATABASE_NAME + ";create=true";
    public static final String DATABASE_USERNAME = "username";
    public static final String PASSWORD = "password";

    @BeforeClass
    public static void setUpClass() {
        createDB = new CreateDatabase();
        File dbDirectory = new File(CreateDatabase.DATABASE_PATH);
        if (!dbDirectory.exists()) {
            dbDirectory.mkdirs();
            System.out.println("Database directory created.");
        }
        File dbFile = new File(CreateDatabase.DATABASE_PATH + "/" + CreateDatabase.DATABASE_NAME + ".db");
        if (!dbFile.exists()) {
            System.out.println("Database file created.");
        }
    }

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        deleteTestData(TEST_USERNAME);
        insertTestData();
    }

    @After
    public void tearDown() {
        deleteTestData(TEST_USERNAME);
    }

    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        createDB.createTable();

        try (Connection conn = DriverManager.getConnection(CreateDatabase.URL, CreateDatabase.DATABASE_USERNAME, CreateDatabase.PASSWORD)) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, "GamblerStats", null);

            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String dataType = rs.getString("TYPE_NAME");

                switch (columnName) {
                    case "username":
                        assertEquals("VARCHAR", dataType);
                        break;
                    case "plays":
                    case "money":
                    case "moneyBet":
                    case "moneyWon":
                    case "moneyLost":
                    case "gamesWon":
                    case "gamesLost":
                        assertEquals("INTEGER", dataType);
                        break;
                    default:
                        fail("Unexpected column found: " + columnName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateData() {
        createDB.insertData(TEST_USERNAME);

        int newPlays = 5;
        int newBet = 1;
        int newMoneyBet = -1;
        int newMoneyWon = -10;
        int newMoneyLost = 99;
        int newGamesWon = 70;
        int newGamesLost = 999;

        createDB.UpdateData(TEST_USERNAME, newPlays, newBet, newMoneyBet, newMoneyWon, newMoneyLost, newGamesWon, newGamesLost);

        try (Connection conn = DriverManager.getConnection(CreateDatabase.URL, CreateDatabase.DATABASE_USERNAME, CreateDatabase.PASSWORD); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM GamblerStats WHERE username='" + TEST_USERNAME + "'");
            assertTrue(rs.next());

            assertEquals(newPlays, rs.getInt("plays"));
            assertEquals(newBet, rs.getInt("money"));
            assertEquals(newMoneyBet, rs.getInt("moneyBet"));
            assertEquals(newMoneyWon, rs.getInt("moneyWon"));
            assertEquals(newMoneyLost, rs.getInt("moneyLost"));
            assertEquals(newGamesWon, rs.getInt("gamesWon"));
            assertEquals(newGamesLost, rs.getInt("gamesLost"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*    @Test
    public void testRetrieveData() {
    createDB.retrieveData(TEST_USERNAME);
    
    assertEquals(TEST_USERNAME, createDB.username);
    assertEquals(TEST_PLAYS, createDB.plays);
    assertEquals(TEST_BET, createDB.money);
    assertEquals(TEST_MONEY_BET, createDB.moneyBet);
    assertEquals(TEST_MONEY_WON, createDB.moneyWon);
    assertEquals(TEST_MONEY_LOST, createDB.moneyLost);
    assertEquals(TEST_GAMES_WON, createDB.gamesWon);
    assertEquals(TEST_GAMES_LOST, createDB.gamesLost);
    }*/

    @Test
    public void testShutdownDB() {
        try {
            createDB.ShutdownDB();
            fail("Expected SQLException was not thrown");
        } catch (SQLException e) {
            // Expected exception
        }
    }

    /*@Test
    public void testPrimaryKeyExists() {
    assertTrue(createDB.primaryKeyExists(TEST_USERNAME));
    }*/

    private void insertTestData() {
        try (Connection conn = DriverManager.getConnection(URL, DATABASE_USERNAME, PASSWORD); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM GamblerStats");

            String sql = "INSERT INTO GamblerStats (username, plays, money, moneyBet, moneyWon, moneyLost, gamesWon, gamesLost) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, TEST_USERNAME);
                pstmt.setInt(2, TEST_PLAYS);
                pstmt.setInt(3, TEST_BET);
                pstmt.setInt(4, TEST_MONEY_BET);
                pstmt.setInt(5, TEST_MONEY_WON);
                pstmt.setInt(6, TEST_MONEY_LOST);
                pstmt.setInt(7, TEST_GAMES_WON);
                pstmt.setInt(8, TEST_GAMES_LOST);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestData(String username) {
        try (Connection conn = DriverManager.getConnection(URL, DATABASE_USERNAME, PASSWORD); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM GamblerStats WHERE username = '" + username + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

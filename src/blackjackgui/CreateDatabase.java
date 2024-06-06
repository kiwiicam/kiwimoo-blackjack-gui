package blackjackgui;

import java.io.File;
import java.sql.*;

public class CreateDatabase {

    public static final String DATABASE_PATH = "./GamblerStats";
    public static final String DATABASE_NAME = "GamblerStats";
    public static final String URL = "jdbc:derby:" + DATABASE_NAME + ";create=true";
    public static final String DATABASE_USERNAME = "username";
    public static final String PASSWORD = "password";
    public String user;
    public String user2;
    String username;
    int plays;
    int money;
    int moneyBet;
    int moneyWon;
    int moneyLost;
    int gamesWon;
    int gamesLost;

    public CreateDatabase() {
        username = "";
        plays = 0;
        money = 0;
        moneyBet = 0;
        moneyWon = 0;
        moneyLost = 0;
        gamesWon = 0;
        gamesLost = 0;

    }

    public static void main(String[] args) {

    }

    public void createTable() {
        File dbDirectory = new File(DATABASE_PATH);
        if (dbDirectory.exists()) {
            System.out.println("it exists");
        } else {
            System.out.println("creating database ");
            try (Connection conn = DriverManager.getConnection(URL, DATABASE_USERNAME, PASSWORD); Statement st = conn.createStatement()) {
                String sql = "CREATE TABLE GamblerStats ("
                        + "username VARCHAR(255) PRIMARY KEY, "
                        + "plays INT, "
                        + "money INT, "
                        + "moneyBet INT, "
                        + "moneyWon INT, "
                        + "moneyLost INT, "
                        + "gamesWon INT, "
                        + "gamesLost INT)";
                st.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void UpdateData(String username, int plays, int bet, int moneyBet, int moneyWon, int moneyLost, int gamesWon, int gamesLost) {
        String sql = "UPDATE GamblerStats SET plays = ?, money = ?, moneyBet = ?, moneyWon = ?, moneyLost = ?, gamesWon = ?, gamesLost = ? WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL, DATABASE_USERNAME, PASSWORD); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, plays);
            pstmt.setInt(2, bet);
            pstmt.setInt(3, moneyBet);
            pstmt.setInt(4, moneyWon);
            pstmt.setInt(5, moneyLost);
            pstmt.setInt(6, gamesWon);
            pstmt.setInt(7, gamesLost);
            pstmt.setString(8, username);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("GamblerStats updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String username) {

        System.out.println("user doesnt exist");
        String sql = "INSERT INTO GamblerStats (username, plays, money, moneyBet, moneyWon, moneyLost, gamesWon, gamesLost) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, DATABASE_USERNAME, PASSWORD); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setInt(2, 0);
            pstmt.setInt(3, 0);
            pstmt.setInt(4, 0);
            pstmt.setInt(5, 0);
            pstmt.setInt(6, 0);
            pstmt.setInt(7, 0);
            pstmt.setInt(8, 0);

            pstmt.executeUpdate();
            System.out.println("New GamblerStats added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void retrieveData(String usern) {
        String sql = "SELECT * FROM GamblerStats WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, DATABASE_USERNAME, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usern);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                username = rs.getString("username");
                plays = rs.getInt("plays");
                money = rs.getInt("money");
                moneyBet = rs.getInt("moneyBet");
                moneyWon = rs.getInt("moneyWon");
                moneyLost = rs.getInt("moneyLost");
                gamesWon = rs.getInt("gamesWon");
                gamesLost = rs.getInt("gamesLost");

                System.out.println("Username: " + username);
                System.out.println("Plays: " + plays);
                System.out.println("Money: " + money);
                System.out.println("Money Bet: " + moneyBet);
                System.out.println("Money Won: " + moneyWon);
                System.out.println("Money Lost: " + moneyLost);
                System.out.println("Games Won: " + gamesWon);
                System.out.println("Games Lost: " + gamesLost);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ShutdownDB() throws SQLException {
        DriverManager.getConnection("jdbc:derby:;shutdown=true");
    }

    public boolean primaryKeyExists(String username) {
        String sql = "SELECT COUNT(*) FROM GamblerStats WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, DATABASE_USERNAME, PASSWORD); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

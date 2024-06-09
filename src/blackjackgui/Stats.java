/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackgui;

import java.sql.*;

/**
 *
 * @author xndmo
 */
public class Stats {

    public static final String URL = "jdbc:derby://localhost:1527/GamblersStats";
    public static final String DATABASEUSERNAME = "abc";
    public static final String PASSWORD = "123";
     public static String username;
    public static int plays, money, moneyBet, moneyWon, moneyLost, gamesWon, gamesLost;

    /*    public Stats stats(username){
    this.username = username;
    this.plays = plays;
    this.money = money;
    
    }*/
    public static void getStats(String userKey) {
        String sql = "SELECT * FROM ABC.PLAYERS WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(URL, DATABASEUSERNAME, PASSWORD); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, userKey);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                username = userKey;
                plays = rs.getInt("plays");
                money = rs.getInt("money");
                moneyBet = rs.getInt("moneyBet");
                moneyWon = rs.getInt("moneyWon");
                moneyLost = rs.getInt("moneyLost");
                gamesWon = rs.getInt("gamesWon");
                gamesLost = rs.getInt("gamesLost");
                /*
                System.out.println("User: " + username);
                System.out.println("Plays: " + plays);
                System.out.println("Money: " + money);
                System.out.println("Money Bet: " + moneyBet);
                System.out.println("Money Won: " + moneyWon);
                System.out.println("Money Lost: " + moneyLost);
                System.out.println("Games Won: " + gamesWon);
                System.out.println("Games Lost: " + gamesLost);*/
            } else{
                System.out.println("User was not found");
            }
        } catch (SQLException e) {
        }
    }

}

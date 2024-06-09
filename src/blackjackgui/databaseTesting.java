/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackgui;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author xndmo
 */
public class databaseTesting {
    
//this does not contain any testCase and was rather
    //just testing to see that the database was created
    
//    public static void main(String[] args) {
//        String input = "";
//        Scanner scan = new Scanner(System.in);
//        
//        System.out.println("Enter username");
//        input= scan.nextLine().trim();
//        
//        Stats.getStats(input);
//        printStats();
//        /*try (Connection conn = DatabaseConnection.getConnection(); 
//                Statement stmt = conn.createStatement()) {
//        
//        ResultSet rs = stmt.executeQuery("SELECT * FROM ABC.PLAYERS");
//        
//        while (rs.next()) {
//        String username = rs.getString("username");
//        int money = rs.getInt("money");
//        
//        System.out.println("user: " +username+" money:  "+money);
//        }
//        } catch (SQLException e) {
//        e.printStackTrace();
//        }*/
//        
//    }
    
    public static void printStats(){
        System.out.println("User: " + Stats.username);
                System.out.println("Plays: " + Stats.plays);
                System.out.println("Money: " + Stats.money);
                System.out.println("Money Bet: " + Stats.moneyBet);
                System.out.println("Money Won: " + Stats.moneyWon);
                System.out.println("Money Lost: " + Stats.moneyLost);
                System.out.println("Games Won: " + Stats.gamesWon);
                System.out.println("Games Lost: " + Stats.gamesLost);
    }
}

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
public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/GamblersStats";
        String username = "abc";
        String password = "123";

        return DriverManager.getConnection(url, username, password);
    }
}

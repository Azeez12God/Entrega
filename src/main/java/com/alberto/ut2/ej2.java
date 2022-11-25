package com.alberto.ut2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// realiza un ejemplo de código susceptible de sufrir ataque por inyección de SQL.
public class ej2 {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/alberto","root","root");
            Statement stmt = con.createStatement();)
        {
            String sql = "INSERT INTO FUTBOLISTAS values (1, 'Alberto', 100)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

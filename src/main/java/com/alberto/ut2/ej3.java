package com.alberto.ut2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * - haz la variante que corrija el inconveniente anterior (haciendo uso de PreparedStatement)
 */
public class ej3 {
    private static final Scanner tec = new Scanner(System.in);
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/alberto","root","root")){
            String sentencia = "INSERT INTO FUTBOLISTAS (FUTBOLISTAID,NOMBRE,MEDIAGOLES) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sentencia);
            System.out.println("Dime la id que quieres insertar:");
            int id  = tec.nextInt();
            statement.setInt(1,id);
            statement.setString(2,"Alberto");
            statement.setDouble(3,2500);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

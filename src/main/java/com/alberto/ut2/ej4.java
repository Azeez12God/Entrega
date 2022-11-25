package com.alberto.ut2;

import java.sql.*;

/**
 * - realiza una transacción que no se complete al provocar un rollback.
 */
public class ej4 {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/alberto", "root", "root");

            try {
                String altaFutbol1 = "INSERT INTO futbolistas (futbolistaid,nombre,mediagoles) values (?,?,?)";
                String altaFutbol2 = "INSERT INTO futbolistas (futbolistaid,nombre,mediagoles) values (?,?,?)";

                connection.setAutoCommit(false);
                PreparedStatement statement = connection.prepareStatement(altaFutbol1, PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setInt(1, 3);
                statement.setString(2, "DieGOD");
                statement.setDouble(3, 30000);
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int idFutbolista = rs.getInt(1);
                System.out.println("El identificador del producto añadido es " + idFutbolista);
                statement.close();

                statement = connection.prepareStatement(altaFutbol2);
                idFutbolista = 5;
                String nombre = "Vicedo";
                statement.setInt(1, idFutbolista);
                statement.setString(2, nombre);
                statement.setDouble(3, 4);
                statement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
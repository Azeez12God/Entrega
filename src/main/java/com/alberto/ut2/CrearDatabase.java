package com.alberto.ut2;

import java.sql.*;

public class CrearDatabase {
    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432", "root", "root");){
            System.out.println("Conectando con localhost...");
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
            String bdd = "CREATE DATABASE alberto";
            stmt.execute(bdd);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/alberto", "root", "root");){
            Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
            String tabla = "CREATE TABLE FUTBOLISTAS(FUTBOLISTAID int NOT NULL,NOMBRE varchar(50) NOT NULL,MEDIAGOLES NUMERIC NOT NULL, CONSTRAINT PK_FUTBOLISTAS PRIMARY KEY (FUTBOLISTAID))";
            stmt.executeUpdate(tabla);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

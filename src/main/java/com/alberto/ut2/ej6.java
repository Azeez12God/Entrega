package com.alberto.ut2;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;

public class ej6
{
    public static void main(String[] args) {
        Connection conexion=null;
        String jdbcUrl="jdbc:postgresql://localhost/alberto";
        PreparedStatement sentencia = null;
        ResultSet idGenerados=null;
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl(jdbcUrl);
        bds.setUsername("root");
        bds.setPassword("root");
        bds.setMinIdle(5);
        bds.setMinIdle(10);
        bds.setMaxOpenPreparedStatements(100);
        double media = 10;
        int idFutbolista=10;
        String nombre = "Madagascar";
        // El id del producto que vamos a registrar aún no se conoce
        String sqlAltaFutbolista1 = "INSERT INTO futbolistas (futbolistaid,nombre,mediagoles) VALUES (?,?,?)";
        String sqlAltaFutbolista2 = "INSERT INTO futbolistas (futbolistaid,nombre,mediagoles) VALUES (?,?,?)";

        try {
            conexion = bds.getConnection();
            conexion = DriverManager.getConnection(jdbcUrl,"root","root");
            conexion.setAutoCommit(false);
            sentencia = conexion.prepareStatement(sqlAltaFutbolista1, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, idFutbolista);
            sentencia.setString(2, nombre);
            sentencia.setDouble(3, media);
            sentencia.executeUpdate();
            // Obtiene el id del producto que se acaba de registrar
            idGenerados = sentencia.getGeneratedKeys();
            idGenerados.next();
            sentencia = conexion.prepareStatement(sqlAltaFutbolista2);
            idFutbolista = 11;
            nombre = "Martinxulo";
            media = 240;
            sentencia.setInt(1, idFutbolista);
            sentencia.setString(2, nombre);
            sentencia.setDouble(3, media);
            sentencia.executeUpdate();
            // Valida la transacción
            conexion.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conexion != null) {
                try {
                    conexion.setAutoCommit(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            DbUtils.closeQuietly(idGenerados);
            DbUtils.closeQuietly(sentencia);
            DbUtils.closeQuietly(conexion);
        }
    }
}

package com.alberto.ut2;

import java.sql.*;

/**
 * - utiliza al menos una función y un procedimiento. Adjunta el código de las funciones y procedimientos también.
 */

public class ej5
{
    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cstmt = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            //Abrir la conexión con la Base de Datos
            System.out.println("Conectando con la Base de datos...");
            String jdbcUrl = "jdbc:postgresql://localhost:5432/alberto";
            conn = DriverManager.getConnection(jdbcUrl,"root","root");
            System.out.println("Conexión establecida con la Base de datos...");

            String sentenciaSql = "call borrafutbolistas()";

            cstmt = conn.prepareCall(sentenciaSql);
            cstmt.execute();

            sentenciaSql = "select public.cuentafutbolistas()";
            prst = conn.prepareStatement(sentenciaSql);
            rs = prst.executeQuery();
            rs.next();

            if(rs.wasNull())
                System.out.println("No hay futbolistas");
            else
            {
                int cont = rs.getInt(1);
                System.out.println("El total de futbolistas es: " + cont);
            }
        }catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        }
    }
}

package com.alberto.ut2;

import java.sql.*;

/*- sobre un ResultSet de todo el contenido de la tabla realiza al menos una inserción, una modificación y un borrado utilizando,
al menos, 5 métodos de desplazamiento a lo largo del ResultSet.
 */
public class ej1 {
    public static void main(String[] args) {
        try(Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/alberto","root","root");){
            System.out.println("Conectando con la base de datos...");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select * from futbolistas";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                rs.deleteRow();
            }

            System.out.println("Hacemos una inserción");
            rs.moveToInsertRow();
            rs.updateInt(1,1);
            rs.updateString(2,"Azeez");
            rs.updateDouble(3,200);
            rs.insertRow();

            rs.moveToInsertRow();
            rs.updateInt(1,2);
            rs.updateString(2,"Diego");
            rs.updateDouble(3, 500);
            rs.insertRow();

            //Modificamos el último creado
            System.out.println("Situamos el cursor al final");
            System.out.println("Modificamos el nombre de la categoría del último registro");
            rs.last();
            rs.updateString("nombre", "Anto");
            rs.updateRow();

            //Borramos el primero
            //Nos situamos en el primero del ResultSet
            System.out.println("Borramos el primer registro");
            rs.absolute(1);
            rs.deleteRow();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

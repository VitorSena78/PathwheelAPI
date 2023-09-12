package com.pathwheel.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcRegister {

    public void getjdbc() throws SQLException {
        //String sql = "select Id_nota, titulo from notas";
        String sql = "select id, user_name from pathwheel.user where exclusion_date is null and (user_name = 'vitorsena77@gmail.com' or email = 'vitorsena77@gmail.com')";
        PreparedStatement stm = PostgreSql.getConnection().prepareStatement(sql);
        consulta(stm);
    }
    private static void consulta(PreparedStatement stm) {
        try {
            System.out.println(stm.toString());
            ResultSet result = stm.executeQuery();
            while (result.next()){
                System.out.println("Id: "+ result.getInt("id")+ ", titulo: "+ result.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

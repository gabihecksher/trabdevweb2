package model;

import java.sql.*;

public class Conexao {
    public Connection criaConexao() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/blog", "root", null);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void fechaConexao (Connection a) {
        try {
            if (a != null)
                a.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
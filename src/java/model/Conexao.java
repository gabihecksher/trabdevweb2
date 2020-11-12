package model;

import java.sql.*;

public class Conexao {
    private static Connection conexao = null;
    
    public static Connection criaConexao() throws SQLException {
        if ( conexao == null ) {
            try {
                //Carrega o Driver JDBC na memória
                Class.forName("com.mysql.jdbc.Driver"); //load driver                       
                System.out.println("Driver foi carregado!");
                //Abre a conexão com o banco de dados via JDBC
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "");
                System.out.println("Conexão realizada com sucesso!");
            }
            catch( ClassNotFoundException e ) {
                System.out.println("Driver não foi localizado!");
            }
        }
        // Retorna um objeto Connection, contendo a conexão aberta com o BD
        return conexao;
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
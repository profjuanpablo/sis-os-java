package br.com.infox.dal;

import java.sql.*;

public class ModuloConexao {
        //conexão com o banco 
        public static Connection conector() {
        java.sql.Connection conexao = null;

        //Chamar o driver hop ha
        String driver = "com.mysql.jdbc.Driver";

        //informações referentes ao banco 
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";
        
        //conectando ao banco
            try {
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
                return conexao;
            } catch (ClassNotFoundException | SQLException e ) {
                //esclarecer o erro
                

                System.out.println("Não foi possivel estabelecer Conexão");
               return null;
            }

    }
}

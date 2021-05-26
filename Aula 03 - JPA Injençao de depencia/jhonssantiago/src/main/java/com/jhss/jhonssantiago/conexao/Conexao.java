
package com.jhss.jhonssantiago.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    public static void main(String[] args) {
        
        //testar conexão
        System.out.println(Conexao.criarConexao());
                
    }
    
    /** 
     * método que vai retornar uma conexão
     * método static: método da classe
     * método não static: método de objeto
     * @return
     */
    public static Connection criarConexao(){
        try {
            //carregar o driver de conexão
            Class.forName("org.postgresql.Driver");
            //parâmetros
            String url = "jdbc:postgresql://localhost/BANCO_DADOS";
            String usuario = "postgres";
            String senha = "1998";
            //retorna a conexão com o banco de dados
            return DriverManager.getConnection(url, usuario, senha);
           
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}

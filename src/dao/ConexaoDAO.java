package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoDAO {
    
    public Connection conectarBD() {
        Connection conexao = null;

        try{
            String url = "jdbc:mysql://localhost:3306/login_gpt"; 
            String user = "root";
            String password = "123456";

            conexao = DriverManager.getConnection(url, user, password);
        } catch (SQLException errorSQL) {
            JOptionPane.showMessageDialog(null, "ConectarBD: " + errorSQL);
        }

        return conexao;
    }

}

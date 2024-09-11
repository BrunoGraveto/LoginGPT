package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dto.UsuarioDTO;

public class UsuarioDAO {
    
    Connection conexao;

    // Verificar se o Email existe
    public ResultSet verificarEmail(UsuarioDTO usuarioDTO) {
        conexao = new ConexaoDAO().conectarBD();

        try {
            String sql = "select * from usuario where email_usuario = ?";

            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuarioDTO.getEmail());

            return pstm.executeQuery();
        } catch (SQLException errorSQL) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO verificarEmail(): " + errorSQL);
            return null;
        }
    }

    // Verificar a Senha
    public ResultSet verificarSenha(UsuarioDTO usuarioDTO) {
        conexao = new ConexaoDAO().conectarBD();

        try {
            String sql = "select * from usuario where email_usuario = '" + usuarioDTO.getEmail() + "' and senha_usuario = ?";

            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuarioDTO.getSenha());

            return pstm.executeQuery();
        } catch (SQLException errorSQL) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO verificarSenha(): " + errorSQL);
            return null;
        }
    }

    // Cadastrar o Usuário
    public void cadastrarUsuario(UsuarioDTO usuarioDTO) {
        conexao = new ConexaoDAO().conectarBD();

        try {
            String sql = "insert into usuario(email_usuario, senha_usuario) values (?, ?)";

            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, usuarioDTO.getEmail());
            pstm.setString(2, usuarioDTO.getSenha());
            
            pstm.execute();
            pstm.close();
        } catch (SQLException errorSQL) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO cadastrarUsuario(): " + errorSQL);
        }
    }

}

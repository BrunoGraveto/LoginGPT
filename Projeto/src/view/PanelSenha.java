package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import main.Main;
import main.Utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.sql.SQLException;
import java.sql.ResultSet;

public class PanelSenha extends Panel implements ActionListener{

    // Usuario
    UsuarioDTO usuarioDTO;

    public PanelSenha() {
        // Label para mostrar que está na Tela de Login
        lbInfo = new JLabel("Digite sua senha");
        lbInfo.setFont(new Font("Arial", Font.BOLD, 20));
        lbInfo.setSize(160,40);
        lbInfo.setLocation(Utility.getCenter(WIDTH, lbInfo.getWidth()), 60);
        add(lbInfo);
        // Campo de Texto para colocar o Email
        txtEmail = new JTextField(""+Main.frame.getEmail());
        txtEmail.setSize(200,40);
        txtEmail.setLocation(Utility.getCenter(WIDTH, txtEmail.getWidth()), 140);
        txtEmail.addMouseListener(mouseAdapter);
        txtEmail.setFocusable(false);
        add(txtEmail);
        // Campo de Texto para colocar a Senha
        txtSenha = new JTextField("Senha*");
        txtSenha.setSize(200,40);
        txtSenha.setLocation(Utility.getCenter(WIDTH, txtSenha.getWidth()), 190);
        txtSenha.addMouseListener(mouseAdapter);
        txtSenha.addActionListener(this);
        txtSenha.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (txtSenha.getText().equals("Senha*")) {
                    txtSenha.setText("");
                }
            }
        });
        add(txtSenha);
        // Label que mostra os erros
        lbError = new JLabel();
        lbError.setSize(200,15);     
        lbError.setLocation(Utility.getCenter(WIDTH, lbError.getWidth()), 235);
        lbError.setForeground(Color.RED);
        add(lbError);
        // Botão para iniciar Login
        btnContinuar = new JButton("Continuar");
        btnContinuar.setSize(200,40);
        btnContinuar.setLocation(Utility.getCenter(WIDTH, btnContinuar.getWidth()), 240);
        btnContinuar.setFocusable(false);
        btnContinuar.addActionListener(this);
        add(btnContinuar);
        // Mode e Etapa
        setEtapa(2);
    }

    // Clique no Botão Continuar
    @Override
    public void actionPerformed(ActionEvent e) {
        // Usuario DTO
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail(txtEmail.getText());
        usuarioDTO.setSenha(txtSenha.getText());
        // Usuario DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            // Caso esteja no Modo Login
            if (getMode().equals("Login")) {
                ResultSet rs = usuarioDAO.verificarSenha(usuarioDTO);

                // Se a Senha estiver Correta
                if (rs.next()) {
                    System.exit(0);
                }
                // Se a senha estiver Incorreta
                else {
                    mostrarErro("Senha inválida!", true);
                    // lbError.setText("Senha Inválida!");
                    // txtSenha.setForeground(Color.RED);
                }
            }
            // Caso esteja no Modo Cadastro
            else if (getMode().equals("Cadastro")) {
                if (txtSenha.getText().length() >= 8) {
                    // Cadastra o Email
                    usuarioDAO.cadastrarUsuario(usuarioDTO);
                    // Volta a Tela Inicial
                    Main.frame.setEtapa(1);
                    Main.frame.setMode("Login");
                    Main.frame.showPanelEmail();
                } else {
                    mostrarErro("Digite pelo menos 8 caracteres!", true);
                }
            }
        } catch (SQLException errorSQL) {
            JOptionPane.showMessageDialog(null, "PanelSenha: " + errorSQL);
        }
    }

}
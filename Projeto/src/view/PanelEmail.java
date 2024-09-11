package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import dto.UsuarioDTO;
import main.Main;
import main.Utility;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PanelEmail extends Panel implements ActionListener{

    public PanelEmail() {
        // Label para mostrar que está na Tela de Login
        lbInfo = new JLabel();
        lbInfo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbInfo);
        // Campo de Texto para colocar o Email
        txtEmail = new JTextField("Email*");
        txtEmail.setSize(200,40);
        txtEmail.setLocation(Utility.getCenter(WIDTH, txtEmail.getWidth()), 140);
        txtEmail.addMouseListener(mouseAdapter);
        txtEmail.setFocusable(false);
        txtEmail.addActionListener(this);
        add(txtEmail);
        // Label que mostra os erros
        lbError = new JLabel();
        lbError.setSize(200,15);     
        lbError.setLocation(Utility.getCenter(WIDTH, lbError.getWidth()), 190);
        lbError.setForeground(Color.RED);
        add(lbError);
        // Botão para iniciar Login
        btnContinuar = new JButton("Continuar");
        btnContinuar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnContinuar.setSize(200,40);
        btnContinuar.setLocation(Utility.getCenter(WIDTH, btnContinuar.getWidth()), 195);
        btnContinuar.setFocusable(false);
        btnContinuar.addActionListener(this);
        add(btnContinuar);
        // Label perguntando se o Usuário ja Possui Conta
        lbTemConta = new JLabel();
        lbTemConta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbTemConta.addMouseListener(mouseAdapter);
        add(lbTemConta);
        // Etapa
        setEtapa(1);
    }

    // Clique no Botão Continuar
    @Override
    public void actionPerformed(ActionEvent e) {
        // Usuario DTO
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail(txtEmail.getText());
        // Usuario DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ResultSet rs = usuarioDAO.verificarEmail(usuarioDTO);
        
        try {
            // Caso esteja no Modo Login
            if (getMode().equals("Login")) { 
                // Se Encontrar o Email
                if (rs.next()) {
                    lbError.setText("");
                    Main.frame.showPanelSenha();
                    Main.frame.setMode("Login");
                }
                // Se não tiver o Email
                else {
                    mostrarErro("Email não encontrado!", false);
                }
            }
            // Caso esteja no Modo Cadastro
            else if (getMode().equals("Cadastro")) {
                // Regex para Verificar o Email
                Pattern p = Pattern.compile(EMAIL_REGEX);
                Matcher m = p.matcher(txtEmail.getText());
                // Se não tiver o Email já Cadastrado e se o Formato estiver Correto
                if (!rs.next() && m.find()) {
                    lbError.setText("");
                    Main.frame.showPanelSenha();
                    Main.frame.setMode("Cadastro");
                }
                // Se Encontrar o Email
                else if (rs.next()) {
                    mostrarErro("Esse Email já existe!", false);
                }
                // Se o Formato estiver Incorreto
                else if (!m.find()) {
                    mostrarErro("Email inválido!", false);
                }
            }
        } catch (SQLException errorSQL) {
            JOptionPane.showMessageDialog(null, "PanelEmail: " + errorSQL);
        }
    }

}
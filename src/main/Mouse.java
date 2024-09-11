package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.Panel;

public class Mouse extends MouseAdapter{

    Panel panel;

    public Mouse(Panel panel) {
        this.panel = panel;
    }

    public void mouseClicked(MouseEvent e) {
        // Componente pra ser verificado
        Object componenteClicado = e.getSource();
        // Caso clique na Label para iniciar Cadastro ou Login
        if (componenteClicado == panel.lbTemConta) {
            // Verifica se a Label de Erro estava Ativada
            if (!panel.lbError.getText().equals("")) {
                panel.btnContinuar.setLocation(panel.btnContinuar.getX(), panel.btnContinuar.getY()-20);
                panel.lbTemConta.setLocation(panel.lbTemConta.getX(), panel.lbTemConta.getY()-20);
                panel.lbError.setText("");
            }
            // Se o Modo Atual for Login
            if (panel.getMode().equals("Login")) {
                panel.setMode("Cadastro");
            }
            // Se o Modo Atual for Cadastro
            else if (panel.getMode().equals("Cadastro")) {
                panel.setMode("Login");
            }
        }
        // Cliques no Painel de Login
        else if (panel.getEtapa() == 1) {
            // Clique no Campo de Texto do Email
            if (componenteClicado == panel.txtEmail) {
                panel.txtEmail.setText("");
                panel.txtEmail.setFocusable(true);
                panel.txtEmail.requestFocusInWindow();
            }
            // Clique no Painel
            else if (componenteClicado == panel) {
                if (panel.txtEmail.getText().equals("")) {
                    panel.txtEmail.setText("Email*");
                }
                panel.txtEmail.setFocusable(false);
            }
        }
        // Cliques no Painel de Cadastro
        else if (panel.getEtapa() == 2) {
            // Clique no Campo de Texto do Email
            if (componenteClicado == panel.txtEmail) {
                panel.btnContinuar.setLocation(panel.btnContinuar.getX(), panel.btnContinuar.getY()-20);
                Main.frame.showPanelEmail();
            }
            // Clique no Campo de Texto da Senha
            else if (componenteClicado == panel.txtSenha) {
                panel.txtSenha.setText("");
                panel.txtSenha.setFocusable(true);
                panel.txtSenha.requestFocusInWindow();
            }
            // Clique no Painel
            else if (componenteClicado == panel) {
                if (panel.txtSenha.getText().equals("")) {
                    panel.txtSenha.setText("Senha*");
                }
                panel.txtSenha.setFocusable(false);
            }
        }
    }

}

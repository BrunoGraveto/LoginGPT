package view;

import javax.swing.JFrame;

public class Frame extends JFrame {

    // Paineis
    private PanelEmail panelEmail;
    private PanelSenha panelSenha;

    public Frame() {
        // Configurações do Frame
        setTitle("ChatGPT");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        // Painel Inicial
        panelEmail = new PanelEmail();
        panelEmail.setMode("Login");
        add(panelEmail);
        // Ajustes Finais após incremento do Painel
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Obter o texto que está no Campo de Texto Email
    public String getEmail() {
        return panelEmail.txtEmail.getText();
    }

    // Seta o Modo da Janela
    public void setMode(String mode) {
        panelEmail.setMode(mode);
        panelSenha.setMode(mode);
    }

    // Seta a Etapa
    public void setEtapa(int etapa) {
        panelEmail.setEtapa(etapa);
    }

    // Mostra o Painel de Email
    public void showPanelEmail() {
        // Alterna os Paineis
        remove(panelSenha);
        add(panelEmail);
        // Foca no JTextField do Email
        panelEmail.txtEmail.setFocusable(true);
        panelEmail.txtEmail.requestFocusInWindow();
        // Restaura os gráficos
        revalidate();
        repaint();
    }

    // Mostra o Painel de Senha
    public void showPanelSenha() {
        // Alterna os Paineis
        panelSenha = new PanelSenha();
        remove(panelEmail);
        add(panelSenha);
        // Foca no JTextField da Senha
        panelSenha.txtSenha.setFocusable(true);
        panelSenha.txtSenha.requestFocusInWindow();
        panelSenha.txtSenha.setCaretPosition(0);
        // Restaura os gráficos
        revalidate();
        repaint();
    }
}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Mouse;
import main.Utility;

public class Panel extends JPanel{

    // Tamanho Janela
    final int WIDTH = 400;
    final int HEIGHT = 400;

    // REGEX
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // Mouse Adapter
    public MouseAdapter mouseAdapter = new Mouse(this);

    // Etapa - Email(1) Senha(2)
    private int etapa;

    // Modo da janela - "Login" ou "Cadastro"
    private String mode;

    // Componentes
    JLabel lbInfo;
    public JTextField txtEmail;
    public JTextField txtSenha;
    public JLabel lbError;
    public JButton btnContinuar;
    public JLabel lbTemConta;

    public Panel() {
        // Configurações do Painel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);
        addMouseListener(mouseAdapter);
    }

    // Mostrar Erro
    public void mostrarErro(String msgError, boolean senha) {

        // Se Tiver algum Erro Relacionado a Senha
        if (senha && txtSenha.getForeground() != Color.RED) {
            btnContinuar.setLocation(btnContinuar.getX(), btnContinuar.getY()+20);
            txtSenha.setForeground(Color.RED);
        }
        // Se Tiver algum Erro Relacionado ao Email
        else if (!senha && txtEmail.getForeground() != Color.RED) {
            btnContinuar.setLocation(btnContinuar.getX(), btnContinuar.getY()+20);
            lbTemConta.setLocation(lbTemConta.getX(), lbTemConta.getY()+20);
            txtEmail.setForeground(Color.RED);
        }
        lbError.setText(msgError);

    }

    // Seta o Modo da Janela - Login ou Cadastro
    public void setMode(String mode) {

        // Restaura a Cor do Campo de Texto
        txtEmail.setForeground(Color.DARK_GRAY);

        // Se for o Modo Login
        if (mode == "Login") {
            modoLogin();
        }
        // Se for o modo Cadastro
        else if (mode == "Cadastro") {
            modoCadastro();
        }

        // Centraliza a Label Info
        lbInfo.setLocation(Utility.getCenter(WIDTH, lbInfo.getWidth()), 60);

        // Caso esteja na Etapa do Email
        if (etapa == 1) {
            // Centraliza a Label "Tem Conta"
            lbTemConta.setLocation(Utility.getCenter(WIDTH, lbTemConta.getWidth()), 240);
        }

        this.mode = mode;
    }

    // Modo Login
    public void modoLogin() {
        // Configurações Label
        lbInfo.setText("Que bom que você voltou");
        lbInfo.setSize(250,40);

        // Caso esteja na Etapa do Email
        if (etapa == 1) {
            lbTemConta.setText("Não tem uma conta? Cadastrar");
            lbTemConta.setSize(180, 15);        
        }
    }

    // Modo Cadastro
    public void modoCadastro() {
        // Configurações Label
        lbInfo.setText("Criar uma conta");
        lbInfo.setSize(160,40);

        // Caso esteja na Etapa do Email
        if (etapa == 1) {
            lbTemConta.setText("Tem uma conta? Entrar");
            lbTemConta.setSize(140,15); 
        }       
    }

    // Obtem o Modo Atual
    public String getMode() {
        return this.mode;
    }

    // Seta a Etapa
    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    // Obtem a Etapa
    public int getEtapa() {
        return this.etapa;
    }   
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CipherChat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class LOGIN extends JFrame implements ActionListener, FocusListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JCheckBox cb1;
    private UsuarioList usuarios;

    public LOGIN(UsuarioList usuarios) {
        this.usuarios = usuarios;
        initComponents();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Welcome");
        titleLabel.setFont(new Font("Kristen ITC", Font.BOLD, 20));
        titleLabel.setBounds(200, 10, 100, 30);
        this.add(titleLabel);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("./Images/login.jpeg"));
        Image imageDimension = imageIcon.getImage().getScaledInstance(120, 110, Image.SCALE_SMOOTH);
        ImageIcon adjustedImageIcon = new ImageIcon(imageDimension);
        JLabel imageLabel = new JLabel(adjustedImageIcon);
        imageLabel.setBounds(190, 30, 120, 110);
        this.add(imageLabel);

        JLabel usernameLabel = new JLabel("Codigo:");
        usernameLabel.setFont(new Font("Kristen ITC", Font.BOLD, 12));
        usernameLabel.setBounds(70, 140, 80, 25);
        this.add(usernameLabel);

        usernameField = new JTextField("");
        usernameField.setBounds(130, 140, 260, 25);
        this.add(usernameField);

        JLabel passwordLabel = new JLabel("Contrasena:");
        passwordLabel.setFont(new Font("Kristen ITC", Font.BOLD, 12));
        passwordLabel.setBounds(43, 180, 80, 25);
        this.add(passwordLabel);

        passwordField = new JPasswordField("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.setBounds(130, 180, 260, 25);
        passwordField.addFocusListener(this);
        this.add(passwordField);

        loginButton = new JButton("Iniciar Sesion");
        loginButton.setBounds(275, 260, 150, 25);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(75, 193, 31));
        loginButton.addActionListener(this);
        this.add(loginButton);

        registerButton = new JButton("Registrarse");
        registerButton.setBounds(75, 260, 150, 25);
        registerButton.setBackground(new Color(214, 225, 50));
        registerButton.addActionListener(this);
        this.add(registerButton);

        cb1 = new JCheckBox("Ver Password");
        cb1.setFont(new Font("Kristen ITC", Font.BOLD, 12));
        cb1.setBounds(130, 220, 150, 25);
        cb1.setVisible(true);
        cb1.addActionListener(this);
        this.add(cb1);

        this.setTitle("Login");
        this.setLocationRelativeTo(null);
        this.setSize(500, 350);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cb1) {
            if (cb1.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('\u25CF');
            }
        } else if (ae.getSource() == loginButton) {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            String pwd = new String(password);

            // Validación de admin y moderador
            if (username.equals("3198935960914") && pwd.equals("p1IPC1")) {
                new AdminModeradorView(usuarios); // Redirigir a la vista de admin/moderador
                this.dispose();
                return;
            } else if (username.equals("202010033") && pwd.equals("p1IPC1")) {
                new AdminModeradorView(usuarios); // Redirigir a la vista de admin/moderador
                this.dispose();
                return;
            }

            Usuario usuario = usuarios.iniciarSesion(username, pwd);
            if (usuario != null) {
                if (usuario.isBloqueadoSistema()) {
                    JOptionPane.showMessageDialog(this, "Usuario bloqueado.", "Error con el LOGIN", JOptionPane.ERROR_MESSAGE);
                } else {
                    new MenuUsuario(usuarios, usuario);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Código y/o contraseña incorrectos.", "Error con el LOGIN", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == registerButton) {
            new CREATE_USER(usuarios);
            this.dispose();
        }
        System.out.println("================================================");
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == passwordField && String.valueOf(passwordField.getPassword()).equals("Password")) {
            passwordField.setText("");
            passwordField.setEchoChar('\u25CF');
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == passwordField && String.valueOf(passwordField.getPassword()).isEmpty()) {
            passwordField.setText("Password");
            passwordField.setEchoChar((char) 0);
        }
    }
}

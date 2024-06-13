/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class ChatInterface extends JFrame implements ActionListener {

    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private JButton reportButton;
    private JButton backButton;
    private JButton logoutButton;
    private JTextField pathFieldA;
    private JTextField pathFieldB;
    private JButton loadButtonA;
    private JButton loadButtonB;
    private UsuarioList usuarios;
    

    public ChatInterface() {
        initComponents();
    }

    private void initComponents() {
        this.setTitle("Chat Interface");
        this.setSize(600, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBounds(10, 10, 400, 250);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setBounds(10, 10, 400, 250);
        this.add(chatScrollPane);

        // Message field
        messageField = new JTextField();
        messageField.setBounds(10, 270, 300, 25);
        this.add(messageField);

        // Send button
        sendButton = new JButton("Enviar");
        sendButton.setBounds(320, 270, 80, 25);
        sendButton.addActionListener(this);
        this.add(sendButton);

        // Report button
        reportButton = new JButton("Reportar último mensaje del otro usuario");
        reportButton.setBounds(10, 310, 250, 25);
        reportButton.addActionListener(this);
        this.add(reportButton);

        // Matrix path fields and load buttons
        JLabel pathLabelA = new JLabel("Ruta de la matriz clave A:");
        pathLabelA.setBounds(420, 10, 160, 25);
        this.add(pathLabelA);

        pathFieldA = new JTextField();
        pathFieldA.setBounds(420, 40, 160, 25);
        this.add(pathFieldA);

        loadButtonA = new JButton("Cargar");
        loadButtonA.setBounds(420, 70, 160, 25);
        loadButtonA.addActionListener(this);
        this.add(loadButtonA);

        JLabel pathLabelB = new JLabel("Ruta de la matriz clave B:");
        pathLabelB.setBounds(420, 100, 160, 25);
        this.add(pathLabelB);

        pathFieldB = new JTextField();
        pathFieldB.setBounds(420, 130, 160, 25);
        this.add(pathFieldB);

        loadButtonB = new JButton("Cargar");
        loadButtonB.setBounds(420, 160, 160, 25);
        loadButtonB.addActionListener(this);
        this.add(loadButtonB);

        // Back to menu button
        backButton = new JButton("Regresar a menú usuario");
        backButton.setBounds(420, 310, 160, 25);
        backButton.addActionListener(this);
        this.add(backButton);

        // Logout button
        logoutButton = new JButton("Log out");
        logoutButton.setBounds(420, 340, 160, 25);
        logoutButton.addActionListener(this);
        this.add(logoutButton);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == sendButton) {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                chatArea.append("Tú: " + message + "\n");
                messageField.setText("");
            }
        } else if (ae.getSource() == reportButton) {
            // Lógica para el botón de reportar
            JOptionPane.showMessageDialog(this, "Reportar último mensaje del otro usuario.");
        } else if (ae.getSource() == loadButtonA) {
            String pathA = pathFieldA.getText();
            // Lógica para cargar la matriz A desde la ruta especificada
            JOptionPane.showMessageDialog(this, "Cargar matriz A desde: " + pathA);
        } else if (ae.getSource() == loadButtonB) {
            String pathB = pathFieldB.getText();
            // Lógica para cargar la matriz B desde la ruta especificada
            JOptionPane.showMessageDialog(this, "Cargar matriz B desde: " + pathB);
        } else if (ae.getSource() == backButton) {
            new MenuUsuario();
            this.dispose();
            
        } else if (ae.getSource() == logoutButton) {
            new LOGIN(usuarios);
            this.dispose();
        }
    }

//    public static void main(String[] args) {
//        new ChatInterface();
//    }
}
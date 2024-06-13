/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class CHAT extends JFrame implements ActionListener {

    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private UsuarioList usuarios;

    public CHAT(UsuarioList usuarios) {
        this.usuarios = usuarios;
        initComponents();
    }

    private void initComponents() {
        chatArea = new JTextArea();
        chatArea.setBounds(10, 10, 460, 300);
        this.add(chatArea);

        messageField = new JTextField();
        messageField.setBounds(10, 320, 340, 30);
        this.add(messageField);

        sendButton = new JButton("Send");
        sendButton.setBounds(360, 320, 110, 30);
        sendButton.addActionListener(this);
        this.add(sendButton);

        this.setTitle("Chat");
        this.setLocationRelativeTo(null);
        this.setSize(500, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == sendButton) {
            String message = messageField.getText();
            chatArea.append("Me: " + message + "\n");
            messageField.setText("");
        }
    }
}


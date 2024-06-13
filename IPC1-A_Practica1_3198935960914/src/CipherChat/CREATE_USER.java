/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class CREATE_USER extends JFrame implements ActionListener {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField passwordField;
    private JComboBox<String> genderComboBox;
    private JTextField ageField;
    private JTextField phoneField;
    private JButton registerButton;
    private UsuarioList usuarios;

    public CREATE_USER(UsuarioList usuarios) {
        this.usuarios = usuarios;
        initComponents();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setFont(new Font("Kristen ITC", Font.BOLD, 20));
        titleLabel.setBounds(200, 10, 100, 30);
        this.add(titleLabel);

        JLabel firstNameLabel = new JLabel("Nombre:");
        firstNameLabel.setBounds(70, 50, 80, 25);
        this.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(130, 50, 260, 25);
        this.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Apellido:");
        lastNameLabel.setBounds(70, 90, 80, 25);
        this.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(130, 90, 260, 25);
        this.add(lastNameField);

        JLabel passwordLabel = new JLabel("Contrasena:");
        passwordLabel.setBounds(70, 130, 80, 25);
        this.add(passwordLabel);

        passwordField = new JTextField();
        passwordField.setBounds(130, 130, 260, 25);
        this.add(passwordField);

        JLabel genderLabel = new JLabel("Genero:");
        genderLabel.setBounds(70, 170, 80, 25);
        this.add(genderLabel);

        genderComboBox = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        genderComboBox.setBounds(130, 170, 260, 25);
        this.add(genderComboBox);

        JLabel ageLabel = new JLabel("Edad:");
        ageLabel.setBounds(70, 210, 80, 25);
        this.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(130, 210, 260, 25);
        this.add(ageField);

        JLabel phoneLabel = new JLabel("Telefono:");
        phoneLabel.setBounds(70, 250, 80, 25);
        this.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(130, 250, 260, 25);
        this.add(phoneField);

        registerButton = new JButton("Registrarse");
        registerButton.setBounds(200, 300, 100, 25);
        registerButton.addActionListener(this);
        this.add(registerButton);

        this.setTitle("Registro de Usuario");
        this.setLocationRelativeTo(null);
        this.setSize(500, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerButton) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String password = passwordField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String ageText = ageField.getText();
            
            if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty() || gender.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Algún campo obligatorio está vacío, por favor llénalo.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String phone = phoneField.getText();
            int code = usuarios.size() + 1;

            Usuario newUser = new Usuario(String.valueOf(code), password, firstName, lastName, gender, age, phone, code);
            usuarios.agregarUsuario(newUser);

            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.\nSu código de inicio\n de sesion es:  \n-- " + code + " --", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            new LOGIN(usuarios);
            this.dispose();
        }
    }
}

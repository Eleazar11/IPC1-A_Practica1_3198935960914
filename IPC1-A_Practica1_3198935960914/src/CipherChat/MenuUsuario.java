/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class MenuUsuario extends JFrame implements ActionListener {

    private JButton listaContactosButton;
    private JButton editarPerfilButton;
    private JButton cerrarSesionButton;
    private UsuarioList usuarios;
    private Usuario usuarioActual;
   

    public MenuUsuario(UsuarioList usuarios, Usuario usuarioActual) {
        this.usuarios = usuarios;
        this.usuarioActual = usuarioActual;
       
        initComponents();
    }

    public MenuUsuario() throws HeadlessException {
        initComponents();
    }
    

    private void initComponents() {
        JLabel titleLabel = new JLabel("Menu de Usuario");
        titleLabel.setFont(new Font("Kristen ITC", Font.BOLD, 20));
        titleLabel.setBounds(150, 10, 200, 30);
        this.add(titleLabel);

        listaContactosButton = new JButton("Lista de Contactos");
        listaContactosButton.setBounds(150, 60, 200, 30);
        listaContactosButton.addActionListener(this);
        this.add(listaContactosButton);

        editarPerfilButton = new JButton("Editar Perfil");
        editarPerfilButton.setBounds(150, 110, 200, 30);
        editarPerfilButton.addActionListener(this);
        this.add(editarPerfilButton);

        cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.setBounds(150, 160, 200, 30);
        cerrarSesionButton.addActionListener(this);
        this.add(cerrarSesionButton);

        this.setTitle("Menu de Usuario");
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == listaContactosButton) {
            new ContactosView(usuarios); // Redirigir a la vista de admin/moderador
                this.dispose();
        } else if (ae.getSource() == editarPerfilButton) {
            // Implementación para editar el perfil del usuario
            JOptionPane.showMessageDialog(this, "Funcionalidad para editar perfil en desarrollo.", "Editar Perfil", JOptionPane.INFORMATION_MESSAGE);
        } else if (ae.getSource() == cerrarSesionButton) {
            // Implementación para cerrar sesión
            new LOGIN(usuarios);
            this.dispose();
        }
    }

    // Método para abrir la ventana de contactos
    
}

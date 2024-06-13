/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class AdminModeradorView extends JFrame implements ActionListener {

    private JTable userTable;
    private UsuarioList usuarios;
    private JButton logoutButton;

    public AdminModeradorView(UsuarioList usuarios) {
        this.usuarios = usuarios;
        initComponents();
    }

    private void initComponents() {
        String[] columnNames = {"Código", "Nombre", "Edad", "Género", "Teléfono"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<Usuario> listaUsuarios = usuarios.getUsuarios();
        for (Usuario usuario : listaUsuarios) {
            Object[] rowData = {usuario.getCodigo(), usuario.getNombre(), usuario.getEdad(), usuario.getGenero(), usuario.getTelefono()};
            tableModel.addRow(rowData);
        }

        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(10, 10, 460, 300);
        this.add(scrollPane);

        // Botón Logout
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(480, 150, 100, 30);
        logoutButton.addActionListener(this);
        this.add(logoutButton);

        this.setLayout(null); // Usar diseño nulo para posicionar componentes manualmente
        this.setTitle("Admin/Moderador - Lista de Usuarios");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == logoutButton) {
            new LOGIN(usuarios);
            this.dispose();
        }
    }
}
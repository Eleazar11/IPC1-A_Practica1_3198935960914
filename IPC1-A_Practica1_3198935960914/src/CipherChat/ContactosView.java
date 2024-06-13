/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class ContactosView extends JFrame implements ActionListener {

    private JTable contactosTable;
    private UsuarioList usuarios;
    private JButton logoutButton;

    public ContactosView(UsuarioList usuarios) {
        this.usuarios = usuarios;
        initComponents();
    }

    private void initComponents() {
        String[] columnNames = {"Código", "Nombre", "Chat", "Eliminar"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        List<Usuario> listaUsuarios = usuarios.getUsuarios();
        for (Usuario usuario : listaUsuarios) {
            Object[] rowData = {usuario.getCodigo(), usuario.getNombre(), "Abrir", "Eliminar"};
            tableModel.addRow(rowData);
        }

        contactosTable = new JTable(tableModel);
        contactosTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        contactosTable.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
        contactosTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        contactosTable.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(contactosTable);
        scrollPane.setBounds(10, 10, 460, 300);
        this.add(scrollPane);

        // Botón Logout
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(480, 150, 100, 30);
        logoutButton.addActionListener(this);
        this.add(logoutButton);

        this.setLayout(null); // Usar diseño nulo para posicionar componentes manualmente
        this.setTitle("Contactos");
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

    // Renderer para los botones en las celdas
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Editor para los botones en las celdas
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(this);
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // Acción al presionar el botón
                new ChatInterface();
                
            }
            isPushed = false;
            return label;
        }

        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
        }
    }
}


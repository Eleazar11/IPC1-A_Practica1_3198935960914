/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class MAIN {

    static UsuarioList usuarios = new UsuarioList();
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LOGIN.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Agregar usuarios predeterminados
        usuarios.agregarUsuario(new Usuario("1", "123", "Fernanda", "Alvarez", "Masculino", 30, "1234567890", 0));
        LOGIN login = new LOGIN(usuarios);
    }
}

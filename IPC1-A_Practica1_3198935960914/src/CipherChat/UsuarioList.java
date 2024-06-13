/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.util.ArrayList;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class UsuarioList {
    private ArrayList<Usuario> usuarios;

    public UsuarioList() {
        usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario iniciarSesion(String codigo, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo().equals(codigo) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

    public int size() {
        return usuarios.size();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class Usuario {
    private String codigo;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String genero;
    private int edad;
    private String telefono;
    private int mensajes_enviados; 
    private String[] mensajesReportados;
    private int penalizaciones;
    private boolean bloqueadoSistema;
    private String[] contactos; // Lista de contactos
    private int numContactos; // Número de contactos añadidos

    public Usuario(String codigo, String contrasena, String nombre, String apellido, String genero, int edad, String telefono, int mensajes_enviados) {
        this.codigo = codigo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.edad = edad;
        this.telefono = telefono;
        this.mensajes_enviados = 0;
        this.mensajesReportados = new String[8];
        this.penalizaciones = 0;
        this.bloqueadoSistema = false;
        this.contactos = new String[15];
        this.numContactos = 0;
    }
   

    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getMensajes_enviados() {
        return mensajes_enviados;
    }

    public void setMensajes_enviados(int mensajes_enviados) {
        this.mensajes_enviados = mensajes_enviados;
    }

    public String[] getMensajesReportados() {
        return mensajesReportados;
    }

    public void setMensajesReportados(String[] mensajesReportados) {
        this.mensajesReportados = mensajesReportados;
    }

    public int getPenalizaciones() {
        return penalizaciones;
    }

    public void setPenalizaciones(int penalizaciones) {
        this.penalizaciones = penalizaciones;
    }

    public boolean isBloqueadoSistema() {
        return bloqueadoSistema;
    }

    public void setBloqueadoSistema(boolean bloqueadoSistema) {
        this.bloqueadoSistema = bloqueadoSistema;
    }

   
   
}


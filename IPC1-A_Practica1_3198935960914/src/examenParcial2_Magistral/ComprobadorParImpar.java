/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenParcial2_Magistral;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class ComprobadorParImpar {

    // Lista de valores
    private static int[] vector = {10, 5, 6, 15, 23, 0, 8, 2, 22, 43, 27, 99, 4, 1, 100};

    // Método principal para iniciar la comprobación
    public static void inicioPrograma() {
        comprobarParImpar(0); // Comenzamos la comprobación desde el primer elemento
    }

    // Método recursivo que verifica si cada elemento del vector es par o impar
    public static void comprobarParImpar(int indice) {
        // Si el índice es igual al tamaño del vector, salimos de la recursión
        if (indice == vector.length) {
            return;
        }

        // Verificamos si el valor actual es par o impar
        if (vector[indice] % 2 == 0) {
            System.out.println(vector[indice] + " es par");
        } else {
            System.out.println(vector[indice] + " es impar");
        }
        
        // Llamada recursiva al siguiente elemento del vector
        comprobarParImpar(indice + 1);
    }
}

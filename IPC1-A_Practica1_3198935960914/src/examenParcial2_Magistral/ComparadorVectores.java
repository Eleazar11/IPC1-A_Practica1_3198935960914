/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenParcial2_Magistral;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
import java.util.Scanner;

public class ComparadorVectores {
    
    public static void comparadorVectores() {
        Scanner scanner = new Scanner(System.in);

        // Solicitamos el tamaño de los vectores
        System.out.print("Ingrese el tamaño de los vectores: ");
        int tamaño = scanner.nextInt();

        // Crear los vectores, en base al parametro que ingresó en usuario
        int[] vector1 = new int[tamaño];
        int[] vector2 = new int[tamaño];

        // Ingresasamos los elementos del primer vector 
        System.out.println("Ingrese los elementos del primer vector:");
        for (int i = 0; i < tamaño; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vector1[i] = scanner.nextInt();
        }

        // Ingresasamos los elementos del segundo vector
        System.out.println("Ingrese los elementos del segundo vector:");
        for (int i = 0; i < tamaño; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vector2[i] = scanner.nextInt();
        }

        // Comparamos los vectores usando el método recursivo
        boolean iguales = sonIguales(vector1, vector2, 0);

        // Imprimimos el resultado
        if (iguales) {
            System.out.println("Los vectores son iguales.");
        } else {
            System.out.println("Los vectores no son iguales.");
        }
    }

    // Método recursivo que compara los vectores
    public static boolean sonIguales(int[] vector1, int[] vector2, int indice) {
        // Si ya hemos llegado al final de los vectores, son iguales
        if (indice == vector1.length) {
            return true;
        }

        // Si los elementos en la posición actual no son iguales, los vectores no son iguales
        if (vector1[indice] != vector2[indice]) {
            return false;
        }

        // Llamada recursiva para comparar el siguiente elemento
        return sonIguales(vector1, vector2, indice + 1);
    }
}

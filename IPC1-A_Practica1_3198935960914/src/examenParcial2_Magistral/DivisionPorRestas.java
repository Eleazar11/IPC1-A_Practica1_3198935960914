/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenParcial2_Magistral;
//tercerEnunciado
/**
 *
 * @author 3198935960914 - Eleazar Colop
 */

import java.util.Scanner;

public class DivisionPorRestas {

    public static void divisionPorRestas() {
        Scanner scanner = new Scanner(System.in);

        //Le pedimos al usuario que ingrese los dos números
        System.out.print("Ingrese el dividendo: ");
        int dividendo = scanner.nextInt();

        System.out.print("Ingrese el divisor: ");
        int divisor = scanner.nextInt();

        //Realizamos la división utilizando la función recursiva
        int resultado = dividirPorRestas(dividendo, divisor);
        System.out.println("El resultado de la división es: " + resultado);
    }

    // Función recursiva que realiza la división utilizando restas sucesivas
    public static int dividirPorRestas(int dividendo, int divisor) {
        //Si el dividendo es menor que el divisor, el resultado es 0
        if (dividendo < divisor) {
            return 0;
        }

        //Restamos el divisor del dividendo y contamos la operación
        return (1 + dividirPorRestas(dividendo - divisor, divisor));
    }
}

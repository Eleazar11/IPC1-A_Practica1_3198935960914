/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CipherChat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author 3198935960914 - Eleazar Colop
 */
public class InversaMatriz {

    private static final String DIRECTORY = ".\\Matrices\\desencriptar\\";

    public static void main(String[] args) {
        // Crear directorio si no existe
        new File(DIRECTORY).mkdirs();

        // Leer la matriz 3x3 desde un archivo
        int[][] matrizDes = leerMatriz3x3();
        if (matrizDes == null) {
            System.err.println("Error al leer la matriz.");
            return;
        }

        System.out.println("Matriz original:");
        mostrarMatrizEnteros(matrizDes);
        guardarMatriz(matrizDes, "D_Matriz_A");

        // Calcular el determinante
        int determinante = calcularDeterminante(matrizDes);
        System.out.println("Determinante: " + determinante);
        guardarDeterminante(determinante, "D_Determinante_de_A");

        if (determinante == 0) {
            System.err.println("La matriz no tiene inversa.");
            return;
        }

        // Calcular la matriz adjunta
        int[][] adjunta = calcularAdjunta(matrizDes);
        System.out.println("Matriz adjunta:");
        mostrarMatrizEnteros(adjunta);
        guardarMatriz(adjunta, "D_Matriz_Adjunta_de_A");

        // Calcular la traspuesta de la matriz adjunta
        int[][] traspuestaAdjunta = transponerMatriz(adjunta);
        System.out.println("Traspuesta de la matriz adjunta:");
        mostrarMatrizEnteros(traspuestaAdjunta);
        guardarMatriz(traspuestaAdjunta, "D_Matriz_traspuesta_de_la_adjunta_de_A");

        // Calcular la matriz inversa
        double[][] inversa = calcularInversa(matrizDes, determinante, traspuestaAdjunta);

        // Mostrar la matriz inversa
        System.out.println("Matriz inversa:");
        mostrarMatrizDecimales(inversa);
        guardarMatriz(inversa, "D_Matriz_inversa_de_A");

        // Pedir al usuario las matrices C y B
        int[][] matrizC = leerMatrizVariable();
        System.out.println("Matriz C:");
        mostrarMatrizEnteros(matrizC);
        guardarMatriz(matrizC, "D_Matriz_C");

        int[][] matrizB = leerMatrizVariable();
        System.out.println("Matriz B:");
        mostrarMatrizEnteros(matrizB);
        guardarMatriz(matrizB, "D_Matriz_B");

        // Verificar si las matrices C y B tienen el mismo tamaño
        if (matrizC.length != matrizB.length) {
            System.err.println("Las matrices C y B deben tener el mismo número de filas.");
            return;
        }

        // Restar las matrices C y B
        int[][] matrizRestaCMenosB = restarMatrices(matrizC, matrizB);

        // Mostrar la matriz resultado
        System.out.println("Matriz resultado de la resta de C y B:");
        mostrarMatrizEnteros(matrizRestaCMenosB);
        guardarMatriz(matrizRestaCMenosB, "D_Matriz_resultado_de_C_menos_B");

        // Multiplicar la matriz inversa por la matriz resultado
        double[][] matrizA1porResta = multiplicarInversaPorResta(inversa, matrizRestaCMenosB);

        // Mostrar la matriz resultado
        System.out.println("Matriz resultado de la multiplicación de la inversa por la resta de C y B:");
        mostrarMatrizAproximada(matrizA1porResta);

        // Guardar la matriz resultado
        guardarMatriz(matrizA1porResta, "D_MatrizA1porResta");
    }

    // Método para obtener la fecha y hora actuales en formato deseado
    private static String obtenerFechaHora() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return sdf.format(new Date());
    }

    // Método para leer una matriz 3x3 desde un archivo
    private static int[][] leerMatriz3x3() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la ruta del archivo que contiene la matriz 3x3: ");
        String ruta = scanner.nextLine();
        try (Scanner fileScanner = new Scanner(new File(ruta))) {
            int[][] matriz = new int[3][3];
            for (int i = 0; i < 3; i++) {
                String[] valores = fileScanner.nextLine().split(",");
                for (int j = 0; j < 3; j++) {
                    matriz[i][j] = Integer.parseInt(valores[j].trim());
                }
            }
            return matriz;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer la matriz: " + e.getMessage());
            return null;
        }
    }

    // Método para leer una matriz variable desde un archivo
    private static int[][] leerMatrizVariable() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la ruta del archivo que contiene la matriz: ");
        String ruta = scanner.nextLine();
        try {
            Scanner fileScanner = new Scanner(new File(ruta));
            int numFilas = 0;
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                numFilas++;
            }
            fileScanner.close();

            fileScanner = new Scanner(new File(ruta));
            String[] primeraLinea = fileScanner.nextLine().split(",");
            int numColumnas = primeraLinea.length;
            int[][] matriz = new int[numFilas][numColumnas];
            fileScanner.close();

            fileScanner = new Scanner(new File(ruta));
            for (int i = 0; i < numFilas; i++) {
                String[] valores = fileScanner.nextLine().split(",");
                if (valores.length != numColumnas) {
                    System.err.println("Las filas de la matriz no tienen la misma cantidad de elementos.");
                    return null;
                }
                for (int j = 0; j < numColumnas; j++) {
                    matriz[i][j] = Integer.parseInt(valores[j].trim());
                }
            }
            return matriz;
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer la matriz: " + e.getMessage());
            return null;
        }
    }

    // Método para restar dos matrices del mismo tamaño
    private static int[][] restarMatrices(int[][] matrizC, int[][] matrizB) {
        int numFilas = matrizC.length;
        int numColumnas = matrizC[0].length;
        int[][] resultado = new int[numFilas][numColumnas];
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                resultado[i][j] = matrizC[i][j] - matrizB[i][j];
            }
        }
        return resultado;
    }

    // Método para multiplicar la matriz inversa por la matriz resultado de la resta de C y B
    private static double[][] multiplicarInversaPorResta(double[][] inversa, int[][] matrizRestaCMenosB) {
        int numFilasInversa = inversa.length;
        int numColumnasResta = matrizRestaCMenosB[0].length;
        double[][] resultado = new double[numFilasInversa][numColumnasResta];
        for (int i = 0; i < numFilasInversa; i++) {
            for (int j = 0; j < numColumnasResta; j++) {
                for (int k = 0; k < matrizRestaCMenosB.length; k++) {
                    resultado[i][j] += inversa[i][k] * matrizRestaCMenosB[k][j];
                }
            }
        }
        return resultado;
    }

    // Método para mostrar una matriz en pantalla (enteros)
    private static void mostrarMatrizEnteros(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int j = 0; j < fila.length; j++) {
                System.out.print(fila[j]);
                if (j < fila.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    // Método para mostrar una matriz en pantalla (decimales)
    private static void mostrarMatrizDecimales(double[][] matriz) {
        for (double[] fila : matriz) {
            for (int j = 0; j < fila.length; j++) {
                System.out.printf("%.6f", fila[j]); // Ajustar el formato a 6 decimales
                if (j < fila.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    // Método para mostrar una matriz en pantalla (enteros aproximados)
    private static void mostrarMatrizAproximada(double[][] matriz) {
        for (double[] fila : matriz) {
            for (int j = 0; j < fila.length; j++) {
                System.out.print((int) Math.round(fila[j])); // Redondear y convertir a entero
                if (j < fila.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    // Método para guardar una matriz de tipo int en un archivo
    private static void guardarMatriz(int[][] matriz, String nombreBase) {
        String nombreArchivo = nombreBase + "_" + obtenerFechaHora() + ".txt";
        try (FileWriter writer = new FileWriter(DIRECTORY + nombreArchivo)) {
            for (int[] fila : matriz) {
                for (int j = 0; j < fila.length; j++) {
                    writer.write(Integer.toString(fila[j]));
                    if (j < fila.length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
            System.out.println("Matriz guardada en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Método para guardar una matriz de tipo double en un archivo
    private static void guardarMatriz(double[][] matriz, String nombreBase) {
        String nombreArchivo = nombreBase + "_" + obtenerFechaHora() + ".txt";
        try (FileWriter writer = new FileWriter(DIRECTORY + nombreArchivo)) {
            for (double[] fila : matriz) {
                for (int j = 0; j < fila.length; j++) {
                    writer.write(Double.toString(fila[j]));
                    if (j < fila.length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
            System.out.println("Matriz guardada en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Método para guardar el determinante en un archivo
    private static void guardarDeterminante(int determinante, String nombreBase) {
        String nombreArchivo = nombreBase + "_" + obtenerFechaHora() + ".txt";
        try (FileWriter writer = new FileWriter(DIRECTORY + nombreArchivo)) {
            writer.write("Determinante: " + determinante + "\n");
            System.out.println("Determinante guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el determinante: " + e.getMessage());
        }
    }

    // Método para calcular el determinante de una matriz 3x3
    private static int calcularDeterminante(int[][] matriz) {
        return matriz[0][0] * (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1])
                - matriz[0][1] * (matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0])
                + matriz[0][2] * (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]);
    }

    // Método para calcular la matriz adjunta de una matriz 3x3
    private static int[][] calcularAdjunta(int[][] matriz) {
        int[][] adjunta = new int[3][3];

        adjunta[0][0] = (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1]);
        adjunta[0][1] = -(matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0]);
        adjunta[0][2] = (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]);

        adjunta[1][0] = -(matriz[0][1] * matriz[2][2] - matriz[0][2] * matriz[2][1]);
        adjunta[1][1] = (matriz[0][0] * matriz[2][2] - matriz[0][2] * matriz[2][0]);
        adjunta[1][2] = -(matriz[0][0] * matriz[2][1] - matriz[0][1] * matriz[2][0]);

        adjunta[2][0] = (matriz[0][1] * matriz[1][2] - matriz[0][2] * matriz[1][1]);
        adjunta[2][1] = -(matriz[0][0] * matriz[1][2] - matriz[0][2] * matriz[1][0]);
        adjunta[2][2] = (matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0]);

        return adjunta;
    }

    // Método para calcular la traspuesta de una matriz 3x3
    private static int[][] transponerMatriz(int[][] matriz) {
        int[][] transpuesta = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                transpuesta[i][j] = matriz[j][i];
            }
        }
        return transpuesta;
    }

    // Método para calcular la matriz inversa de una matriz 3x3
    private static double[][] calcularInversa(int[][] matriz, int determinante, int[][] traspuestaAdjunta) {
        double[][] inversa = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inversa[i][j] = (double) traspuestaAdjunta[i][j] / determinante;
            }
        }
        return inversa;
    }
}
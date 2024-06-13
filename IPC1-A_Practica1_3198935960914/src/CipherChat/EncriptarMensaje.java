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
public class EncriptarMensaje {

    public static void main(String[] args) {
        // Tabla de conversión
        String[] tabla = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " "};

        // Leer el mensaje a encriptar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el mensaje: ");
        String mensaje = scanner.nextLine().toUpperCase();

        // Obtener la primera palabra del mensaje
        String primerPalabra = mensaje.split(" ")[0];

        // Calcular el número de columnas
        int longitud = mensaje.length();
        int columnas = (int) Math.ceil((double) longitud / 3.0);

        // Crear la matriz 3xN
        char[][] matrizEnc = new char[3][columnas];
        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < 3; i++) {
                int index = j * 3 + i;
                if (index < longitud) {
                    matrizEnc[i][j] = mensaje.charAt(index);
                } else {
                    matrizEnc[i][j] = ' ';  // Rellenar con espacios si es necesario
                }
            }
        }

        // Imprimir la matriz de caracteres en orden vertical
        System.out.println("Matriz formada:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("\"" + matrizEnc[i][j] + "\"");
                if (j < columnas - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }

        // Crear y llenar la matriz de números
        int[][] matrizNumeros = new int[3][columnas];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizNumeros[i][j] = obtenerValor(tabla, matrizEnc[i][j]);
            }
        }

        // Imprimir la matriz de números en orden vertical
        System.out.println("Matriz de números:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matrizNumeros[i][j]);
                if (j < columnas - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }

        // Obtener fecha y hora actual
        String fechaHora = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Guardar el mensaje original en un archivo
        String nombreArchivoMensaje = ".\\Matrices\\encriptar\\E_Mensaje_" + primerPalabra + "_" + fechaHora + ".txt";
        crearDirectorioSiNoExiste(".\\Matrices\\encriptar");
        guardarMensajeEnArchivo(mensaje, nombreArchivoMensaje);

        // Guardar la matriz de números en un archivo
        String nombreArchivoMatrizNumeros = ".\\Matrices\\encriptar\\E_Mensaje_codificado_" + fechaHora + ".txt";
        guardarMatrizEnArchivo(matrizNumeros, nombreArchivoMatrizNumeros);

        // Leer matrices A y B
        int[][] matrizAENC = leerMatriz("A");
        int[][] matrizBENC = leerMatriz("B");
        if (matrizAENC != null && matrizBENC != null) {
            System.out.println("Matrices A y B cargadas con éxito");
            System.out.println("Matriz A:");
            mostrarMatriz(matrizAENC);
            System.out.println("Matriz B:");
            mostrarMatriz(matrizBENC);

            // Guardar las matrices A y B en archivos
            guardarMatrizEnArchivo(matrizAENC, ".\\Matrices\\encriptar\\E_Matriz_A_" + fechaHora + ".txt");
            guardarMatrizEnArchivo(matrizBENC, ".\\Matrices\\encriptar\\E_Matriz_B_" + fechaHora + ".txt");

            // Calcular la matriz C
            int[][] matrizCENC = calcularMatrizC(matrizAENC, matrizNumeros, matrizBENC);
            System.out.println("Matriz C:");
            mostrarMatriz(matrizCENC);

            // Guardar la matriz C en un archivo
            guardarMatrizEnArchivo(matrizCENC, ".\\Matrices\\encriptar\\E_Matriz_C_" + fechaHora + ".txt");

            // Guardar la matriz M (matrizNumeros) en un archivo
            guardarMatrizEnArchivo(matrizNumeros, ".\\Matrices\\encriptar\\E_Matriz_M_" + fechaHora + ".txt");

            // Guardar el resultado de A por M en un archivo
            int[][] matrizAxM = multiplicarMatrices(matrizAENC, matrizNumeros);
            guardarMatrizEnArchivo(matrizAxM, ".\\Matrices\\encriptar\\E_Matriz_resultado_A_por_M_" + fechaHora + ".txt");
        }
    }

    // Función para obtener el valor encriptado del caracter
    private static int obtenerValor(String[] tabla, char caracter) {
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i].charAt(0) == caracter) {
                return i;
            }
        }
        return 27; // Si el caracter no está en la tabla, se considera como espacio
    }

    // Método para leer una matriz desde un archivo
    private static int[][] leerMatriz(String nombreMatriz) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la ruta de la matriz " + nombreMatriz + ": ");
        String ruta = scanner.nextLine();
        try (Scanner fileScanner = new Scanner(new File(ruta))) {
            int[][] matriz = new int[3][];
            int fila = 0;
            while (fileScanner.hasNextLine() && fila < 3) {
                String[] valores = fileScanner.nextLine().split(",");
                matriz[fila] = new int[valores.length];
                for (int columna = 0; columna < valores.length; columna++) {
                    matriz[fila][columna] = Integer.parseInt(valores[columna].trim());
                }
                fila++;
            }
            System.out.println("Matriz " + nombreMatriz + " cargada con éxito");
            return matriz;
        } catch (IOException e) {
            System.err.println("Error al leer la matriz " + nombreMatriz + ": " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir los números de la matriz " + nombreMatriz + ": " + e.getMessage());
            return null;
        }
    }

    // Método para mostrar una matriz en pantalla
    private static void mostrarMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int j = 0; j < fila.length; j++) {
                System.out.print(fila[j]);
                if (j < fila.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }

    // Método para calcular la matriz C = A * M + B
    private static int[][] calcularMatrizC(int[][] A, int[][] M, int[][] B) {
        int filasA = A.length;
        int columnasA = A[0].length;
        int columnasM = M[0].length;

        if (columnasA != M.length) {
            throw new IllegalArgumentException("El número de columnas de A debe ser igual al número de filas de M.");
        }

        int[][] C = new int[filasA][columnasM];

        // Multiplicar A por M
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasM; j++) {
                C[i][j] = 0;
                for (int k = 0; k < columnasA; k++) {
                    C[i][j] += A[i][k] * M[k][j];
                }
                // Sumar la matriz B
                C[i][j] += B[i][j];
            }
        }
        return C;
    }

    // Método para multiplicar dos matrices
    private static int[][] multiplicarMatrices(int[][] A, int[][] M) {
        int filasA = A.length;
        int columnasA = A[0].length;
        int columnasM = M[0].length;

        int[][] resultado = new int[filasA][columnasM];

        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasM; j++) {
                resultado[i][j] = 0;
                for (int k = 0; k < columnasA; k++) {
                    resultado[i][j] += A[i][k] * M[k][j];
                }
            }
        }
        return resultado;
    }

    // Método para guardar un mensaje en un archivo
    private static void guardarMensajeEnArchivo(String mensaje, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(mensaje);
            System.out.println("Mensaje guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el mensaje: " + e.getMessage());
        }
    }

    // Método para guardar una matriz en un archivo
    private static void guardarMatrizEnArchivo(int[][] matriz, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
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

    // Método para crear un directorio si no existe
    private static void crearDirectorioSiNoExiste(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado: " + rutaDirectorio);
            } else {
                System.err.println("Error al crear el directorio: " + rutaDirectorio);
            }
        }
    }
}

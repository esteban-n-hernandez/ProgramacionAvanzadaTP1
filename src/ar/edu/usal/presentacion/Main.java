package ar.edu.usal.presentacion;

import ar.edu.usal.negocio.GestorConstrucciones;

import java.util.Scanner;


/*
 * Comienza con el menu principal.
 * Hay 4 opciones: Crear, Listar construcciones, Listar el contenido de alguna construccion, Salir. .
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            int opcion = validarInputEntero(scan);

            switch (opcion) {
                case 1:
                    MenuConstrucciones.crearConstruccion();
                    break;
                case 2:
                    MenuConstrucciones.listarConstruciones();
                    break;
                case 3:
                    MenuConstrucciones.cargarMenuListarContenido();
                    break;
                case 4:
                    GestorConstrucciones.obtenerPrecioConstrucciones();
                    break;
                case 5:
                    System.out.println("******* Proceso Finalizado *******");
                    continuar = false; // Salir del bucle while
                    break;
                default:
                    System.out.println("La opción no se encuentra disponible.");
            }
        }
    }

    private static int validarInputEntero(Scanner scan) {
        int number;
        do {
            System.out.println("**************************");
            System.out.println("1. Crear construcción \n2. Listar construcciones creadas \n3. Listar información de una construcción \n4. Obtener precios de las construcciones \n5. Salir");
            System.out.println("**************************");
            while (!scan.hasNextInt()) {
                System.out.println("Input incorrecto. Debe ingresar un número.");
                scan.next();
            }
            number = scan.nextInt();
        } while (number <= 0);
        return number;
    }

}

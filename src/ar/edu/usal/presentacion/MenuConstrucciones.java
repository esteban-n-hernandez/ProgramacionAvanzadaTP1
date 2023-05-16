package ar.edu.usal.presentacion;

import ar.edu.usal.negocio.GestorConstrucciones;

import java.util.ArrayList;
import java.util.Scanner;

import static ar.edu.usal.utils.ArchivosUtils.crearFile;

public class MenuConstrucciones {

    public static void crearConstruccion() throws Exception {

        //Inicializo el array que va a tener las construcciones hechas para poder crear el file.
        ArrayList<Object> construccionesList = new ArrayList<>();

        //Inicializo el scanner para poder tener los inputs del teclado.
        Scanner scan = new Scanner(System.in);

        //Llamo al metodo para poder comenzar a crear los edificios y las casas.
        menuCrear(construccionesList, scan);
    }

    private static void menuCrear(ArrayList<Object> construccionesList, Scanner scan) throws Exception {
        int number = validarInputEntero(scan);
        switch (number) {
            case 1:
                GestorConstrucciones.crearEdificio(construccionesList);
                menuCrear(construccionesList, scan);
                break;
            case 2:
                GestorConstrucciones.crearCasa(construccionesList);
                menuCrear(construccionesList, scan);
                break;
            case 3:
                GestorConstrucciones.crearArchivoTxt(construccionesList);
                break;
            default:
                System.out.println("Opcion incorrecta. Debe ingresar un numero del listado.");
                menuCrear(construccionesList, scan);
                break;
        }
    }

    /*
     * Busca el la carpeta del proyecto los archivos .txt creados. Si encuentra, va haciendo un sysout
     */
    public static void listarConstruciones() {
        GestorConstrucciones.listarConstruciones();
    }

    public static void cargarMenuListarContenido() {
        Scanner scan = new Scanner(System.in);
        String nombreArchivo = "";

        while (nombreArchivo.isEmpty()) {
            System.out.println("Ingresar el nombre de la construccion y la fecha que desea buscar. \nEl formato es construc_ddMMyyyy. Por ejemplo CasaNorte_01012023.");
            nombreArchivo = scan.nextLine();
            if (nombreArchivo.isEmpty()) {
                System.out.println("Input inválido. Debe ingresar un texto.");
            }
        }
        GestorConstrucciones.leerContenidoArchivosTxt(nombreArchivo);
    }

    /**
     * Validar que el input no sea nulo ni un caracter.
     *
     * @param scan
     * @return number
     */
    private static int validarInputEntero(Scanner scan) {
        int number;
        do {
            System.out.println("¿Que desea agregar? \n 1. Edificio \n 2. Casa \n 3. Registrar construccion.");
            while (!scan.hasNextInt()) {
                System.out.println("Input incorrecto. Debe ingresar un numero.");
                scan.next();
            }
            number = scan.nextInt();
        } while (number <= 0);
        return number;
    }
}

package ar.edu.usal.negocio;

import ar.edu.usal.persistencia.Casas;
import ar.edu.usal.persistencia.Edificios;
import ar.edu.usal.utils.DateUtils;

import java.io.*;
import java.util.*;


public class GestorConstrucciones {

    /**
     * Crea un nuevo edificio y lo agrega a la lista de construcciones.
     *
     * @param construccionesList
     */
    public static void crearEdificio(ArrayList<Object> construccionesList) {
        Edificios edificio = GestorEdificios.cargarNuevoEdificio();
        construccionesList.add(edificio);
    }

    /**
     * Crea una nueva casa y lo agrega a la lista de construcciones.
     *
     * @param construccionesList
     */
    public static void crearCasa(ArrayList<Object> construccionesList) throws Exception {
        Casas casa = GestorCasas.cargarNuevaCasa();
        construccionesList.add(casa);
    }

    /*
     * Busca el la carpeta del proyecto los archivos .txt creados. Si encuentra, va haciendo un sysout
     */
    public static void listarConstruciones() {
        System.out.println("Construciones registradas: ");
        File carpeta = new File(System.getProperty("user.dir") + "/construcciones");
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".txt")) {
                    System.out.println(". " + archivo.getName().replace(".txt", ""));
                }
            }
        }
    }

    public static void leerContenidoArchivosTxt(String nombreConstruccion) {
        try (BufferedReader reader = new BufferedReader(new FileReader("construcciones/" + nombreConstruccion + ".txt"))) {
            String linea;
            System.out.println("Leyendo contenido de: " + nombreConstruccion);
            while ((linea = reader.readLine()) != null) {

                String[] fields = linea.split(", ");

                System.out.println("Superficie: " + fields[0].split(": ")[1] +
                        ", Precio: " + fields[1].split(": ")[1] +
                        ", Dirección: " + fields[2].split(": ")[1] +
                        ", Unidades: " + fields[3].split(": ")[1] +
                        ", Pisos: " + fields[4].split(": ")[1]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }


    /**
     * Obtener los precios de cada construccion.
     */
    public static void obtenerPrecioConstrucciones() {
        File folder = new File(System.getProperty("user.dir") + "/construcciones");
        File[] files = folder.listFiles();

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingresar rangos de precio: Desde");
        int precioDesde = scan.nextInt();
        System.out.println("Hasta: ");
        int precioHasta = scan.nextInt();

        Scanner scanner = null;
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("Archivo: " + file.getName());
                    try {
                        scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();

                            String[] fields = line.split(", ");
                            double precio = Double.parseDouble(fields[1].split(": ")[1]);

                            if (precio >= precioDesde && precio <= precioHasta) {
                                double precioConIVA = precio * 1.21; // Sumar el 21% al precio

                                // Imprimir la información de la construcción con el precio actualizado
                                System.out.println("Superficie: " + fields[0].split(": ")[1] +
                                        ", Precio (sin IVA): " + precio +
                                        ", Precio (con IVA): " + precioConIVA +
                                        ", Dirección: " + fields[2].split(": ")[1]);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("No se pudo encontrar el archivo: " + file.getName());
                    }
                }
            }
        }
    }

    /**
     * Crear un archivo. Recibe como parametro la lista de las casas y edificios construidos.
     *
     * @param construccion
     */
    public static void crearArchivoTxt(List<Object> construccion) {

        //Valido que la lista no este vacia. En caso de que este vacia, Tira un sysout con el mensaje de que no hay info cargada.

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresar el nombre del archivo.");
        String fileName = scanner.next() + "_" + DateUtils.formatFecha();

        try (FileWriter writer = new FileWriter("construcciones/" + fileName + ".txt")) {
            // Escribe cada objeto de la lista en una línea del archivo
            for (Object object : construccion) {
                if (object instanceof Casas) {
                    Casas d = (Casas) object;
                    writer.write(d + System.lineSeparator());
                } else {
                    Edificios d = (Edificios) object;
                    writer.write(d.toString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}

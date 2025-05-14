package co.edu.unbosque.model.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase ArchivoManager: Maneja la lectura y escritura de datos en archivos.
 * Permite la persistencia de la información de la aplicación.
 */
public class ArchivoManager {

    private String nombreArchivo; // Nombre del archivo a manejar

    /**
     * Constructor de la clase ArchivoManager.
     *
     * @param nombreArchivo El nombre del archivo.
     */
    public ArchivoManager(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Escribe una lista de cadenas de texto en el archivo.
     *
     * @param datos La lista de cadenas a escribir.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void escribirDatos(List<String> datos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String dato : datos) {
                bw.write(dato);
                bw.newLine();
            }
        }
    }

    /**
     * Lee los datos del archivo y los devuelve como una lista de cadenas.
     *
     * @return Una lista de cadenas con los datos del archivo.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public List<String> leerDatos() throws IOException {
        List<String> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                datos.add(linea);
            }
        }
        return datos;
    }

    /**
     * Escribe un objeto en el archivo (serialización).
     *
     * @param objeto El objeto a escribir.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void escribirObjeto(Object objeto) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(objeto);
        }
    }

    /**
     * Lee un objeto del archivo (deserialización).
     *
     * @return El objeto leído del archivo.
     * @throws IOException            Si ocurre un error de entrada/salida.
     * @throws ClassNotFoundException Si no se encuentra la clase del objeto.
     */
    public Object leerObjeto() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return ois.readObject();
        }
    }
}
package co.edu.unbosque.model.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ArchivoManager {

    private String nombreArchivo; // Nombre del archivo a manejar


    public ArchivoManager(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void escribirDatos(List<String> datos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (String dato : datos) {
                bw.write(dato);
                bw.newLine();
            }
        }
    }

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

    public void escribirObjeto(Object objeto) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(objeto);
        }
    }

    public Object leerObjeto() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return ois.readObject();
        }
    }
}
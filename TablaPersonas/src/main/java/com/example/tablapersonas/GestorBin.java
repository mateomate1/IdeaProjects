package com.example.tablapersonas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorBin <T>{
    private static final Logger log = LoggerFactory.getLogger(GestorBin.class);

    private String rutaArchivo;
    private File archivo;

    public GestorBin (String rutaArchivo){
        this.rutaArchivo = rutaArchivo;
        archivo = new File(rutaArchivo);
        if(!archivo.exists()) {
            log.debug("El archivo no existe");
            try {
                log.debug("Creando archivo");
                archivo.createNewFile();
                log.debug("Archivo creado con exito");
            } catch (IOException e) {
                log.error("Error creando el archivo");
            }
        }
    }

    public List<T> leer(String rutaArchivo) {
        List<T> datos = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            try {
                while (true) {
                    T data = (T) ois.readObject();
                    datos.add(data);
                }
            } catch (EOFException e) {
                // Fin del archivo alcanzado
            } catch (ClassNotFoundException e) {
                System.err.println("Error al deserializar el objeto: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.err.println("El archivo no existe: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return datos;
    }

    public void add(T data){
        List<T> datos = leer();
        datos.add(data);
    }

}

package com.dam.di.gestion.gestiondeservicios;

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

    public List<T> leer() {
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
                log.error("Error al deserializar el objeto: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            log.error("El archivo no existe: " + e.getMessage());
        } catch (IOException e) {
            log.error("Error al leer el archivo: " + e.getMessage());
        }

        return datos;
    }

    public void escribir(List<T> datos) {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (T data : datos) {
                oos.writeObject(data);
            }
            log.debug("Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            log.error("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void add(T data){
        List<T> datos = leer();
        datos.add(data);
        escribir(datos);
    }

    public boolean eliminarPorPosicion(int posicion){
        List<T> datos = leer();

        if(posicion < 0 || posicion >= datos.size()){
            log.warn("Posición fuera de rango: " + posicion);
            return false;
        }
        datos.remove(posicion);
        escribir(datos);
        return true;
    }

    public void vaciar(){
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            log.debug("El archivo ha sido vaciado correctamente.");
        } catch (IOException e) {
            log.error("Error al vaciar el archivo: {}", e.getMessage());
        }
    }

}

package com.dam.di.gestion.gestiondeservicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorBin<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GestorBin.class);
    private final String rutaArchivo;

    public GestorBin(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        verifyFile();
    }

    private void verifyFile() {
        File archivo = new File(rutaArchivo);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                LOGGER.info("Archivo creado: {}", rutaArchivo);
            }
        } catch (IOException e) {
            LOGGER.error("Error al crear el archivo: {}", e.getMessage(), e);
        }
    }

    public void add(T data) {
        List<T> items = leer();
        items.add(data);
        escribir(items);
    }

    public List<T> leer() {
        List<T> items = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            items = (List<T>) ois.readObject();

        } catch (EOFException e) {
            // Fin del archivo alcanzado
        } catch (FileNotFoundException e) {
            LOGGER.warn("El archivo no existe: {}", e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Error al leer el archivo: {}", e.getMessage(), e);
        }

        return items;
    }

    public void escribir(List<T> data) {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
            LOGGER.info("Datos escritos en el archivo correctamente.");
        } catch (IOException e) {
            LOGGER.error("Error al escribir en el archivo: {}", e.getMessage(), e);
        }
    }

    public boolean eliminarPorPosicion(int posicion) {
        List<T> items = leer();

        if (posicion < 0 || posicion >= items.size()) {
            LOGGER.warn("Posición fuera de rango: {}", posicion);
            return false;
        }

        items.remove(posicion);
        escribir(items);
        return true;
    }

    public void vaciar() {
        escribir(new ArrayList<>());
        LOGGER.info("El archivo ha sido vaciado correctamente.");
    }
}

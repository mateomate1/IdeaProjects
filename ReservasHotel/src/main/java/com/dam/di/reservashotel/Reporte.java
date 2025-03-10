package com.dam.di.reservashotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import win.zqxu.jrviewer.JRViewerFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase Reporte
 */
public class Reporte {
    /**
     * Definicion del atributo print, una instancia de JasperPrint para poder generar el PDF
     */
    static JasperPrint print = null;

    /**
     * Metodo principal
     *
     * @param args
     */
    public static void main(String[] args) {

        Set<Habitacion> hotel = getHabitaciones();

        try {
            File fichero = new File("./informes/Reservas.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(hotel);

            HashMap<String, Object> parametetros = new HashMap<>();

            parametetros.put("RUTA_IMAGEN", "./informes/imagen.png");

            print = JasperFillManager.fillReport(informe,parametetros,coleccion);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo getNumpersonas
     * Devuelve la coleccion hotel ya rellena
     *
     * @return hotel
     */
    private static Set<Habitacion> getHabitaciones() {
        Reserva r1 = new Reserva(2, "Mario Garcia", 15);
        Reserva r2 = new Reserva(1, "Cristiano Ronaldo", 2);
        Reserva r3 = new Reserva(10, "Marco Aurelio", 4);
        Reserva r4 = new Reserva(6, "Nicki Minaj", 7);
        Reserva r5 = new Reserva(4, "Felipe VI", 1);

        Habitacion h1 = new Habitacion(101, 50.0, r1);
        Habitacion h2 = new Habitacion(102, 85.0, r2);
        Habitacion h3 = new Habitacion(103, 55.0, r3);
        Habitacion h4 = new Habitacion(104, 70.0, r4);
        Habitacion h5 = new Habitacion(105, 150.0, r5);

        Set<Habitacion> hotel = new HashSet<>();

        hotel.add(h1);
        hotel.add(h2);
        hotel.add(h3);
        hotel.add(h4);
        hotel.add(h5);
        return hotel;
    }

    /**
     * Metodo exportarInforme
     * Exporta el informe
     *
     * @param ruta ruta donde guardar el PDF del informe
     */
    public void exportarInforme(String ruta){
        try {
            File reportFile = new File("informes/informeHotel.pdf");
            //File reportFile = new File(ruta);
            OutputStream output = new FileOutputStream(reportFile);
            JasperExportManager.exportReportToPdfStream(print, output);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo exportarInforme
     * Muestra el informe
     *
     * @param pane
     */
    public void mostrarInforme(Pane pane){
        JRViewerFX visor = new JRViewerFX(print);
        pane.getChildren().add(visor);
    }

}

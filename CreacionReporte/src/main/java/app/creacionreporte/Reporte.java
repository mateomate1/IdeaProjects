package app.creacionreporte;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import win.zqxu.jrviewer.JRViewerFX;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Reporte {
    JasperPrint print = null;
    JasperViewer visor = null;
    public void generarInforme(Set<Habitacion> hotel) {
        /*
        Reserva r1 = new Reserva(2, "Juan Perez", 3);
        Reserva r2 = new Reserva(1, "Maria Gomez", 2);
        Reserva r3 = new Reserva(3, "Carlos Ruiz", 4);
        Reserva r4 = new Reserva(2, "Ana Lopez", 1);
        Reserva r5 = new Reserva(4, "Luis Fernandez", 5);

        Habitacion h1 = new Habitacion(101, 50.0, r1);
        Habitacion h2 = new Habitacion(102, 60.0, r2);
        Habitacion h3 = new Habitacion(103, 55.0, r3);
        Habitacion h4 = new Habitacion(104, 70.0, r4);
        Habitacion h5 = new Habitacion(105, 65.0, r5);

        Set<Habitacion> hotel = new HashSet<>();

        hotel.add(h1);
        hotel.add(h2);
        hotel.add(h3);
        hotel.add(h4);
        hotel.add(h5);
        */

        try {
            File fichero = new File("./informes/Habitaciones.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(hotel);

            HashMap<String, Object> parametetros = new HashMap<>();

            parametetros.put("RUTA_IMAGEN", "./informes/hotel.png");

            print = JasperFillManager.fillReport(informe,parametetros,coleccion);
            visor = new JasperViewer(print, false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarInforme(Set<Habitacion> hotel, String rutaPDF){
        try {
            File fichero = new File("./informes/Habitaciones.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(hotel);

            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./informes/hotel.png");

            print = JasperFillManager.fillReport(informe, parametros, coleccion);
            visor = new JasperViewer(print, false);

            JasperExportManager.exportReportToPdfFile(print, rutaPDF);

            System.out.println("Informe guardado en: " + rutaPDF);
        } catch (JRException e) {
            throw new RuntimeException("Error al generar o guardar el informe", e);
        }

    }

    public void mostrarInforme(Set<Habitacion> hotel){
        try {
            File fichero = new File("./informes/Habitaciones.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(hotel);

            HashMap<String, Object> parametetros = new HashMap<>();

            parametetros.put("RUTA_IMAGEN", "./informes/hotel.png");

            print = JasperFillManager.fillReport(informe,parametetros,coleccion);
            visor = new JasperViewer(print, false);
            visor.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}

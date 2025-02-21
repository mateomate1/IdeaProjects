package org.example.informereservas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import win.zqxu.jrviewer.JRViewerFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Alvaro Morales Moreno
 * @version 1
 * @since 17/02/2025
 * Clase controladora para gestionar la generacion del informe y el guardado en formato pdf.
 */
public class HelloController implements Initializable {
    @FXML
    private TableView<Habitacion> tableView;
    @FXML
    private TableColumn<Habitacion, Integer> colNumero;
    @FXML
    private TableColumn<Habitacion, Double> colPrecio;
    @FXML
    private TableColumn<Habitacion, String> colHuesped;
    @FXML
    private TableColumn<Habitacion, Integer> colDuracion;
    @FXML
    private Button btnViewReport;
    @FXML
    private Button btnSaveReport;
    @FXML
    private Pane panelInforme;

    private ObservableList<Habitacion> habitacionesList;

    /**
     * Metodo que inicializa una lista con habitaciones y sus respectivos datos.
     */
    private void inicializarDatos() {
        List<Habitacion> habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion(101, 300.0, new Reserva(2, "Alvaro Morales", 5)));
        habitaciones.add(new Habitacion(102, 900.0, new Reserva(5, "Lionel Andrés Messi", 7)));
        habitaciones.add(new Habitacion(103, 100.0, new Reserva(4, "Ivan Fernandez", 4)));
        habitaciones.add(new Habitacion(104, 80.0, new Reserva(3, "Guillermo Peinado", 2)));
        habitaciones.add(new Habitacion(105, 70.0, new Reserva(1, "Arturo Gomez", 1)));

        habitacionesList = FXCollections.observableArrayList(habitaciones);
    }

    /**
     * Metodo que configura la tabla con los valores de las habitaciones.
     */
    private void configurarTabla() {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colHuesped.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createStringBinding(() -> cellData.getValue().getReserva().getHuespedPrincipal()));
        colDuracion.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue().getReserva().getDuracion()));

        tableView.setItems(habitacionesList);
    }

    /**
     * Metodo que genera un informe y lo muestra en el panel de informes.
     */
    @FXML
    private void generarInforme() {
        try {
            File fichero = new File("./Informes/EntregaHotelAlvaro2.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(habitacionesList);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./Informes/img.png");
            JasperPrint print = JasperFillManager.fillReport(informe, parametros, coleccion);
            JRViewerFX visor = new JRViewerFX(print);
            panelInforme.getChildren().add(visor);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que guarda el informe generado en formato pdf.
     */
    @FXML
    private void guardarInforme() {
        try {
            File fichero = new File("./Informes/EntregaHotelAlvaro2.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(habitacionesList);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./Informes/img.png");
            JasperPrint print = JasperFillManager.fillReport(informe, parametros, coleccion);

            File reportFile = new File("Informes/EntregaHotelAlvaro2.pdf");
            OutputStream output = new FileOutputStream(reportFile);
            JasperExportManager.exportReportToPdfStream(print, output);
        } catch (FileNotFoundException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que inicializa la clase configurando los datos y la tabla.
     *
     * @param url URL de inicialización.
     * @param resourceBundle Recursos utilizados para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarDatos();
        configurarTabla();
    }

    /*
        -- module info:
            requires javafx.swing;
            requires net.sf.jasperreports.core;
            requires jrviewer.fx;
            requires java.sql;

        --
        net.sf.jasperreports 7.0.1
        net.sf.jasperreports.charts 7.0.0
        openjfx.javafx.swing 17
        win.zqxu.jrviewer.fx 0.1.1

     */
}

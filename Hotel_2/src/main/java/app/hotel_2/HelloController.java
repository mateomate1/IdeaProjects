package app.hotel_2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import win.zqxu.jrviewer.JRViewerFX;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class HelloController implements Initializable {

    Set<Habitacion> hotel = new HashSet<>();

    JasperPrint print = new JasperPrint();
    JasperViewer visor = new JasperViewer(print);

    @FXML public Label label;
    @FXML public Pane panel;

    @FXML
    public void btnGenerar(ActionEvent actionEvent){
        try {
            File fichero = new File("./informes/Habitaciones.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(hotel, false);

            HashMap<String, Object> parametetros = new HashMap<>();

            parametetros.put("RUTA_IMAGEN", "./informes/hotel.png");

            print = JasperFillManager.fillReport(informe,parametetros,coleccion);
            visor = new JasperViewer(print, false);
            visor.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error generando el reporte");
            alert.setContentText("");
            alert.showAndWait();
        }
    }
    @FXML
    public void btnExportar(ActionEvent actionEvent){
        try{
            String pathname = "./informes/informesHotel.pdf";
            File reportFile = new File(pathname);
            OutputStream output = new FileOutputStream(reportFile);
            JasperExportManager.exportReportToPdfStream(print, output);
            JasperExportManager.exportReportToPdfFile(print, pathname);
            visor.setVisible(true);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error exportando el reporte");
            alert.setContentText("Hubicacion del archivo no encontrada no encontrado");
            alert.showAndWait();
        } catch (JRException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error exportando el reporte");
            alert.setContentText("Error con el JasperReport");
            alert.showAndWait();
        }
    }
    @FXML
    public void btnMostrar(ActionEvent actionEvent){
        JRViewerFX visor = new JRViewerFX(print);
        panel.getChildren().add(visor);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        hotel.add(h1);
        hotel.add(h2);
        hotel.add(h3);
        hotel.add(h4);
        hotel.add(h5);
    }
}
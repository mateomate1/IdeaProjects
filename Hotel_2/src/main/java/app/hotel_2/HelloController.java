package app.hotel_2;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import win.zqxu.jrviewer.JRViewerFX;

/**
 * Controlador principal de la aplicacion del hotel.
 * Maneja la generacion de reportes, la visualizacion de informes y la configuracion de ayuda.
 * @author Mateo
 */
public class HelloController implements Initializable {

    Set<Habitacion> hotel = new HashSet<>();

    JasperPrint print = new JasperPrint();

    JasperViewer visor = new JasperViewer(print);

    @FXML
    public Label label;
    @FXML
    public Pane panel;
    @FXML
    private SwingNode helpButtonNode;
    private JButton helpButton;

    /**
     * Genera un informe de habitaciones y lo muestra en JasperViewer.
     * @param actionEvent Evento de la interfaz grafica.
     */
    @FXML
    public void btnGenerar(ActionEvent actionEvent) {
        try {
            File fichero = new File("./informes/Habitaciones.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(hotel, false);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./informes/hotel.png");
            print = JasperFillManager.fillReport(informe, parametros, coleccion);
            visor = new JasperViewer(print, false);
            visor.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
            mostrarAlertaError("Error generando el reporte");
        }
    }

    /**
     * Exporta el informe generado a un archivo PDF.
     * @param actionEvent Evento de la interfaz grafica.
     */
    @FXML
    public void btnExportar(ActionEvent actionEvent) {
        copiarArchivo("informes/informeHotel.pdf", "informeHotel.pdf");
    }

    /**
     * Muestra el informe en la interfaz usando JRViewerFX.
     * @param actionEvent Evento de la interfaz grafica.
     */
    @FXML
    public void btnMostrar(ActionEvent actionEvent) {
        JRViewerFX visorFX = new JRViewerFX(print);
        panel.getChildren().add(visorFX);
    }

    /**
     * Inicializa la aplicacion cargando algunas habitaciones de prueba y configurando la ayuda.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotel.add(new Habitacion(101, 50.0, new Reserva(2, "Juan Perez", 3)));
        hotel.add(new Habitacion(102, 60.0, new Reserva(1, "Maria Gomez", 2)));
        hotel.add(new Habitacion(103, 55.0, new Reserva(3, "Carlos Ruiz", 4)));
        hotel.add(new Habitacion(104, 70.0, new Reserva(2, "Ana Lopez", 1)));
        hotel.add(new Habitacion(105, 65.0, new Reserva(4, "Luis Fernandez", 5)));
        initJavaHelpConfig();
    }

    /**
     * Configura la ayuda de JavaHelp.
     */
    private void initJavaHelpConfig() {
        try {
            File helpFile = new File("help/help_set.hs");
            URL helpURL = helpFile.toURI().toURL();
            HelpSet helpSet = new HelpSet(getClass().getClassLoader(), helpURL);
            HelpBroker helpBroker = helpSet.createHelpBroker();

            SwingUtilities.invokeLater(() -> {
                helpButton = new JButton("Ayuda");
                helpButton.setBounds(0, 0, 150, 50);
                helpButtonNode.setContent(helpButton);
                helpBroker.enableHelpOnButton(helpButton, "aplicacion", helpSet);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2),
                        e -> helpButtonNode.getContent().repaint()));
                timeline.playFromStart();
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar la ayuda de JavaHelp", e);
        }
    }

    /**
     * Copia un archivo de origen a destino.
     * @param origen Ruta del archivo de origen.
     * @param destino Ruta del archivo de destino.
     */
    public static void copiarArchivo(String origen, String destino) {
        try {
            Files.copy(Path.of(origen), Path.of(destino), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado con exito.");
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    /**
     * Muestra una alerta de error en la interfaz.
     * @param mensaje Mensaje de error a mostrar.
     */
    private void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(mensaje);
        alert.setContentText("");
        alert.showAndWait();
    }
}
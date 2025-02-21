package app.hotel_2;

<<<<<<< Updated upstream
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;

import javafx.embed.swing.SwingFXUtils;
=======
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingNode;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
import javafx.stage.Stage;
=======
import javafx.util.Duration;
>>>>>>> Stashed changes
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import win.zqxu.jrviewer.JRViewerFX;

<<<<<<< Updated upstream
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


=======
import javax.help.HelpBroker;
import javax.help.HelpSet;
>>>>>>> Stashed changes
import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

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

    @FXML
    public void btnGenerar(ActionEvent actionEvent) {
        try {
            File fichero = new File("./informes/Habitaciones.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(hotel, false);

            HashMap<String, Object> parametetros = new HashMap<>();

            parametetros.put("RUTA_IMAGEN", "./informes/hotel.png");

            print = JasperFillManager.fillReport(informe, parametetros, coleccion);
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
    public void btnExportar(ActionEvent actionEvent) {
        copiarArchivo("informes/informeHotel.pdf", "informeHotel.pdf");
        /*
        try {
            String pathname = "./informes/informesHotel.pdf";
            File reportFile = new File(pathname);
            OutputStream output = new FileOutputStream(reportFile);
            JasperExportManager.exportReportToPdfStream(print, output);
            JasperExportManager.exportReportToPdfFile(print, pathname);
            visor.setVisible(true);
        } catch (FileNotFoundException e) {
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
        }*/
    }

    @FXML
<<<<<<< Updated upstream
    public void btnMostrar(ActionEvent actionEvent){
        //JRViewerFX visor = new JRViewerFX(print);
        //panel.getChildren().add(visor);
        mostrarPDFEnPanel(panel,"informes/informesHotel.pdf");
=======
    public void btnMostrar(ActionEvent actionEvent) {
        JRViewerFX visor = new JRViewerFX(print);
        panel.getChildren().add(visor);
>>>>>>> Stashed changes
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

        initJavaHelpConfig();
    }

    private void initJavaHelpConfig() {
        try {
            // Cargar el archivo de configuración de JavaHelp
            File helpFile = new File("help/help_set.hs");
            URL helpURL = helpFile.toURI().toURL();
            HelpSet helpSet = new HelpSet(getClass().getClassLoader(), helpURL);
            HelpBroker helpBroker = helpSet.createHelpBroker();

            SwingUtilities.invokeLater(() -> {
                helpButton = new JButton("Ayuda");
                helpButton.setBounds(0, 0, 150, 50);
                helpButtonNode.setContent(helpButton);

                // Asociar el botón con la ayuda de JavaHelp
                helpBroker.enableHelpOnButton(helpButton, "aplicacion", helpSet);

                // Evita que el botón se vea en negro
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2),
                        e -> helpButtonNode.getContent().repaint()));
                timeline.playFromStart();
            });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar la ayuda de JavaHelp", e);
        }
    }

    public static void copiarArchivo(String origen, String destino) {
        Path sourcePath = Path.of(origen);
        Path destinationPath = Path.of(destino);

        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado con exito.");
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
    /*
    * File fichero = new File("./informes/Libros.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(libros);

            HashMap<String, Object> parametetros = new HashMap<>();

            parametetros.put("RUTA_IMAGEN", "./informes/libro.png");

            JRDesignSortField sortField = new JRDesignSortField();
            sortField.setName("ISBN");
            sortField.setOrder(SortOrderEnum.ASCENDING);
            sortField.setType(SortFieldTypeEnum.FIELD);
            List<JRSortField> sortList = new ArrayList<>();
            sortList.add(sortField);
            parametetros.put(JRParameter.SORT_FIELDS, sortList);

            JasperPrint print = JasperFillManager.fillReport(informe,parametetros,coleccion);
            // JasperExportManager.exportReportToPdf(print,fichero);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setVisible(true);
    * */
    }

    public static void copiarArchivo(String origen, String destino) throws IOException {
        File archivoOrigen = new File(origen);
        File archivoDestino = new File(destino);

        try (FileInputStream fis = new FileInputStream(archivoOrigen);
             FileOutputStream fos = new FileOutputStream(archivoDestino)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLeidos);
            }
        }
    }

    public void mostrarPDFEnPanel(Pane panel, String rutaPDF) {
        WebView webView = new WebView();
        // Obtener el motor de WebView
        WebEngine webEngine = webView.getEngine();

        // Ruta al archivo PDF (asegúrate de que la ruta sea correcta)
        String pdfUrl = "file:///" + new File(rutaPDF).getAbsolutePath().replace("\\", "/");

        // Cargar el archivo PDF en el WebView (los navegadores generalmente lo permiten)
        webEngine.load(pdfUrl);

        // Añadir el WebView al panel para mostrar el PDF
        panel.getChildren().add(webView);
    }

}
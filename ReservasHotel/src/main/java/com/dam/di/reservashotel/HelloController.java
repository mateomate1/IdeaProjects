
package com.dam.di.reservashotel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import java.io.File;
import java.net.URL;

/**
 * Clase Controller
 */
public class HelloController {

    @FXML
    private SwingNode helpButtonNode;

    @FXML
    private JButton helpButton;

    /**
     * Definicion de atributos locales, correspondientes a los elementos graficos de la aplicacion, al igual que una instancia de la clase Reporte
     */
    @FXML
    private Pane paneReporte;
    @FXML
    private Button btnVisualizarInforme, btnGuardarPDF;

    private Reporte reporte;

    /**
     * Metodo initialize
     * Crea un nuevo reporte y se lo asigna a la instancia creada anteriormente
     */
    public void initialize() {
        reporte = new Reporte();
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
                helpButton = new JButton("JavaHelp");
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
            throw new RuntimeException("ERROR: no se pudo cargar la ayuda de JavaHelp", e);
        }
    }

    /**
     * Metodo mostrarReporte
     * Accede al metodo mostrarInforme de la clase Reporte
     */
    @FXML
    private void mostrarReporte() {
        reporte.mostrarInforme(paneReporte);
    }

    /**
     * Metodo exportarReporte
     * Accede al metodo exportarInforme de la clase Reporte con la ruta deseada pasada como parametro
     */
    @FXML
    private void exportarReporte() {
        reporte.exportarInforme("informes/informeHotel.pdf");
    }
}




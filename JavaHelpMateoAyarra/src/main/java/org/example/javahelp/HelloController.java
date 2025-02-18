package org.example.javahelp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.util.Duration;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import java.io.File;
import java.net.URL;

public class HelloController {

    @FXML
    private SwingNode helpButtonNode;

    private JButton helpButton;

    @FXML
    public void initialize() {
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
}

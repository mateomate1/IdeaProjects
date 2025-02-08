package app.mateo_ud4_registro_login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MainApplication extends Application {
    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    private final String fichero = "db.properties";
    private Properties fichProperties = null;

    private String USER, PASSWORD, DRIVERNAME, DBURL;
    private boolean keep = false;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Gestor Usuarios");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void getProperties() {
        log.info("Recogiendo las propiedades para el inicio de sesion");
        try {
            fichProperties = new Properties();
            fichProperties.load(new FileInputStream(fichero));
            keep = Boolean.parseBoolean(fichProperties.getProperty("keep"));
            log.info("El usuario de la ultima sesion ha decidido " + (keep ? "guardar":"no guardar") + " la sesion");
            if (keep){
                log.debug("Recuperando ultima sesion");
                USER = fichProperties.getProperty("lastUser");
                PASSWORD = fichProperties.getProperty("lastPass");
            }
            else {
                log.debug("Recuperando sesion por defecto");
                USER = fichProperties.getProperty("user");
                PASSWORD = fichProperties.getProperty("password");
            }
            log.info("Usuario: " + USER + " Contraseña: " + PASSWORD);
            DRIVERNAME=fichProperties.getProperty("driver");
            DBURL = fichProperties.getProperty("url");
            log.debug("Driver " + DRIVERNAME + "URL: " + DBURL);

        } catch (IOException e) {
            log.error("Error recogiendo datos del fichero properties" + e.getMessage());
        }
    }
}
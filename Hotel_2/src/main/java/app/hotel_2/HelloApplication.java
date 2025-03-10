package app.hotel_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicacion que extiende Application y gestiona la inicializacion de la interfaz grafica.
 */
public class HelloApplication extends Application {

    /**
     * Metodo que inicia la aplicacion JavaFX, cargando la vista desde un archivo FXML y configurando la escena.
     *
     * @param stage El escenario principal de la aplicacion.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     * @author Mateo
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hotel Geranio");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Metodo principal que lanza la aplicacion JavaFX.
     *
     * @param args Argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}

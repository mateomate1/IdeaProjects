    package app.mateo_ud4_registro_login_v2;

    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Scene;
    import javafx.stage.Stage;

    import java.io.IOException;
    import java.io.UnsupportedEncodingException;
    import java.security.NoSuchAlgorithmException;
    import java.util.Map;
    import java.util.Scanner;

    public class HelloApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 266 , 361);
            stage.setTitle("Gestor Usuarios");
            stage.setScene(scene);
            stage.setWidth(266);
            stage.setHeight(361);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }

    }
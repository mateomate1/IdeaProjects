package com.dam.di.gestion.gestiondeservicios;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsHandler {
    public static void load(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(fxml));
        Scene scene = new Scene(loader.load(), 400,400);
        Stage stage = new Stage();
        stage.setTitle("BiblioDuck");
        stage.setScene(scene);
        stage.show();
    }
    public static void close(Stage stage) {
        stage.close();
    }
}

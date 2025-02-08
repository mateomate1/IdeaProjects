package com.dam.di.gestion.recudi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MainApplication extends Application {

    private static final String fichero = "db.properties";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Properties fichProperties;
        try {
            fichProperties = new Properties();
            fichProperties.load(new FileInputStream(fichero));
            fichProperties.put("user", "mateo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        launch();
    }
}
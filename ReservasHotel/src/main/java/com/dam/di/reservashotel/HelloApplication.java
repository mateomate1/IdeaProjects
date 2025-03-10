package com.dam.di.reservashotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase Application
 */
public class HelloApplication extends Application {
    /**
     * Metodo start
     * Crea la ventana principal de la aplicacion, ajustando el tamaño, titulo y referenciando el fxml correspondiente
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 450);
            primaryStage.setTitle("Sistema de Reservas de Hotel");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo main
     * Lanza la aplicacion a traves del metodo launch
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}


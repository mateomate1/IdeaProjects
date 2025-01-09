package com.dam.di.gestion.gestiondeservicios;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML TextField userImput;
    @FXML PasswordField passwordField;
    @FXML
    public void access(){

    }
    @FXML
    public void close() throws IOException {
        WindowsHandler.load("initView.fxml");
        WindowsHandler.close((Stage) userImput.getScene().getWindow());
    }

}

package com.example.tablapersonas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML private Button btnAgregar;
    @FXML private TextField inNombre;
    @FXML private TextField inApellidos;
    @FXML private TextField inEdad;
    @FXML private TableView<Persona> tabla;
    @FXML private TableColumn<?,?> colNombre;
    @FXML private TableColumn<?,?> colApellidos;
    @FXML private TableColumn<?,?> colEdad;
    private ObservableList<Persona> personas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personas = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("Apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("Edad"));
    }

    @FXML
    private void addPersona(ActionEvent event){
        try {
            String nombre = this.inNombre.getText();
            String apellidos = this.inApellidos.getText();
            int edad = Integer.parseInt(this.inEdad.getText());

            Persona p = new Persona(nombre, apellidos, edad);

            if (!this.personas.contains(p)) {
                this.personas.add(p);
                this.tabla.setItems (personas);

            } else {
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setHeaderText (null);
                alert.setTitle("Error");
                alert.setContentText("La persona existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }

}
package com.example.tablapersonas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML private Button btnAgregar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private TextField inNombre;
    @FXML private TextField inApellidos;
    @FXML private TextField inEdad;
    @FXML private TableView<Persona> tblPersonas;
    @FXML private TableColumn<?, ?> colNombre;
    @FXML private TableColumn<?, ?> colApellidos;
    @FXML private TableColumn<?, ?> colEdad;
    private ObservableList<Persona> personas;

    private static final String RUTA_ARCHIVO = "personas.dat";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personas = FXCollections.observableArrayList();

        // Cargar datos desde el archivo binario
        List<Persona> listaDesdeArchivo = GestorBin.leer(RUTA_ARCHIVO);
        personas.addAll(listaDesdeArchivo);

        this.colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("Apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("Edad"));
        this.tblPersonas.setItems(personas);
    }

    @FXML
    private void addPersona(ActionEvent event) {
        try {
            String nombre = this.inNombre.getText();
            String apellidos = this.inApellidos.getText();
            int edad = Integer.parseInt(this.inEdad.getText());

            Persona p = new Persona(nombre, apellidos, edad);

            if (!this.personas.contains(p)) {
                this.personas.add(p);
                this.tblPersonas.setItems(personas);

                // Añadir al archivo binario
                GestorBin.add(RUTA_ARCHIVO, p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Persona añadida");
                alert.showAndWait();
            } else {
                mostrarError("La persona ya existe");
            }
        } catch (NumberFormatException e) {
            mostrarError("Formato incorrecto");
        }
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p != null) {
            this.inNombre.setText(p.getNombre());
            this.inApellidos.setText(p.getApellidos());
            this.inEdad.setText(String.valueOf(p.getEdad()));
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            mostrarError("Debes seleccionar una persona");
        } else {
            try {
                String nombre = this.inNombre.getText();
                String apellidos = this.inApellidos.getText();
                int edad = Integer.parseInt(this.inEdad.getText());

                Persona aux = new Persona(nombre, apellidos, edad);

                if (!this.personas.contains(aux) || p.equals(aux)) {
                    p.setNombre(nombre);
                    p.setApellidos(apellidos);
                    p.setEdad(edad);
                    this.tblPersonas.refresh();

                    // Actualizar archivo binario
                    GestorBin.escribir(RUTA_ARCHIVO, personas);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Persona modificada");
                    alert.showAndWait();
                } else {
                    mostrarError("La persona ya existe");
                }
            } catch (NumberFormatException e) {
                mostrarError("Formato incorrecto");
            }
        }
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();
        if (p == null) {
            mostrarError("Debes seleccionar una persona");
        } else {
            this.personas.remove(p);
            this.tblPersonas.refresh();

            // Actualizar archivo binario
            GestorBin.escribir(RUTA_ARCHIVO, personas);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Persona eliminada");
            alert.showAndWait();
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

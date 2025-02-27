package app.mateo_ud4_registro_login_v2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class altaController {
    @FXML TextField userName;
    @FXML TextField visiblePass;
    @FXML TextField visibleConfirm;

    @FXML PasswordField dotPass;
    @FXML PasswordField dotConfirm;

    @FXML CheckBox checkPass;
    @FXML CheckBox checkConfirm;

    private DBManager data = new DBManager(); // Instancia de DBManager

    @FXML
    private void initialize() {
        dotPass.textProperty().addListener((obs, oldVal, newVal) -> visiblePass.setText(newVal));
        visiblePass.textProperty().addListener((obs, oldVal, newVal) -> dotPass.setText(newVal));

        dotConfirm.textProperty().addListener((obs, oldVal, newVal) -> visibleConfirm.setText(newVal));
        visibleConfirm.textProperty().addListener((obs, oldVal, newVal) -> dotConfirm.setText(newVal));
    }

    @FXML
    public void chekVisible(ActionEvent actionEvent){
        dotPass.setVisible(!checkPass.isSelected());
    }

    @FXML
    public void chekConfirm(ActionEvent actionEvent){
        dotConfirm.setVisible(!checkConfirm.isSelected());
    }

    @FXML
    public void btnConfirmar(ActionEvent actionEvent) {
        // Obtener el nombre de usuario y la contraseña desde los campos de texto
        String usuario = userName.getText();
        String contrasena = null;

        // Verificar si las contraseñas coinciden
        if (!dotPass.getText().equals(dotConfirm.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Las contraseñas no coinciden");
            alert.setContentText("Por favor, verifica que ambas contraseñas sean iguales.");
            alert.showAndWait();
            return;
        }

        // Obtener la contraseña visible (la que el usuario ve)
        contrasena = visiblePass.getText();
        // Limpiar los campos de texto
        userName.clear();
        visiblePass.clear();
        visibleConfirm.clear();

        // Validación del nombre de usuario y la contraseña
        int validacion = UsersPasswordsData.validar(usuario, contrasena);
        if (validacion == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("El nombre de usuario o la contraseña contienen caracteres no válidos.");
            alert.setContentText("Los caracteres válidos son: " + UsersPasswordsData.USABLE_CHARS);
            alert.showAndWait();
            return;
        } else if (validacion == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("El nombre de usuario o la contraseña no cumplen con las especificaciones necesarias.");
            alert.setContentText("El usuario y contraseña deben tener entre " + UsersPasswordsData.MAX_CHARS + "-" + UsersPasswordsData.MIN_CHARS + " caracteres y deben contener al menos una mayúscula y un número.");
            alert.showAndWait();
            return;
        }

        // Crear una instancia de UsersPasswordsData para gestionar los usuarios
        UsersPasswordsData data = new UsersPasswordsData();

        // Verificar si el límite de usuarios ha sido alcanzado
        if (data.getUsers().size() >= UsersPasswordsData.MAX_USERS) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Límite de usuarios alcanzado");
            alert.setContentText("No se pueden almacenar más usuarios.");
            alert.showAndWait();
            return;
        }

        try {
            // Codificar la contraseña antes de agregarla
            contrasena = data.encode(contrasena);

            // Intentar agregar el nuevo usuario
            int resultado = data.addUser(usuario, contrasena);
            if (resultado == 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText("No se pudo agregar el usuario.");
                alert.setContentText("Límite de usuarios alcanzado.");
                alert.showAndWait();
            } else if (resultado == -1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText("No se pudo agregar el usuario.");
                alert.setContentText("Ese usuario ya existe.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Usuario registrado correctamente");
                alert.setContentText("El nuevo usuario ha sido guardado exitosamente.");
                alert.showAndWait();
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error del sistema");
            alert.setContentText("No se ha podido cifrar la contraseña, no se guardará el usuario...");
            alert.showAndWait();
        }
    }


    @FXML
    public void volver(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Gestor Usuarios");
            stage.setScene(scene);
            stage.setWidth(266);
            stage.setHeight(361);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar la vista");
            alert.setContentText("Ocurrió un problema al intentar regresar a la pantalla principal.");
            alert.showAndWait();
        }
    }
}

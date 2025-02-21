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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class altaController {
    @FXML TextField userName;
    @FXML TextField visiblePass;
    @FXML TextField visibleConfirm;

    @FXML PasswordField dotPass;
    @FXML PasswordField dotConfirm;

    @FXML CheckBox checkPass;
    @FXML CheckBox checkConfirm;

    private DBManager dbManager = new DBManager(); // Instancia de DBManager

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
        String user = userName.getText();
        String pass = null;

        if (!dotPass.getText().equals(dotConfirm.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Las contraseñas no coinciden");
            alert.setContentText("Por favor, verifica que ambas contraseñas sean iguales.");
            alert.showAndWait();
            return;
        }

        pass = visiblePass.getText();

        // Validación del nombre de usuario y la contraseña
        int validation = DBManager.validar(user, pass);
        if (validation == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("El nombre de usuario contiene caracteres no válidos");
            alert.setContentText("Los caracteres válidos son: " + DBManager.USABLE_CHARS);
            alert.showAndWait();
            return;
        } else if (validation == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("La contraseña contiene caracteres no válidos");
            alert.setContentText("Los caracteres válidos son: " + DBManager.USABLE_CHARS);
            alert.showAndWait();
            return;
        }

        // Verificamos si el usuario ya existe
        int totalUsuarios = dbManager.contarUsuarios();
        if (totalUsuarios == DBManager.MAX_USERS) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Límite de usuarios alcanzado");
            alert.setContentText("No se pueden almacenar más usuarios.");
            alert.showAndWait();
            return;
        }

        try {
            // Cifrado de la contraseña
            pass = dbManager.encode(pass);

            // Intentamos agregar al nuevo usuario
            boolean userAdded = dbManager.addUser(user, pass);
            if (!userAdded) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText("Error al agregar el usuario");
                alert.setContentText("No se pudo agregar el usuario.");
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

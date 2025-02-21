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
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class loginController {
    @FXML TextField userName;

    @FXML TextField visiblePass;

    @FXML PasswordField dotPass;

    @FXML
    CheckBox checkPass;

    private DBManager dbManager = new DBManager();  // Instancia del gestor de base de datos

    @FXML
    private void initialize() {
        dotPass.textProperty().addListener((obs, oldVal, newVal) -> visiblePass.setText(newVal));
        visiblePass.textProperty().addListener((obs, oldVal, newVal) -> dotPass.setText(newVal));
    }

    @FXML
    public void checkVisible(ActionEvent actionEvent){
        dotPass.setVisible(!checkPass.isSelected());
    }

    @FXML
    public void confirmar(ActionEvent actionEvent) {
        String user = userName.getText();
        String pass = dotPass.getText();

        // Validar el nombre de usuario y contraseña con los caracteres permitidos
        int validationResult = DBManager.validar(user, pass);

        if (validationResult == -1) {
            showError("Error de validación", "El nombre de usuario contiene caracteres no válidos.");
        } else if (validationResult == 0) {
            showError("Error de validación", "La contraseña contiene caracteres no válidos.");
        } else {
            // Codificar la contraseña antes de la autenticación
            try {
                pass = dbManager.encode(pass);  // Codificar la contraseña
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                showError("Error de codificación", "Error al codificar la contraseña.");
                return;
            }

            // Autenticar al usuario
            int authResult = dbManager.authenticateUser(user, pass);

            switch (authResult) {
                case -1:
                    showError("Error de autenticación", "El usuario no existe.");
                    break;
                case 0:
                    showError("Error de autenticación", "Contraseña incorrecta.");
                    break;
                case 1:
                    showInfo("Inicio de sesión exitoso", "Inicio de sesión exitoso.");
                    break;
                case -2:
                    showError("Error al codificar", "Error al codificar la contraseña.");
                    break;
                default:
                    showError("Error desconocido", "Error desconocido.");
                    break;
            }
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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

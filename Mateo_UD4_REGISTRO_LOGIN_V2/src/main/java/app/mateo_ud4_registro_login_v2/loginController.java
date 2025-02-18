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

public class loginController {
    @FXML TextField userName;

    @FXML TextField visiblePass;

    @FXML PasswordField dotPass;

    @FXML
    CheckBox checkPass;

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

        int validationResult = UsersPasswordsData.validar(user, pass);

        if (validationResult == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validación");
            alert.setHeaderText(null);
            alert.setContentText("El nombre de usuario contienen caracteres no válidos.");
            alert.showAndWait();
        } else if (validationResult == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de validación");
            alert.setHeaderText(null);
            alert.setContentText("La contraseña contiene caracteres no válidos.");
            alert.showAndWait();
        } else {
            UsersPasswordsData usersData = new UsersPasswordsData();
            int authResult = usersData.authenticateUser(user, pass);

            switch (authResult) {
                case -1:
                    Alert alertUserNotFound = new Alert(Alert.AlertType.ERROR);
                    alertUserNotFound.setTitle("Error de autenticación");
                    alertUserNotFound.setHeaderText(null);
                    alertUserNotFound.setContentText("El usuario no existe.");
                    alertUserNotFound.showAndWait();
                    break;
                case 0:
                    Alert alertWrongPassword = new Alert(Alert.AlertType.ERROR);
                    alertWrongPassword.setTitle("Error de autenticación");
                    alertWrongPassword.setHeaderText(null);
                    alertWrongPassword.setContentText("Contraseña incorrecta.");
                    alertWrongPassword.showAndWait();
                    break;
                case 1:
                    Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                    alertSuccess.setTitle("Inicio de sesión exitoso");
                    alertSuccess.setHeaderText(null);
                    alertSuccess.setContentText("Inicio de sesión exitoso.");
                    alertSuccess.showAndWait();
                    break;
                case -2:
                    Alert alertHashError = new Alert(Alert.AlertType.ERROR);
                    alertHashError.setTitle("Error al codificar");
                    alertHashError.setHeaderText(null);
                    alertHashError.setContentText("Error al codificar la contraseña.");
                    alertHashError.showAndWait();
                    break;
                default:
                    Alert alertUnknownError = new Alert(Alert.AlertType.ERROR);
                    alertUnknownError.setTitle("Error desconocido");
                    alertUnknownError.setHeaderText(null);
                    alertUnknownError.setContentText("Error desconocido.");
                    alertUnknownError.showAndWait();
                    break;
            }
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

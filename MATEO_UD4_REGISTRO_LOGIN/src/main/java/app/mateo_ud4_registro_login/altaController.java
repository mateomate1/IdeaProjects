package app.mateo_ud4_registro_login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
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

    @FXML
    private void initialize() {
        // Sincronizar los textos entre PasswordField y TextField
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
    public void btnConfirmar(ActionEvent actionEvent){
        String name = userName.getText();
        String password = "";
        if(dotPass.getText().equals(dotConfirm.getText())){ // Confirmar que las contraseñas coincides
            password = dotPass.getText();
        }
        if((!name.isBlank() && !password.isBlank()) || // Comprobar que ninguno de los campos esten en blanco y por lo tanto tampoco vacios
                (name.length() >= UsersPasswordsData.MIN_CHARS && name.length() <= UsersPasswordsData.MAX_CHARS)|| // Comprobar que esta entre 5-10 caracteres
                (password.length() >= UsersPasswordsData.MIN_CHARS && password.length() <= UsersPasswordsData.MAX_CHARS)){
            char[][] user = new char[2][10];
            user[0] = name.toCharArray();
            user[1] = password.toCharArray();
            try{
                boolean salida = UsersPasswordsData.addUser(user); // Intentar guardar en archibo binario
                //if (salida)

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error interno al serializar el usuario");
                alert.setContentText("No se ha guardado el usuario");
                alert.showAndWait();
                System.out.println(e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Tu usuario/contraseña no contienen entre 5-10 caracteres");
            alert.setContentText("No se ha guardado el usuario");
            alert.showAndWait();
        }
    }

    public String toSHA256(String string) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(string.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xFF & hash[i]);
                if(hex.length()==1){
                    hexString.append('0');
                    hexString.append(hex);
                }
                string = hexString.toString();
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            string = "FAILED HASH";
            throw e;
        }
        System.out.println(string);
        return string;
    }
}


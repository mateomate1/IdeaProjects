package app.mateo_ud4_registro_login;

public class Usuario {
    String username, password;

    public static final int MAX_CHARS = 10, MIN_CHARS = 5, MAX_USERS = 10;
    public static final String
            USABLE_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + // Letras mayúsculas
                    "abcdefghijklmnopqrstuvwxyz" + // Letras minúsculas
                    "0123456789" + // Números
                    "!#$%&'()*+,-./:;<=>?@[]^_`{|}~", // Caracteres especiales permitidos;
            usersDataFile = "UsersData.dat";

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

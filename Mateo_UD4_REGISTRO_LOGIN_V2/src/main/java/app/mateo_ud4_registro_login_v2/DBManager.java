package app.mateo_ud4_registro_login_v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;

public class DBManager {
    private static final Logger log = LoggerFactory.getLogger(DBManager.class);

    public static final int MAX_USERS = 10;
    public static final int MAX_CHARS = 10;
    public static final String
            USABLE_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + // Letras mayúsculas
                    "abcdefghijklmnopqrstuvwxyz" + // Letras minúsculas
                    "0123456789" + // Números
                    "!#$%&()*+,-./:;<=>?@[]^_`{|}~", // Caracteres especiales permitidos; '=0
            usersDataFile = "UsersData.dat";

    private String user, password, driverName, dbUrl;
    private boolean keep;
    private String lastUser, lastPass;
    private static final String PROPERTIES_FILE = "db.properties";
    private Connection conexion = null;

    public DBManager() {
        loadProperties();
        try {
            // Registramos el driver
            Class.forName(driverName);
            log.debug("Se ha registrado correctamente el driver: " + driverName);
            conexion = DriverManager.getConnection(dbUrl, user, password);
            log.debug("Se ha creado correctamente la conexion");
        } catch (ClassNotFoundException e) {
            log.error("Error registrando el driver: " + driverName, e);
        } catch (SQLException e) {
            log.error("Error estableciendo conexion con la BDD: " + e.getMessage(), e);
        }
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(fis);
            dbUrl = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driverName = properties.getProperty("driver");
            keep = Boolean.parseBoolean(properties.getProperty("keep"));
            lastUser = properties.getProperty("lastUser");
            lastPass = properties.getProperty("lastPass");
        } catch (IOException e) {
            log.error("Error cargando el fichero de propiedades: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        return conexion;
    }

    public boolean addUser(String username, String contrasena) {
        // Primero, contamos el total de usuarios
        int totalUsuarios = contarUsuarios();

        if (totalUsuarios >= MAX_USERS) {
            log.warn("No se puede agregar el usuario: el limite de usuarios es 10.");
            return false; // Si hay más de 10 usuarios, no se añade el nuevo
        }

        // Si el total de usuarios es menor que 10, se procede a agregar el nuevo usuario
        String query = "INSERT INTO usuarios (username, pass) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, contrasena);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                log.debug("Usuario " + username + " agregado correctamente.");
                return true; // Usuario agregado con éxito
            } else {
                log.warn("No se pudo agregar el usuario: " + username);
                return false; // Si no se puede agregar el usuario
            }
        } catch (SQLException e) {
            log.error("Error al agregar usuario: " + e.getMessage(), e);
            return false; // Error al ejecutar la consulta
        }
    }

    public int authenticateUser(String username, String contrasena) {
        String query = "SELECT pass FROM usuarios WHERE username = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // El usuario existe, ahora verificamos la contraseña
                String storedPass = rs.getString("pass");
                return storedPass.equals(contrasena) ? 1 : 0;
            } else {
                // El usuario no existe
                return -1;
            }
        } catch (SQLException e) {
            log.error("Error al verificar usuario: " + e.getMessage(), e);
            return -1; // En caso de error, consideramos que el usuario no existe
        }
    }

    public int contarUsuarios() {
        String query = "SELECT COUNT(*) FROM usuarios";
        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1); // Devuelve la cantidad total de usuarios
            }
        } catch (SQLException e) {
            log.error("Error al contar los usuarios: " + e.getMessage(), e);
        }
        return -1; // En caso de error, devuelve -1
    }

    /**
     * Metodo que valida un nombre de usuario y una contrasena segun los caracteres permitidos.
     *
     * Este metodo revisa que cada caracter del nombre de usuario y de la contrasena
     * pertenezca a un conjunto de caracteres validos. Si alguno de los caracteres no es valido,
     * se devuelve un codigo de error correspondiente.
     *
     * @param user El nombre de usuario a validar.
     * @param pass La contrasena a validar.
     * @return Un codigo entero que indica el estado de la validacion:
     *         -1: El nombre de usuario contiene caracteres no validos.
     *         0: La contrasena contiene caracteres no validos.
     *         1: El nombre de usuario y la contrasena son validos.
     */
    public static int validar(String user, String pass){
        if (user == null || user.trim().isEmpty()) {
            return -1;
        }
        if (pass == null || pass.trim().isEmpty()) {
            return -1;
        }
        for(Character c : user.toCharArray()){
            if (USABLE_CHARS.indexOf(c) == -1) {
                log.warn("Carácter no válido detectado: [" + c + "] con código ASCII: " + (int)c);
                return -1; // Codigo usuario no valido
            }
        }
        for(Character c : pass.toCharArray()){
            if (USABLE_CHARS.indexOf(c) == -1) {
                return 0; // Codigo contraseña no valida
            }
        }
        return 1; // Codigo validado
    }

    public String encode(String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(pass.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xFF & hash[i]);
                if(hex.length()==1){
                    hexString.append('0');
                    hexString.append(hex);
                }
                pass = hexString.toString();
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            pass = "FAILED HASH";
            throw e;
        }
        return pass;
    }

}

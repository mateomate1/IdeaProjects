package app.mateo_ud4_registro_login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static final Logger log = LoggerFactory.getLogger(DBManager.class);

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
        String query = "INSERT INTO usuarios (username, contrasena) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, username);  // Usamos username como identificador
            stmt.setString(2, contrasena); // Es importante encriptar la contraseña antes de almacenarla
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Usuario " + username + " agregado correctamente.");
                return true;
            } else {
                log.warn("No se pudo agregar el usuario: " + username);
                return false;
            }
        } catch (SQLException e) {
            log.error("Error al agregar usuario: " + e.getMessage(), e);
            return false;
        }
    }
}

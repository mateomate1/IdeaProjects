package app.mateo_ud4_registro_login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static final Logger log = LoggerFactory.getLogger(DBManager.class);

    private String USER, PASSWORD, DRIVERNAME, DBURL;
    private boolean keep = false;

    private Connection conexion = null;

    public DBManager(String USER, String PASSWORD, String DRIVERNAME, String DBURL) {
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        this.DRIVERNAME = DRIVERNAME;
        this.DBURL = DBURL;
        try {
            // PRIMERO REGISTRAMOS EL DRIVER
            Class.forName(DRIVERNAME);
            log.debug("Se ha registrado correctamente el driver: " + DRIVERNAME);
            conexion = DriverManager.getConnection(DBURL, USER, PASSWORD);
            log.debug("Se ha creado correctamente la conexion");
        } catch (ClassNotFoundException e) {
            log.error("Error durante registrando el driver: " + DRIVERNAME);
        } catch (SQLException e) {
            log.error("Error estableciendo conexion con la BDD: " + e.getMessage());
        }
    }
}

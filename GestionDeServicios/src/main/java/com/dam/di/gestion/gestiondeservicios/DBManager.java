package com.dam.di.gestion.gestiondeservicios;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private Connection connection = null;
    private String
            FICHERO = "DB_BiblioDuck.properties",
            DRIVERNAME = "driver",
            URL="url",
            USER = "user",
            PASSWORD = "password";
    public DBManager(){
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileInputStream(FICHERO));
            DRIVERNAME = propiedades.getProperty(DRIVERNAME);
            URL = propiedades.getProperty(URL);
            USER = propiedades.getProperty(USER);
            PASSWORD = propiedades.getProperty(PASSWORD);

            Class.forName(DRIVERNAME);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

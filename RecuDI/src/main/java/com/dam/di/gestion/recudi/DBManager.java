package com.dam.di.gestion.recudi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBManager {
    private static final Logger log = LoggerFactory.getLogger(DBManager.class);

    private String USER, PASSWORD, DRIVERNAME, DBURL;

    private final String fichero = "db.properties";

    private Connection conexion = null;

    public DBManager(){
        Properties fichProperties;
        try {
            fichProperties = new Properties();
            fichProperties.load(new FileInputStream(fichero));
            fichProperties.put("user", "mateo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

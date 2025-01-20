package com.dam.di.gestion.gestiondeservicios;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DBManager {
    private Connection connection = null;
    private final String
            FICHERO = "DB_BiblioDuck.properties",
            DRIVERNAME, URL, USER, PASSWORD;
    public DBManager(){
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileInputStream(FICHERO));
            DRIVERNAME = propiedades.getProperty("driver");
            URL = propiedades.getProperty("url");
            USER = propiedades.getProperty("USER");
            PASSWORD = propiedades.getProperty("password");
            System.out.println(PASSWORD);
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
    public boolean addUser(Usuario u){
        boolean status = true;
        PreparedStatement psAdd = null;
        if(connection != null){
            String username, password;
            if((username = u.getUsername())==null || (password = u.getPassword())==null){
                return false;
            }
            try {
                psAdd = connection.prepareStatement(QUERRYS.INSERTAR_USUARIO);
                psAdd.setString(1, username);
                psAdd.setString(2,password);
                psAdd.executeUpdate();
            } catch (SQLException e) {
                status = false;
            } finally {
                if (psAdd != null){
                    try {
                        psAdd.close();
                    } catch (SQLException e) {
                        status = false;
                    }
                }
            }
        }
        return status;
    }
}

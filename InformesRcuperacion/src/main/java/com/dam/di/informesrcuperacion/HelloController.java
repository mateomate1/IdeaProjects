package com.dam.di.informesrcuperacion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class HelloController implements Initializable {
    Set<Producto> productos;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Categoria c1 = new Categoria("Panaderia", "Diferentes comidas");
        Categoria c2 = new Categoria("Liquidos","Diferentes bebidas o liquideos de cocina");
        Producto p1 = new Producto(1, "Pan", c1, 1.25, LocalDate.of(2,3,2025));
        Producto p2 = new Producto(2, "Ensaimada", c1, 2.5, LocalDate.of(7,3,2025));
        Producto p3 = new Producto(3, "Agua", c2, 0.75, LocalDate.of(2,3,2027));
        Producto p4 = new Producto(2, "Aceite", c2, 5.20, LocalDate.of(2,5,2025));

        productos = new HashSet<>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);



    }
    @FXML
    public void visualizar(){

    }
    public void guardar(){

    }
}
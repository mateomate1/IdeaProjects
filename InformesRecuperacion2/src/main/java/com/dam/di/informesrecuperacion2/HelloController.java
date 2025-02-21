package com.dam.di.informesrecuperacion2;

import com.dam.di.informesrecuperacion2.Categoria;
import com.dam.di.informesrecuperacion2.Producto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import win.zqxu.jrviewer.JRViewerFX;

import java.sql.Connection;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class HelloController implements Initializable {
    Set<Producto> productos;
    JasperPrint print = new JasperPrint();
    JasperViewer visor = new JasperViewer(print);
    @FXML public Label label;
    @FXML public Pane panel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Categoria c1 = new Categoria("Panaderia", "Diferentes comidas");
        Categoria c2 = new Categoria("Liquidos","Diferentes bebidas o liquideos de cocina");
        Categoria c3 = new Categoria("Jugetes", "Seccion de jugetes");
        Producto p1 = new Producto(1, "Pan", 1.25, LocalDate.of(2,3,2025));
        Producto p2 = new Producto(2, "Ensaimada", 2.5, LocalDate.of(7,3,2025));
        Producto p3 = new Producto(3, "Agua", 0.75, LocalDate.of(2,3,2027));
        Producto p4 = new Producto(4, "Aceite", 5.20, LocalDate.of(2,5,2025));
        Producto p5 = new Producto(5, "Peluche", 7.1, LocalDate.of(3,5,2025));
        Producto p6 = new Producto(6, "Brioche", 1.20, LocalDate.of(2,3,2025));
        Producto p7 = new Producto(7, "Energety", 1, LocalDate.of(6,12,2025));
        Producto p8 = new Producto(8, "Munieco", 6, LocalDate.of(7,12,2030));
        Producto p9 = new Producto(9, "Friegasuelos", 12.5, LocalDate.of(12,7, 2026));
        Producto p10 = new Producto(10, "Chapa", 0.5, LocalDate.of(2,5,2028));

        productos = new HashSet<>();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        productos.add(p6);
        productos.add(p7);
        productos.add(p8);
        productos.add(p9);
        productos.add(p10);
        try {
            File fichero = new File("./informes/Productos2.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource colection = new JRBeanCollectionDataSource(productos, false);

            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("RUTA_IMAGEN", "./informes/hotel.png");

            print = JasperFillManager.fillReport(informe,parametros,colection);
            visor = new JasperViewer(print, false);
            visor.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void visualizar(){
        JRViewerFX visor = new JRViewerFX(print);
        panel.getChildren().add(visor);
    }
    public void guardar(){

    }
}
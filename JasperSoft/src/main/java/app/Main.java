package app;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;
import net.sf.jasperreports.engine.type.SortOrderEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Autor autor1 = new Autor("1Paco", "1Pill");
        Autor autor2 = new Autor("2Paco", "2Pill");
        Autor autor3 = new Autor("3Paco", "3Pill");
        Autor autor4 = new Autor("4Paco", "4Pill");
        Libro libro1 = new Libro("111111","Libro 1", autor1);
        Libro libro2 = new Libro("222222","Libro 2", autor2);
        Libro libro3 = new Libro("333333","Libro 3", autor3);
        Libro libro4 = new Libro("444444","Libro 4", autor4);
        libro1.setVentas(11);
        libro2.setVentas(22);
        libro3.setVentas(33);
        libro4.setVentas(44);

        HashSet<Libro> libros = new HashSet<>();
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);

        try{
            File fichero = new File("./informes/ReporteLibros.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource colection = new JRBeanCollectionDataSource((libros));

            HashMap<String, Object> parametros = new HashMap<>();

            parametros.put("RUTA_IMAGEN", "./informes/libro.png");

            JRDesignSortField sortField =new JRDesignSortField();
            sortField.setName("ISBN");
            sortField.setOrder(SortOrderEnum.ASCENDING);
            sortField.setType(SortFieldTypeEnum.FIELD);
            List<JRSortField> sortList = new ArrayList<>();
            sortList.add(sortField);
            parametros.put(JRParameter.SORT_FIELDS, sortList);

            JasperPrint print = JasperFillManager.fillReport(informe, parametros, colection);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
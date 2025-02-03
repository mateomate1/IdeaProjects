package biblioteca;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashSet;

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
        //System.out.println("LIBROS ");
        try {
            File fichero = new File("./informes/Cherry.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(libros);
            JasperPrint print = JasperFillManager.fillReport(informe,null,coleccion);
            // JasperExportManager.exportReportToPdf(print,fichero);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setVisible(true);
        }
        catch (JRException e) {
            e.printStackTrace();
        }
    }
}
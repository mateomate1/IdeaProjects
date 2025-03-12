package app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;
import net.sf.jasperreports.engine.type.SortOrderEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    HashSet<Empleado> empleados;
    Departamento d1,d2,d3;

    @FXML
    protected void onHelloButtonClick() {
        mostrarReport();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        d1 = new Departamento(1, "Ventas", "Departamento encargado de gestionar las ventas");
        d2 = new Departamento(2, "Marketing", "Departamento encargado de el marketing y publicidad");
        d3 = new Departamento(3, "I+D+D", "Departamento encargado al desarrollo de nuevo software para la empresa");

        Empleado e1 = new Empleado(1, "Juan", "Jefe", 21200.20, LocalDate.of(2015,5,2), d1);
        Empleado e2 = new Empleado(2, "Paco", "Delegado", 18000.60, LocalDate.of(2015,7,5), d1);
        Empleado e3 = new Empleado(3, "Sergio", "Currito", 12000.00, LocalDate.of(2000, 9, 15),d2);
        Empleado e4 = new Empleado(4, "Augusto", "Presidente", 35000.70, LocalDate.of(1975, 2,3),d1);
        Empleado e5 = new Empleado(5, "Sanson", "Jefe", 15020.15, LocalDate.of(2020,2,6),d3);
        Empleado e6 = new Empleado(6, "Jose", "Currito", 9821.40, LocalDate.of(2017, 6,1),d3);
        Empleado e7 = new Empleado(7, "Mateo", "Presidente", 12300.12, LocalDate.of(1999, 5,4),d2);
        Empleado e8 = new Empleado(8, "Antonio", "Presidente", 12300.12, LocalDate.of(1999, 5,4),d3);
        Empleado e9 = new Empleado(9, "Hercules", "Portero", 12050.20, LocalDate.of(2015, 6,7),d1);

        empleados = new HashSet<>();
        empleados.add(e1);
        empleados.add(e2);
        empleados.add(e3);
        empleados.add(e4);
        empleados.add(e5);
        empleados.add(e6);
        empleados.add(e7);
        empleados.add(e8);
        empleados.add(e9);
        mostrarReport();
    }

    public void mostrarReport(){
        try{
            File fichero = new File("./informes/ejercicioB_mateo_ayarra_barbero.jasper");
            JasperReport informe = (JasperReport) JRLoader.loadObject(fichero);
            JRBeanCollectionDataSource colection = new JRBeanCollectionDataSource((empleados));

            HashMap<String, Object> parametros = new HashMap<>();

            parametros.put("RUTA_IMAGEN", "./informes/logo.png");

            JRDesignSortField sortField =new JRDesignSortField();
            sortField.setName("salario");
            sortField.setOrder(SortOrderEnum.ASCENDING);
            sortField.setType(SortFieldTypeEnum.FIELD);
            List<JRSortField> sortList = new ArrayList<>();
            sortList.add(sortField);

            parametros.put(JRParameter.SORT_FIELDS, sortList);

            parametros.put("media_d1", calcularMedia(d1));
            parametros.put("media_d2", calcularMedia(d2));
            parametros.put("media_d3", calcularMedia(d3));

            JasperPrint print = JasperFillManager.fillReport(informe, parametros, colection);
            JasperViewer visor = new JasperViewer(print, false);
            visor.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public Double calcularMedia(Departamento dep){
        int count = 0;
        Double media = 0.0;
        for(Empleado empleado:empleados){
            if(empleado.departamento==dep){
                media+=empleado.salario;
                count++;
            }
        }
        return media/count;
    }
}
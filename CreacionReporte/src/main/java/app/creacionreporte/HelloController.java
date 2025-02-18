package app.creacionreporte;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.HashSet;
import java.util.Set;

public class HelloController {
    Set<Habitacion> hotel = null;
    Reporte r = new Reporte();
    boolean generado = false;

    @FXML public Label label;
    @FXML public Pane panel;
    @FXML public ListView<Habitacion> habitaciones = new ListView<>();

    @FXML
    public void btnGenerar(ActionEvent actionEvent){
        r.generarInforme(hotel);
        label.setText("Informe generado ya puede cargar el informe");
        generado = true;
    }
    @FXML
    public void btnExportar(ActionEvent actionEvent){
        if(generado){
            String ruta = "./informes/informeHotel.pdf";
            r.exportarInforme(hotel, ruta);
            label.setText("Informe exportado en " + ruta);
        } else{
            label.setText("No se puede exportar el informe sin haberlo generado");
        }
    }
    @FXML
    public void btnMostrar(ActionEvent actionEvent){
        if(generado){
            r.mostrarInforme(hotel);
            label.setText("Mostrando informe");
        }
        else{
            label.setText("No se puede mostrar el informe sin haberlo generado");
        }
    }

    public void initialize(){
        Reserva r1 = new Reserva(2, "Juan Perez", 3);
        Reserva r2 = new Reserva(1, "Maria Gomez", 2);
        Reserva r3 = new Reserva(3, "Carlos Ruiz", 4);
        Reserva r4 = new Reserva(2, "Ana Lopez", 1);
        Reserva r5 = new Reserva(4, "Luis Fernandez", 5);

        Habitacion h1 = new Habitacion(101, 50.0, r1);
        Habitacion h2 = new Habitacion(102, 60.0, r2);
        Habitacion h3 = new Habitacion(103, 55.0, r3);
        Habitacion h4 = new Habitacion(104, 70.0, r4);
        Habitacion h5 = new Habitacion(105, 65.0, r5);

        hotel = new HashSet<>();

        hotel.add(h1);
        hotel.add(h2);
        hotel.add(h3);
        hotel.add(h4);
        hotel.add(h5);

        ObservableList<Habitacion> observableList = FXCollections.observableArrayList(hotel);
        habitaciones.setItems(observableList);
    }

}
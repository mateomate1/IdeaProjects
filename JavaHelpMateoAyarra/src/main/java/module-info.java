module org.example.javahelp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javahelp;
    requires javafx.swing;

    opens org.example.javahelp to javafx.fxml;
    exports org.example.javahelp;
}
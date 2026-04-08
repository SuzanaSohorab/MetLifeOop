module com.example.metlife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.metlife to javafx.fxml;
    exports com.example.metlife;
}
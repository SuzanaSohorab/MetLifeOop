module com.example.metlife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens com.example.metlife to javafx.fxml;
    exports com.example.metlife;
    exports com.example.metlife.Suzana;
    opens com.example.metlife.Suzana to javafx.fxml;
}
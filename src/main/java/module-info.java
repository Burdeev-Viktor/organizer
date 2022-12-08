module com.example.organizer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;
    requires com.jfoenix;
    requires org.burningwave.core;

    opens com.example.organizer to javafx.fxml;
    exports com.example.organizer;
    exports com.example.organizer.CustomView;
    opens com.example.organizer.CustomView to javafx.fxml;
    exports com.example.organizer.Controller;
    opens com.example.organizer.Controller to javafx.fxml;


}

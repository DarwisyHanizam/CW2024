module com.example.demo {
    requires transitive javafx.graphics;
    requires transitive java.desktop;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
}
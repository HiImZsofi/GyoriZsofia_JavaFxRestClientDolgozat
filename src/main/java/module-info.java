module com.example.gyorizsofia_javafxrestclientdolgozat {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires jdk.internal.le;


    opens com.example.gyorizsofia_javafxrestclientdolgozat to javafx.fxml;
    exports com.example.gyorizsofia_javafxrestclientdolgozat;
}
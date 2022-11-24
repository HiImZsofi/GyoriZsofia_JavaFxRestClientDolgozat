package com.example.gyorizsofia_javafxrestclientdolgozat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;

public class ProjectController {
    protected void error(String headerText) {
        error(headerText, "");
    }

    protected void error(String headerText, String contentText) {
        alert(Alert.AlertType.ERROR, headerText, contentText);
    }

    protected void warning(String headerText) {
        alert(Alert.AlertType.WARNING, headerText, "");
    }

    protected void alert(Alert.AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
package com.example.gyorizsofia_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class ListJobs extends ProjectController{
    @FXML
    public Button insertButton;
    @FXML
    public Button updateButton;
    @FXML
    public Button deleteButton;
    @FXML
    public TableView jobTable;
    @FXML
    public TableColumn titleCol;
    @FXML
    public TableColumn locationCol;
    @FXML
    public TableColumn nameCol;

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("jobLocation"));
        Platform.runLater(() -> {
            try {
                fetchData();
            } catch (IOException e) {
                error("Couldn't get data from server", e.getMessage());
                Platform.exit();
            }
        });
    }

    private void fetchData() throws IOException {
        Response response = RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Job[] jobs = converter.fromJson(content, Job[].class);
        jobTable.getItems().clear();
        for (Job job : jobs) {
            jobTable.getItems().add(job);
        }
    }
}

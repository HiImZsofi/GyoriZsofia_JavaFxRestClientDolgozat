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
    public TableColumn scoreCol;

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("jobLocation"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("jobScore"));
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

    @FXML
    public void insertClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("create-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Create new jobs here");
            stage.setScene(scene);
            stage.show();
            insertButton.setDisable(true);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
            stage.setOnCloseRequest(event -> {
                insertButton.setDisable(false);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
                try {
                    fetchData();
                } catch (IOException e) {
                    error("Random error");
                }
            });
        } catch (IOException e) {
            error("Could not load form", e.getMessage());
        }
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
        int selectedIndex = jobTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            warning("Select something first");
            return;
        }
        Job selected = (Job) jobTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("update-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 480);
            Stage stage = new Stage();
            stage.setTitle("Update Jobs");
            stage.setScene(scene);
            UpdateViewController controller = fxmlLoader.getController();
            controller.setPerson(selected);
            stage.show();
            insertButton.setDisable(true);
            updateButton.setDisable(true);
            deleteButton.setDisable(true);
            stage.setOnHidden(event -> {
                insertButton.setDisable(false);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
                try {
                    fetchData();
                } catch (IOException e) {
                    error("An error occurred while communicating with the server");
                }
            });
        } catch (IOException e) {
            error("Could not load form", e.getMessage());
        }
    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
        int selectedIndex = jobTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            warning("Select something first");
            return;
        }

        Job selected = (Job) jobTable.getSelectionModel().getSelectedItem();
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setHeaderText(String.format("Are you sure you want to delete %s?", selected.getName()));
        Optional<ButtonType> optionalButtonType = confirmation.showAndWait();
        if (optionalButtonType.isEmpty()) {
            System.err.println("Something went wrong");
            return;
        }
        ButtonType clickedButton = optionalButtonType.get();
        if (clickedButton.equals(ButtonType.OK)) {
            String url = App.BASE_URL + "/" + selected.getId();
            try {
                RequestHandler.delete(url);
                fetchData();
            } catch (IOException e) {
                error("Something went wrong");
            }
        }
    }
}

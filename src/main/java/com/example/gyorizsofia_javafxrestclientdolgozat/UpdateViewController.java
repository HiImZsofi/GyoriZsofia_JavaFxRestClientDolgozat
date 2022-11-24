package com.example.gyorizsofia_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

import static jdk.internal.org.jline.utils.Log.error;
import static sun.security.ssl.SSLLogger.warning;

public class UpdateViewController {
    public TextField nameField;
    public TextField titleField;
    public TextField locationField;
    public Button updateButton;
    private Job job;

    public void setPerson(Job job) {
        this.job = job;
        nameField.setText(this.job.getName());
        titleField.setText(this.job.getTitle());
        locationField.setText(this.job.getLocation());
    }
    @FXML
    public void updateClick(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        String title = titleField.getText().trim();
        String location = locationField.getText().trim();
        if (name.isEmpty()) {
            warning("Name is required");
            return;
        }
        else if (title.isEmpty()) {
            warning("Job title is required");
            return;
        }
        else if (location.isEmpty()) {
            warning("Employee location is required");
            return;
        }
        this.job.setName(name);
        this.job.setTitle(title);
        this.job.setLocation(location);
        Gson converter = new Gson();
        String json = converter.toJson(this.job);
        try {
            String url = App.BASE_URL + "/" + this.job.getId();
            Response response = RequestHandler.put(url, json);
            if (response.getResponseCode() == 200) {
                Stage stage = (Stage) this.updateButton.getScene().getWindow();
                stage.close();
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


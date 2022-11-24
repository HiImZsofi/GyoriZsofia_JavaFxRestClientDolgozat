package com.example.gyorizsofia_javafxrestclientdolgozat;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

import static jdk.internal.org.jline.utils.Log.error;
import static sun.security.ssl.SSLLogger.warning;

public class UpdateViewController extends ProjectController{
    public TextField nameField;
    public TextField titleField;
    public TextField locationField;
    public Button updateButton;
    public Spinner scoreField;
    public CheckBox workingField;
    private Job job;

    public void setPerson(Job job) {
        this.job = job;
        nameField.setText(this.job.getJobName());
        titleField.setText(this.job.getJobTitle());
        locationField.setText(this.job.getJobLocation());
        scoreField.getValueFactory().setValue(this.job.getJobScore());
        workingField.setSelected(false);
    }

    @FXML
    private void initialize() {
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 30);
        scoreField.setValueFactory(valueFactory);
    }
    @FXML
    public void updateClick(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        String title = titleField.getText().trim();
        String location = locationField.getText().trim();
        int score = (int) scoreField.getValue();
        boolean working = workingField.isSelected();
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
        this.job.setJobName(name);
        this.job.setJobTitle(title);
        this.job.setJobLocation(location);
        this.job.setJobScore(score);
        this.job.setWorking(working);
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


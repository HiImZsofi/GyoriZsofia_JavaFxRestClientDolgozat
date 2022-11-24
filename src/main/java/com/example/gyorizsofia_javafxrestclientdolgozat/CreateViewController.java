package com.example.gyorizsofia_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

import static jdk.internal.org.jline.utils.Log.error;
import static sun.security.ssl.SSLLogger.warning;

public class CreateViewController extends ProjectController{
    public TextField nameField;
    public TextField titleField;
    public TextField locationField;
    public Spinner<Integer> scoreField;
    public Button submitButton;
    public CheckBox workingField;

    public void initialize(){
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000,0);
        scoreField.setValueFactory(valueFactory);
    }

    public void submitClick(ActionEvent actionEvent) {
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
            warning("Job location is required");
            return;
        }
        Job newJob = new Job(0, name, title, location, score, working);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(newJob);
        try {
            Response response = RequestHandler.post(App.BASE_URL, json);
            if (response.getResponseCode() == 201) {
                warning("New job added!");
                nameField.setText("");
                titleField.setText("");
                locationField.setText("");
                scoreField.getValueFactory().setValue(0);
                workingField.setSelected(false);
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

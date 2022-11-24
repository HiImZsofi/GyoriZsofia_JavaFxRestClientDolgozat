package com.example.gyorizsofia_javafxrestclientdolgozat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static jdk.internal.org.jline.utils.Log.error;
import static sun.security.ssl.SSLLogger.warning;

public class CreateViewController {
    public TextField nameField;
    public TextField titleField;
    public TextField locationField;
    public Button submitButton;

    public void submitClick(ActionEvent actionEvent) {
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
            warning("Job location is required");
            return;
        }
        Job newJob = new Job(0, name, title, location);
        Gson converter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = converter.toJson(newJob);
        try {
            Response response = RequestHandler.post(App.BASE_URL, json);
            if (response.getResponseCode() == 201) {
                warning("New job added!");
                nameField.setText("");
                titleField.setText("");
                locationField.setText("");
            } else {
                String content = response.getContent();
                error(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

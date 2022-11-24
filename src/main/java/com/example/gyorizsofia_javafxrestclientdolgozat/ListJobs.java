package com.example.gyorizsofia_javafxrestclientdolgozat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
}

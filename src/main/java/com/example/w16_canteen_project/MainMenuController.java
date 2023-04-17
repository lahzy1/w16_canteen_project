package com.example.w16_canteen_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainMenuController {

    @FXML
    private Label LabelBalance;

    @FXML
    private Label LabelItemInBasket;

    @FXML
    private Button btnDrinks;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnLunch;

    @FXML
    private Button btnSandwiches;

    @FXML
    private Button btnSnacks;

    @FXML
    private Button btnViewBasket;

    @FXML
    private Label labelTotal;

    @FXML
    private ListView<?> subMenuListView;

    @FXML
    private TextField tfSearch;

    @FXML
    protected void onLogoutClick() {
        CanteenApplication.changeScene(ControllerNames.Login);
    }

    @FXML
    protected void onLunchClick() {
        // TO DO
    }


}

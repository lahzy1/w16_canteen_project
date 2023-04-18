package com.example.w16_canteen_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {
    @FXML
    private Label labelDescription, labelPrice, LabelItemInBasket, LabelBalance, labelTotal;

    @FXML
    private Button btnLunch, btnDrinks, btnSandwiches, btnSnacks, btnAddToBasket, btnViewBasket ,btnLogout, btnLogin;

    @FXML
    private TextField tfSearch;

    @FXML
    private ImageView ImageviewItem;

    @FXML
    protected void onLoginClick() {
        CanteenApplication.changeScene(ControllerNames.MainMenu);
    }
}
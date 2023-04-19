package com.example.w16_canteen_project;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

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
        // Popup with username and password
        Dialog dialog = new Dialog();
        dialog.setTitle("Login");
        Label usernameLabel = new Label("Username");
        TextField username = new TextField();
        Label passwordLabel = new Label("Password");
        TextField password = new TextField();
        VBox dialogVbox = new VBox(5);
        dialogVbox.getChildren().addAll(usernameLabel, username, passwordLabel, password);
        dialog.getDialogPane().setContent(dialogVbox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.OK) {
            // Check if username and password is correct
            // If correct, change scene to MainMenu
            // If not correct, show error message
            if (CanteenApplication.db.login(username.getText(), password.getText())) {
                CanteenApplication.changeScene(ControllerNames.MainMenu);
                CanteenApplication.mainMenuController.updateTextLabels();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong username or password");
                alert.setContentText("Please try again");
                alert.showAndWait();
            }
        }

    }
}
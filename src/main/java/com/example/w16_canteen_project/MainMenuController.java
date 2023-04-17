package com.example.w16_canteen_project;

import Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Item> subMenuTableView;

    @FXML
    private TextField tfSearch;

    private ObservableList<Item> items;

    @FXML
    protected void onLogoutClick() {
        CanteenApplication.changeScene(ControllerNames.Login);
    }

    @FXML
    protected void onLunchClick() {
        // TODO
    }

    @FXML
    protected void onSandwichesClick() {
        DBDAOImpl db = new DBDAOImpl();
        setCellTables();
        items = FXCollections.observableArrayList();
        items.clear();
        items.addAll(db.getAllItems("Sandwiches"));
        subMenuTableView.setItems(items);
    }

    @FXML
    protected void onDrinksClick() {
        // Load all drinks in tableview from DB
        DBDAOImpl db = new DBDAOImpl();
        setCellTables();
        items = FXCollections.observableArrayList();
        items.clear();
        items.addAll(db.getAllItems("Drinks"));
        subMenuTableView.setItems(items);
    }

    private void setCellTables() {
        subMenuTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(""));
        subMenuTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        subMenuTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Price"));
        subMenuTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Description"));
    }


}

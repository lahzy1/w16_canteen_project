package com.example.w16_canteen_project;

import Model.Basket;
import Model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.security.URIParameter;

public class MainMenuController {

    @FXML
    private Label LabelBalance;

    @FXML
    private Button btnDrinks, btnLogout, btnLunch, btnSandwiches, btnSnacks, btnViewBasket;

    @FXML
    private Label labelTotal, labelSubMenu, LabelItemInBasket, labelTotal1, labelSubMenu1, LabelItemInBasket1;

    @FXML
    private AnchorPane checkOutMenu, subMenu;

    @FXML
    private TableView<Item> subMenuTableView, basketTableView;

    @FXML
    private TextField tfSearch;

    Basket basket = new Basket();

    @FXML
    protected void onLogoutClick() {
        CanteenApplication.changeScene(ControllerNames.Login);
        basket.clearBasket();
        changeViewToSubMenu();
        updateTextLabels();
        subMenuTableView.getItems().clear();
    }

    @FXML
    protected void onLunchClick() {
        changeViewToSubMenu();
        DBDAO db = new DBDAOImpl();
        setSubMenuCellTables();
        labelSubMenu.setText("Lunch");
        subMenuTableView.setItems(db.getAllItems("Lunch"));
    }

    @FXML
    protected void onSandwichesClick() {
        changeViewToSubMenu();
        DBDAO db = new DBDAOImpl();
        setSubMenuCellTables();
        labelSubMenu.setText("Sandwiches");
        subMenuTableView.setItems(db.getAllItems("Sandwiches"));
    }

    @FXML
    protected void onDrinksClick() {
        changeViewToSubMenu();
        DBDAO db = new DBDAOImpl();
        setSubMenuCellTables();
        labelSubMenu.setText("Drinks");
        subMenuTableView.setItems(db.getAllItems("Drinks"));
    }

    @FXML
    protected void onSnacksClick() {
        changeViewToSubMenu();
        DBDAO db = new DBDAOImpl();
        setSubMenuCellTables();
        labelSubMenu.setText("Snacks");
        subMenuTableView.setItems(db.getAllItems("Snacks"));
    }

    @FXML
    protected void viewBasket() {
        changeViewToBasket();
        setBasketCellTables();
        basketTableView.setItems(basket.getBasketItems());
        updateTextLabels();
    }

    @FXML
    protected void onCheckOutClick() {

    }

    private void setSubMenuCellTables() {
        subMenuTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(""));
        subMenuTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        subMenuTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Price"));
        subMenuTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Description"));

        subMenuTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (!subMenuTableView.getSelectionModel().isEmpty())
                {
                    Item selectedItem = subMenuTableView.getSelectionModel().getSelectedItem();
                    basket.addItemToBasket(selectedItem);
                    updateTextLabels();
                }
                else {
                    System.out.println("No item selected");
                }
            }
        });
    }

    private void setBasketCellTables() {
        basketTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>(""));
        basketTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        basketTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Price"));
        basketTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Description"));

        basketTableView.setOnMouseClicked(event -> {
            if (event.getButton().toString().equals("SECONDARY")) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem removeItem = new MenuItem("Remove item");
                contextMenu.getItems().add(removeItem);
                basketTableView.setContextMenu(contextMenu);
                removeItem.setOnAction(event1 -> {
                    if (!basketTableView.getSelectionModel().isEmpty())
                    {
                        Item selectedItem = basketTableView.getSelectionModel().getSelectedItem();
                        basket.getBasketItems().remove(selectedItem);
                        updateTextLabels();
                    }
                    else {
                        System.out.println("No item selected");
                    }
                });
            }

        });
    }

    public void changeViewToSubMenu() {
        subMenu.setVisible(true);
        subMenu.setDisable(false);
        checkOutMenu.setVisible(false);
        checkOutMenu.setDisable(true);
    }

    public void changeViewToBasket() {
        subMenu.setVisible(false);
        subMenu.setDisable(true);
        checkOutMenu.setVisible(true);
        checkOutMenu.setDisable(false);
    }

    public void updateTextLabels() {
        LabelItemInBasket.setText("Items in basket: " + String.valueOf(basket.getBasketItems().size()));
        labelTotal.setText("Total cost: " + String.valueOf(basket.getTotal()));
        LabelItemInBasket1.setText("Items in basket: " + String.valueOf(basket.getBasketItems().size()));
        labelTotal1.setText("Total cost: " + String.valueOf(basket.getTotal()));
    }


}


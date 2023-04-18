package com.example.w16_canteen_project;

import Model.Basket;
import Model.Item;
import Model.Items;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.security.URIParameter;

public class MainMenuController {

    @FXML
    private Label LabelBalance, LabelBalance1, labelTotal, labelSubMenu,
            LabelItemInBasket, labelTotal1, labelSubMenu1, LabelItemInBasket1, usernameLabel;

    @FXML
    private Button btnDrinks, btnLogout, btnLunch, btnSandwiches, btnSnacks, btnViewBasket;

    @FXML
    private AnchorPane checkOutMenu, subMenu;

    @FXML
    private TableView<Item> subMenuTableView, basketTableView;

    @FXML
    private TextField tfSearch;


    private void initialize()
    {
        updateTextLabels();
        tfSearch.setPromptText("Search");
        tfSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue)
            {
                // TODO lav search method for alle items


            }
        });

    }

    @FXML
    protected void onLogoutClick() {
        CanteenApplication.changeScene(ControllerNames.Login);
        CanteenApplication.basket.clearBasket();
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
        basketTableView.setItems(CanteenApplication.basket.getBasketItems().getItems());
        updateTextLabels();
    }


    @FXML
    protected void onCheckOutClick() {
        // TODO
        // add order to DB
        if (CanteenApplication.db.addOrder(CanteenApplication.basket.getBasketItems(), CanteenApplication.employee))
        {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order succesful");
            dialog.setHeaderText("Your payment was succesful!");
            ButtonType buttonTypeOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
            dialog.showAndWait();
            onLogoutClick();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Your payment was unsuccesful!");
            alert.setContentText("Insufficient funds");
            alert.showAndWait();
        }





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
                    CanteenApplication.basket.addItemToBasket(selectedItem);
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
                        CanteenApplication.basket.removeItemFromBasket(selectedItem);
                        basketTableView.setItems(CanteenApplication.basket.getBasketItems().getItems());
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
        LabelItemInBasket.setText("Items in basket: " + String.valueOf(CanteenApplication.basket.getBasketItems().getSize()));
        labelTotal.setText("Total cost: " + String.valueOf(CanteenApplication.basket.getTotal()));
        LabelItemInBasket1.setText("Items in basket: " + String.valueOf(CanteenApplication.basket.getBasketItems().getSize()));
        labelTotal1.setText("Total cost: " + String.valueOf(CanteenApplication.basket.getTotal()));
        LabelBalance.setText("Balance: " + String.valueOf(CanteenApplication.employee.getEmployeeBalance()));
        LabelBalance1.setText("Balance: " + String.valueOf(CanteenApplication.employee.getEmployeeBalance()));
        usernameLabel.setText("Logged in as: "+ CanteenApplication.employee.getEmployeeUsername());
    }


}


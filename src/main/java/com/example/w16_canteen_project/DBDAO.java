package com.example.w16_canteen_project;

import Model.Employee;
import Model.Item;
import Model.Items;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public interface DBDAO {

    void addItem(Item item);
    void deleteItem(Item item);
    void updateItem(Item item);
    abstract Item getItem(int id);
    ObservableList<Item> getAllItems(String category);
    ObservableList<Item> getAllItems();

    boolean addOrder(Items items, Employee employee);

    boolean login(String username, String password);
}

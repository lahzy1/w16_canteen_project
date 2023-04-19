package com.example.w16_canteen_project;

import Model.Employee;
import Model.Item;
import Model.Items;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public interface DBDAO {

    ObservableList<Item> getAllItems(String category);

    boolean addOrder(Items items, Employee employee);

    boolean login(String username, String password);
}

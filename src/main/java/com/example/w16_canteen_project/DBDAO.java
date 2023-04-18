package com.example.w16_canteen_project;

import Model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DBDAO {

    void addItem(Item item);
    void deleteItem(Item item) throws SQLException;
    void updateItem(Item item) throws SQLException;
    abstract Item getItem(int id);
    ArrayList<Item> getAllItems(String category);

}

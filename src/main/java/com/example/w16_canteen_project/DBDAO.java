package com.example.w16_canteen_project;

import Model.Item;

import java.util.ArrayList;

public interface DBDAO {

    void addItem(Item item);
    void deleteItem(Item item);
    void updateItem(Item item);
    abstract Item getItem(int id);
    ArrayList<Item> getAllItems();

}

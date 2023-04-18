package com.example.w16_canteen_project;

import Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBDAOImpl implements DBDAO {

    private Connection con;
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String port;
    public static String databaseName;
    public static String userName;
    public static String password;

    String name;
    double price;
    String description;
    String image;
    Item item;
    ObservableList<Item> items = FXCollections.observableArrayList();

    public DBDAOImpl()
    {
        try {
            Properties props = new Properties();
            String fileName = "db.properties";
            InputStream input;
            try{
                input = new FileInputStream(fileName);
                props.load(input);
                port = props.getProperty("port","1433");
                databaseName = props.getProperty("databaseName");
                userName=props.getProperty("userName", "sa");
                password=props.getProperty("password");
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //System.out.println("Database Ready");

            }catch(IOException | ClassNotFoundException e){
                System.err.println(e.getMessage());
            }
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:"+port+";databaseName="+databaseName,userName,password);
            System.out.println("Connection successful!");
            System.out.println();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void addItem(Item item) {
        try {
            ps = con.prepareStatement("Insert into tblItem values (?,?,?,?)");
            ps.setString(1,item.getName());
            ps.setString(2,item.getDescription());
            ps.setDouble(3,item.getPrice());
            ps.setString(4,item.getImage());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(Item item) {

    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public Item getItem(int id) {
        return null;
    }

    @Override
    public ObservableList<Item> getAllItems(String category) {
        try {
            ps = con.prepareStatement("Select * from tblItem where fldCategory = ?");
            ps.setString(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("fldName");
                price = rs.getDouble("fldPrice");
                description = rs.getString("fldDescription");
                image = rs.getString("fldImage");
                item = new Item(name, price, description, image);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Item> getAllItems() {
        return items;
    }


}

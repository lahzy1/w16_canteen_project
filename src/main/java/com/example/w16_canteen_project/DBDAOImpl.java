package com.example.w16_canteen_project;

import Model.Item;

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
    public ArrayList<Item> getAllItems(String category) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            ps = con.prepareStatement("Select * from tblItem where fldCategory = ?");
            ps.setString(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("fldName");
                double price = rs.getDouble("fldPrice");
                String description = rs.getString("fldDescription");
                String image = rs.getString("fldImage");
                Item item = new Item(name, price, description, image);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

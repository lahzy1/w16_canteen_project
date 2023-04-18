package com.example.w16_canteen_project;

import Model.Employee;
import Model.Item;
import Model.Items;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBDAOImpl implements DBDAO {

    private Connection con;
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String port;
    public static String databaseName;
    public static String userName;
    public static String password;

    String itemName;
    double price;
    String description;
    String image;
    Item item;
    int itemID;

    Items items = new Items();

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
        try {
            PreparedStatement ps = con.prepareStatement("delete from tblItem where fldName=?");
            ps.setString(1, item.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateItem(Item item) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE project" + "SET fldName = ?" +
                    "SET fldPrice = ?" + "SET fldDescription = ?" + "SET fldImage = ?" + "SET Category = ?" +
                    "SET fldCurrentStock = ?" + "SET fldMinimumStock = ?" + "WHERE fldItemID = ?;");
            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getImage());
            ps.setString(5, item.getCategory());
            ps.setString(6, item.getCurrentStock());
            ps.setString(7, item.getMinimumStock());
            ps.setInt(8, item.getItemID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }



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
                itemName = rs.getString("fldName");
                price = rs.getDouble("fldPrice");
                description = rs.getString("fldDescription");
                image = rs.getString("fldImage");
                itemID = rs.getInt("fldItemID");
                item = new Item(itemName, price, description, image, itemID);
                items.addItem(item);
            }
            return items.getItems();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Item> getAllItems() {
        return items.getItems();
    }



    @Override
    public boolean addOrder(Items items, Employee employee) {
        if (CanteenApplication.employee.getEmployeeBalance() >= items.getTotal()) {
            try {
                ps = con.prepareStatement("INSERT INTO tblOrder (fldEmployeeID, fldNumberOfItems" +
                        ", fldTotalCost) VALUES (?, ?, ?)");
                ps.setInt(1, Integer.parseInt(employee.getEmployeeID()));
                ps.setInt(2, items.getSize());
                ps.setDouble(3, items.getTotal());
                ps.executeUpdate();
                updateBalance(employee, items.getTotal());
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            return false;
        }
        return false;
    }

    @Override
    public boolean login(String userName, String password) {
        try {
            int employeeID;
            double employeeBalance;
            //Get username and password from login page
            ps = con.prepareStatement("Select * from tblLogin where fldUsername = ? and fldPassword = ?");
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = con.prepareStatement(("SELECT fldEmployeeID FROM tblLogin WHERE fldUsername = ? AND fldPassword = ?"));
                ps.setString(1, userName);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    employeeID = rs.getInt("fldEmployeeID");
                    ps = con.prepareStatement("SELECT fldAccountBalance FROM tblEmployee WHERE fldEmployeeID = ?");
                    ps.setInt(1, employeeID);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        employeeBalance = Double.parseDouble(rs.getString("fldAccountBalance"));
                        CanteenApplication.employee = new Employee(userName, password, employeeID, employeeBalance);
                        return true;
                    }
                }
            } else {
                System.out.println("Login Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void updateBalance(Employee employee, double v) {
        try {
            ps = con.prepareStatement("UPDATE tblEmployee SET fldAccountBalance = ? WHERE fldEmployeeID = ?");
            ps.setDouble(1, employee.getEmployeeBalance() - v);
            ps.setInt(2, Integer.parseInt(employee.getEmployeeID()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

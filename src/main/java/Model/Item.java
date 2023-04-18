package Model;

import com.example.w16_canteen_project.*;

public class Item {
    private String name;
    private Double price;
    private String description;
    private String image;

    private String category;

    private String currentStock;

    private String minimumStock;

    private int itemID;

    public Item(String name, Double price, String description,  String image, int itemID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getCurrentStock() {
        return currentStock;
    }

    public String getMinimumStock() {
        return minimumStock;
    }

    public int getItemID() {
        return itemID;
    }
}


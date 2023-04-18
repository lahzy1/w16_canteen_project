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

    private int itemId;

    public Item(String name, Double price, String description,  String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
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

    public int getItemId() {
        return itemId;
    }
}


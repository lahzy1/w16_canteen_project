package Model;

import com.example.w16_canteen_project.*;

public class Item {
    private String name;
    private Double price;
    private String description;
    private String image;

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

}


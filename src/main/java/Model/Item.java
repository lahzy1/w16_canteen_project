package Model;

public class Item {
    private String name;
    private Double price;
    private String description;
    private String image;

    public Item(String image, String name, Double price, String description) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
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


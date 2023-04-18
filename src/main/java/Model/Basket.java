package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Basket {

    private ObservableList<Item> basketItems = FXCollections.observableArrayList();

    public void addItemToBasket(Item item) {
        basketItems.add(item);
    }
    private int quantity;
    private double total;

    private Item item;


    //get items
    public ObservableList<Item> getBasketItems() {
        return basketItems;
    }

    public String getTotal() {
        total = 0;
        for (Item item : basketItems) {
            total += item.getPrice();
        }
        return String.format("%.2f", total);
    }

    // clear basket
    public void clearBasket() {
        basketItems.clear();
    }











}

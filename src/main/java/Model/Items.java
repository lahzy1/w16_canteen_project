package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Items {

    ObservableList<Item> items = FXCollections.observableArrayList();

    public ObservableList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    // Add prices of all items in basket
    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // size of basket
    public int getSize() {
        return items.size();
    }


}

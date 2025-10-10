package task2;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<String, Double> cart;

    /**
     * Shopping cart class constructor
     */
    public ShoppingCart() {
        cart = new HashMap<>();
    }

    /**
     * Add item to shopping cart
     * @param name item name
     * @param price item price
     */
    public void addItem(String name, double price) {
        cart.put(name, price);
    }

    /**
     * Remove item from shopping cart
     * @param name name of item to be removed
     */
    public void removeItem(String name) {
        cart.remove(name);
    }

    /**
     * Calculate total price of all products in the shopping cart
     * @return total price of all products in the shopping cart
     */
    public double calculateTotal() {
        // define total variable (as an array due to closure)
        double[] total = new double[1];
        // set initial value
        total[0] = 0;
        // iterate through each item
        cart.forEach((name, price) -> {
            // add the price of each item
            total[0] += price;
        });
        // return total costs
        return total[0];
    }

    /**
     * Get total amount of items in the shopping cart
     * @return total amount of items in the shopping cart
     */
    public int getItemCount() {
        return cart.size();
    }

}

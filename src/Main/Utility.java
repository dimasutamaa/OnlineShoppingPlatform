package Main;

import java.util.ArrayList;

public class Utility {
    public static void printHeader(String header){
        System.out.println("\n=== " + header + " ===");
    }

    public static void init(ArrayList<User> users, ArrayList<Product> products){
        users.add(new Customer("C001", "John Doe", "johnDoe@gmail.com", "Jl. Sakura 1"));
        products.add(new Clothing("P001", "T-Shirt - Blue", 19.99, "M", 5));
        products.add(new Clothing("P002", "Jeans - Slim Fit", 39.99, "XL", 4));
        products.add(new Electronics("P003", "Smartphone - Model X", 499.99, "TechCo", 1));
        products.add(new Electronics("P004", "Laptop - UltraBook", 899.99, "MegaElect", 0));
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printAlert(String alert){
        System.out.println("!!! " + alert + " !!!");
    }
}

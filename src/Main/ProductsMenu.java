package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductsMenu {
    private ArrayList<Product> products;
    private Scanner in;
    private User loggedUser;

    public ProductsMenu(ArrayList<Product> products, Scanner in, User loggedUser) {
        this.products = products;
        this.in = in;
        this.loggedUser = loggedUser;
    }

    public void displayProductsMenu(){
        Utility.clearScreen();

        while(true){
            Utility.printHeader("Shop for Products");
            System.out.println("1. View All Products");
            System.out.println("2. Add Clothing to Cart");
            System.out.println("3. Add Electronics to Cart");
            System.out.println("4. Back to Main Menu");
            System.out.print("Your Choice: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    viewAllProducts();
                    break;

                case "2":
                    addClothingToCart();
                    break;

                case "3":
                    addElectronicsToCart();
                    break;

                case "4":
                    Utility.clearScreen();
                    return;
            
                default:
                    Utility.printAlert("Invalid Choice");
                    break;
            }
        }
    }

    public void viewAllProducts(){
        Utility.printHeader("View All Products");
        System.out.printf("%-5s | %-30s | %-13s | %-15s\n", "ID", "Product Name", "Price", "Availability");
        System.out.println("----------------------------------------------------------------------");
        
        for (Product product : products) {
            String isAvalable = (product.isAvailable() == true) ? "In Stock" : "Out of Stock";
            System.out.printf("%-5s | %-30s | $%-12s | %-15s\n", product.getProductId(), product.getProductName(), product.getPrice(), isAvalable);
        }
        System.out.println("----------------------------------------------------------------------\n");
        
        System.out.print("Press Enter to go back to Shop for Products Menu..."); in.nextLine();
        Utility.clearScreen();
    }

    public void addClothingToCart(){    
        Utility.printHeader("Add Clothing to Cart");
        System.out.printf("%-5s | %-30s | %-13s | %-13s | %-15s\n", "ID", "Product Name", "Price", "Size", "Availability");
        System.out.println("--------------------------------------------------------------------------------------");
        
        for (Product product : products) {
            if(product instanceof Clothing){
                ((Clothing) product).displayProductInfo();
            }
        }
        System.out.println("--------------------------------------------------------------------------------------\n");

        while(true){
            System.out.print("Enter the ID to add to your cart (or type 'back' to go back): ");
            String input = in.nextLine();
    
            if(input.equalsIgnoreCase("back")){
                Utility.clearScreen();
                return;
            }else{
                Product product = findProduct(input);
    
                if(product != null){
                    if(product instanceof Clothing){
                        Clothing item = (Clothing) product;
                        if(item.isAvailable() == false){
                            Utility.printAlert("Product is out of stock");
                        }else{
                            Customer customer = (Customer) loggedUser;
                            customer.addToCart(product);
                            Utility.printAlert("Successfully add the product to your cart");
                        }
                    }else{
                        Utility.printAlert("Desired product is not in this category");
                    }
                }else if(!(input.contains("P"))){
                    Utility.printAlert("Invalid Product ID");
                }else{
                    Utility.printAlert("Product is not available");
                }
            }
        }
    }

    public void addElectronicsToCart(){
        Utility.printHeader("Add Electronics to Cart");
        System.out.printf("%-5s | %-30s | %-13s | %-13s | %-15s\n", "ID", "Product Name", "Price", "Brand", "Availability");
        System.out.println("--------------------------------------------------------------------------------------");
        
        for (Product product : products) {
            if(product instanceof Electronics){
                ((Electronics) product).displayProductInfo();
            }
        }
        System.out.println("--------------------------------------------------------------------------------------\n");

        while(true){
            System.out.print("Enter the ID to add to your cart (or type 'back' to go back): ");
            String input = in.nextLine();
            
            if(input.equalsIgnoreCase("back")){
                Utility.clearScreen();
                return;
            }else{
                Product product = findProduct(input);
                
                if(product != null){
                    if(product instanceof Electronics){
                        Electronics item = (Electronics) product;
                        if(item.isAvailable() == false){
                            Utility.printAlert("Product is out of stock");
                        }else{
                            Customer customer = (Customer) loggedUser;
                            customer.addToCart(product);
                            Utility.printAlert("Successfully add the product to your cart");
                        }
                    }else{
                        Utility.printAlert("Desired product is not in this category");
                    }
                }else if(!(input.contains("P"))){
                    Utility.printAlert("Invalid Product ID");
                }else{
                    Utility.printAlert("Product is not available");
                }
            }
        }
    }

    public Product findProduct(String id){
        for (Product product : products) {
            if(product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }
}

package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingSystem {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private Scanner in = new Scanner(System.in);
    private User loggedUser = null;

    public ShoppingSystem(){
        Utility.init(users, products);
        inputCustomerID();
        displayMainMenu();
    }

    public void displayMainMenu(){
        while(true){
            Utility.printHeader("Online Shopping Platform");
            System.out.println("1. Shop for Products");
            System.out.println("2. View Shopping Cart");
            System.out.println("3. View Orders");
            System.out.println("4. Customer Info");
            System.out.println("5. Exit");
            System.out.print("Your Choice: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    Utility.clearScreen();
                    displayProductsMenu();
                    break;

                case "2":
                    displayCartMenu();
                    break;

                case "3":
                    displayOrdersMenu();
                    break;

                case "4":
                    displayCustomerInfo();
                    break;

                case "5":
                    Utility.printAlert("Thank you for using our application");
                    exit();
                    break;
            
                default:
                    Utility.printAlert("Invalid Choice");
                    break;
            }
        }
    }

    public void displayProductsMenu(){
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
                    Utility.clearScreen();
                    displayProductsMenu();
                    break;

                case "2":
                    addClothingToCart();
                    break;

                case "3":
                    addElectronicsToCart();
                    break;

                case "4":
                    Utility.clearScreen();
                    displayMainMenu();
            
                default:
                    Utility.printAlert("Invalid Choice");
                    break;
            }
        }
    }

    public void displayCartMenu(){

    }

    public void displayOrdersMenu(){

    }

    public void displayCustomerInfo(){
        Customer customer = (Customer) loggedUser;
        //
    }

    public void exit(){
        System.exit(0);
    }

    public void inputCustomerID(){
        Utility.printHeader("Online Shopping Platform");
        
        String id;
        do {
            System.out.print("Input Customer ID: "); 
            id = in.nextLine();

            for (User user : users) {
                if(user.getUserId().equals(id)){
                    loggedUser = user;
                    break;
                }
            }
        } while (loggedUser == null);

        Utility.clearScreen();
    }

    /* Products Menu Cases Start */
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
    }

    public void addClothingToCart(){    
        Utility.printHeader("Add Clothing to Cart");
        System.out.printf("%-5s | %-30s | %-13s | %-13s | %-15s\n", "ID", "Product Name", "Price", "Size", "Availability");
        System.out.println("--------------------------------------------------------------------------------------");
        
        for (Product product : products) {
            if(product instanceof Clothing){
                Clothing item = (Clothing) product;
                String isAvalable = (item.isAvailable() == true) ? "In Stock" : "Out of Stock";
                System.out.printf("%-5s | %-30s | $%-12s | %-13s | %-15s\n", item.getProductId(), item.getProductName(), item.getPrice(), item.getSize(), isAvalable);
            }
        }
        System.out.println("--------------------------------------------------------------------------------------\n");
        
        System.out.print("Enter the ID to add to your cart (or type 'back' to go back): ");
        String input = in.nextLine();

        if(input.equalsIgnoreCase("back")){
            Utility.clearScreen();
            displayProductsMenu();
        }else{
            Product product = findProduct(input);

            if(product != null){
                if(product instanceof Clothing){
                    Customer customer = (Customer) loggedUser;
                    customer.addToCart(product);
                    Utility.printAlert("Successfully add the product to your cart");
                }else{
                    Utility.printAlert("Desired product is not in this category");
                }
            }else{
                Utility.printAlert("Product is not available");
            }
        }
    }

    public void addElectronicsToCart(){
        Utility.printHeader("Add Electronics to Cart");
        System.out.printf("%-5s | %-30s | %-13s | %-13s | %-15s\n", "ID", "Product Name", "Price", "Brand", "Availability");
        System.out.println("--------------------------------------------------------------------------------------");
        
        for (Product product : products) {
            if(product instanceof Electronics){
                Electronics item = (Electronics) product;
                String isAvalable = (item.isAvailable() == true) ? "In Stock" : "Out of Stock";
                System.out.printf("%-5s | %-30s | $%-12s | %-12s | %-15s\n", item.getProductId(), item.getProductName(), item.getPrice(), item.getBrand(), isAvalable);
            }
        }
        System.out.println("--------------------------------------------------------------------------------------\n");
        
        System.out.print("Enter the ID to add to your cart (or type 'back' to go back): ");
        String input = in.nextLine();

        if(input.equalsIgnoreCase("back")){
            Utility.clearScreen();
            displayProductsMenu();
        }else{
            Product product = findProduct(input);

            if(product != null){
                if(product instanceof Electronics){
                    Customer customer = (Customer) loggedUser;
                    customer.addToCart(product);
                    Utility.printAlert("Successfully add the product to your cart");
                }else{
                    Utility.printAlert("Desired product is not in this category");
                }
            }else{
                Utility.printAlert("Product is not available");
            }
        }
    }
    /* Products Menu Cases End */

    public Product findProduct(String id){
        for (Product product : products) {
            if(product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }
}

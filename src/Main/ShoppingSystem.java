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
                    Utility.clearScreen();
                    displayCartMenu();
                    break;

                case "3":
                    Utility.clearScreen();
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

    public void displayCartMenu(){
        while(true){
            Utility.printHeader("View Shopping Cart");
            System.out.println("1. View Cart Contents");
            System.out.println("2. Checkout");
            System.out.println("3. Back to Main Menu");
            System.out.print("Your Choice: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    viewCartContents();
                    Utility.clearScreen();
                    break;
            
                case "2":
                    checkOut();
                    break;
            
                case "3":
                    Utility.clearScreen();
                    return;
            
                default:
                    Utility.printAlert("Invalid Choice");
                    break;
            }
        }
    }

    public void displayOrdersMenu(){
        while(true){
            Utility.printHeader("View Orders");
            System.out.println("1. View Order History");
            System.out.println("2. Track Order");
            System.out.println("3. Back to Main Menu");
            System.out.print("Your Choice: ");
            String choice = in.nextLine();

            switch (choice) {
                case "1":
                    viewOrderHistory();
                    break;
            
                case "2":
                    trackOrder();
                    break;
            
                case "3":
                    Utility.clearScreen();
                    return;
            
                default:
                    Utility.printAlert("Invalid Choice");
                    break;
            }
        }
    }

    public void displayCustomerInfo(){
        Customer customer = (Customer) loggedUser;
        
        Utility.printHeader("Customer Info");
        customer.displayCustomerInfo();
        System.out.println("Press Enter to back to Main Menu..."); in.nextLine();

        Utility.clearScreen();
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
        
        boolean isValid = false;

        while(!(isValid)){
            System.out.print("Enter the ID to add to your cart (or type 'back' to go back): ");
            String input = in.nextLine();
    
            if(input.equalsIgnoreCase("back")){
                Utility.clearScreen();
                isValid = true;
            }else{
                Product product = findProduct(input);
    
                if(product != null){
                    if(product instanceof Clothing){
                        Customer customer = (Customer) loggedUser;
                        customer.addToCart(product);
                        Utility.printAlert("Successfully add the product to your cart");
                        return;
                    }else{
                        Utility.printAlert("Desired product is not in this category");
                        isValid = true;
                    }
                }else{
                    Utility.printAlert("Product is not available");
                    isValid = true;
                }
                isValid = false;             
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
        
        boolean isValid = false;

        while(!(isValid)){
            System.out.print("Enter the ID to add to your cart (or type 'back' to go back): ");
            String input = in.nextLine();
            
            if(input.equalsIgnoreCase("back")){
                Utility.clearScreen();
                return;
            }else{
                Product product = findProduct(input);
                
                if(product != null){
                    if(product instanceof Electronics){
                        Customer customer = (Customer) loggedUser;
                        customer.addToCart(product);
                        Utility.printAlert("Successfully add the product to your cart");
                        return;
                    }else{
                        Utility.printAlert("Desired product is not in this category");
                        isValid = true;
                    }
                }else{
                    Utility.printAlert("Product is not available");
                    isValid = true;
                }
                isValid = false;   
            }
        }
    }
    /* Products Menu Cases End */
    
    /* Cart Menu Cases Start */
    public void viewCartContents(){
        Customer customer = (Customer) loggedUser;

        Utility.printHeader("View Cart Contents");   
        customer.viewCart();
        System.out.println("Press Enter to go back to View Shopping Cart Menu..."); in.nextLine();
    }

    public void checkOut(){
        Customer customer = (Customer) loggedUser;
        boolean isValid = false;

        Utility.printHeader("Checkout");
        System.out.println("Review your order:");
        if(!(customer.getCart().isEmpty())){
            customer.viewCart();

            while(!(isValid)){
                System.out.print("Do you want to proceed with the checkout? (Y/N): ");
                String confirm = in.nextLine();

                if(confirm.equalsIgnoreCase("Y")){
                    Order.processOrder(customer);
                    isValid = true;
                }else if(confirm.equalsIgnoreCase("N")){
                    isValid = true;
                }else{
                    isValid = false;
                }
            }
        }else{
            customer.viewCart();
            System.out.println("Press Enter to go back to View Shopping Cart Menu..."); in.nextLine();
            Utility.clearScreen();
        }
    }
    /* Cart Menu Cases End */

    /* Orders Menu Cases Start */
    public void viewOrderHistory(){
        boolean isValid = false;
        Customer customer = (Customer) loggedUser;

        Utility.printHeader("View Order History");
        customer.viewOrders();

        while(!(isValid)){
            System.out.print("Enter the Order ID to view details (or type 'back' to go back): ");
            String input = in.nextLine();
    
            Order order = customer.findOrder(input);
    
            if(order != null){
                order.displayOrderInfo();
                System.out.print("Press Enter to go back to View Order History..."); in.nextLine();
                isValid = true;
            }else if(input.equalsIgnoreCase("back")){
                Utility.clearScreen();
                return;
            }else{
                isValid = false;
            }
        }
    }

    public void trackOrder(){
        boolean isValid = false;
        Customer customer = (Customer) loggedUser;

        Utility.printHeader("Track Order");

        while(!(isValid)){
            System.out.print("Enter the Order ID to track (or type 'back' to go back): ");
            String input = in.nextLine();
    
            Order order = customer.findOrder(input);
    
            if(order != null){
                Utility.printHeader("Order Tracking");
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Order Status: " + order.getStatus());
    
                System.out.println("\nPress Enter to go back to Track Order Menu..."); in.nextLine();
                Utility.clearScreen();
                return;
            }else{
                isValid = false;
            }
        }
    }
    /* Orders Menu Cases End */

    public Product findProduct(String id){
        for (Product product : products) {
            if(product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }
}

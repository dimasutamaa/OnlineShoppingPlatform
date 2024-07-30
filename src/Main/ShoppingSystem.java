package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingSystem {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private Scanner in = new Scanner(System.in);
    private User loggedUser = null;

    public ShoppingSystem(){
        init();
        inputCustomerID();
        displayMainMenu();
    }

    public void displayMainMenu(){
        ProductsMenu productsMenu = new ProductsMenu(products, in, loggedUser);
        CartMenu cartMenu = new CartMenu(in, loggedUser);
        OrdersMenu ordersMenu = new OrdersMenu(in, loggedUser);

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
                    productsMenu.displayProductsMenu();
                    break;

                case "2":
                    cartMenu.displayCartMenu();
                    break;

                case "3":
                    ordersMenu.displayOrdersMenu();
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

    public void init(){
        users.add(new Customer("C001", "John Doe", "johnDoe@gmail.com", "Jl. Sakura 1"));
        products.add(new Clothing("P001", "T-Shirt - Blue", 19.99, "M", 5));
        products.add(new Clothing("P002", "Jeans - Slim Fit", 39.99, "XL", 4));
        products.add(new Electronics("P003", "Smartphone - Model X", 499.99, "TechCo", 1));
        products.add(new Electronics("P004", "Laptop - UltraBook", 899.99, "MegaElect", 0));
    }
}

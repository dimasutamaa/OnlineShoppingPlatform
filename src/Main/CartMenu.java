package Main;

import java.util.Scanner;

public class CartMenu {
    private Scanner in;
    private User loggedUser;
    
    public CartMenu(Scanner in, User loggedUser) {
        this.in = in;
        this.loggedUser = loggedUser;
    }

    public void displayCartMenu(){
        Utility.clearScreen();

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

    public void viewCartContents(){
        Customer customer = (Customer) loggedUser;

        Utility.printHeader("View Cart Contents");   
        customer.viewCart();
        System.out.println("Press Enter to go back to View Shopping Cart Menu..."); in.nextLine();
        Utility.clearScreen();
    }

    public void checkOut(){
        Order order = new Order();
        Customer customer = (Customer) loggedUser;
        
        Utility.printHeader("Checkout");
        System.out.println("Review your order:");
        
        if(customer.getCart().isEmpty()){
            customer.viewCart();
            System.out.println("Press Enter to go back to View Shopping Cart Menu..."); in.nextLine();
            Utility.clearScreen();
        }else{
            customer.viewCart();
            
            while(true){
                System.out.print("Do you want to proceed with the checkout? (Y/N): ");
                String confirm = in.nextLine();
                
                if(confirm.equalsIgnoreCase("Y")){
                    order.processOrder(customer);
                    return;
                }else if(confirm.equalsIgnoreCase("N")){
                    return;
                }else{
                    Utility.printAlert("Invalid input");
                }
            }
        }
    }    
}

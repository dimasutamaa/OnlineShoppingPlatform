package Main;

import java.util.Scanner;

public class OrdersMenu {
    private Scanner in;
    private User loggedUser;

    public OrdersMenu(Scanner in, User loggedUser) {
        this.in = in;
        this.loggedUser = loggedUser;
    }

    public void displayOrdersMenu(){
        Utility.clearScreen();

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

    public void viewOrderHistory(){
        Customer customer = (Customer) loggedUser;

        Utility.printHeader("View Order History");
        customer.viewOrders();

        while(true){
            System.out.print("Enter the Order ID to view details (or type 'back' to go back): ");
            String input = in.nextLine();
            
            if(input.equalsIgnoreCase("back")){
                Utility.clearScreen();
                return;
            }else{
                Order order = customer.findOrder(input);

                if(order != null){
                    order.displayOrderInfo();
                    System.out.println("Press Enter to go back to View Order History..."); in.nextLine();
                    return;
                }else if(!(input.contains("O"))){
                    Utility.printAlert("Invalid Input");
                }else{
                    Utility.printAlert("Invalid Order ID");
                }
            }
        }
    }

    public void trackOrder(){
        Customer customer = (Customer) loggedUser;

        Utility.printHeader("Track Order");

        while(true){
            System.out.print("Enter the Order ID to track (or type 'back' to go back): ");
            String input = in.nextLine();

            if(input.equalsIgnoreCase("back")){
                Utility.clearScreen();
                return;
            }else{
                Order order = customer.findOrder(input);
    
                if(order != null){
                    Utility.printHeader("Order Tracking");
                    System.out.println("Order ID: " + order.getOrderId());
                    System.out.println("Order Status: " + order.getStatus());
        
                    System.out.println("\nPress Enter to go back to Track Order Menu..."); in.nextLine();
                    Utility.clearScreen();
                    return;
                }else if(!(input.contains("O"))){
                    Utility.printAlert("Invalid Input");
                }else{                
                    Utility.printAlert("Invalid Order ID");
                }
            }
        }
    }
}

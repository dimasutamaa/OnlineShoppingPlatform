package Main;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private String orderId;
    private String orderDate;
    private ArrayList<Product> orderItems = new ArrayList<>();
    private String status;
    private static int orderCount;
    
    public Order(String orderId, String orderDate, ArrayList<Product> orderItems, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.status = status;
    }

    public void displayOrderInfo(){
        Utility.printHeader("Order Details");
        System.out.println("Order ID: " + getOrderId());
        System.out.println("Order Date: " + getOrderDate());
        System.out.printf("Total Price: $%.2f\n", calculateTotalPrice());
        
        System.out.println("\nOrder Items:");
        System.out.printf("%-5s | %-30s | %-13s\n", "ID", "Product Name", "Price");
        System.out.println("-------------------------------------------------");        
        for (Product product : orderItems) {
            System.out.printf("%-5s | %-30s | $%-13s\n", product.getProductId(), product.getProductName(), product.getPrice());  
        }
        System.out.println("-------------------------------------------------\n");
    }

    public Double calculateTotalPrice(){
        Double grandTotal = 0.0;

        for (Product product : orderItems) {
            grandTotal += product.getPrice();
        }

        return grandTotal;
    }

    public static void processOrder(Customer customer){
        orderCount++;

        ArrayList<Product> items = customer.getCart();
        String id = String.format("O%03d", orderCount);
        String date = LocalDate.now().toString();

        Order order = new Order(id, date, items, "Ordered");

        for (Product product : items) {
            product.reduceQuantity();
        }

        customer.placeOrder(order);
        customer.clearCart();
        Utility.printAlert("Order has been placed");
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<Product> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<Product> orderItems) {
        this.orderItems = orderItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static int getOrderCount() {
        return orderCount;
    }

    public static void setOrderCount(int orderCount) {
        Order.orderCount = orderCount;
    }
}

package Main;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private String orderId;
    private String orderDate;
    private ArrayList<Product> orderItems = new ArrayList<>();
    private String status;
    
    public Order(String orderId, String orderDate, ArrayList<Product> orderItems, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.status = status;
    }

    public void displayOrderInfo(){
        System.out.println("ID");
        System.out.println("Date");
        System.out.println("Items:");

        for (Product product : orderItems) {
            System.out.println("- " + product.getProductName());
        }
    }

    public Double calculateTotalPrice(){
        double grandTotal = 0;

        for (Product product : orderItems) {
            grandTotal =+ product.getPrice();
        }

        return grandTotal;
    }

    public static void processOrder(Customer customer){
        int orderCount = 0;

        ArrayList<Product> items = customer.getCart();
        String id = String.format("O%03d", orderCount++);
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
}

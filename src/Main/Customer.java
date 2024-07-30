package Main;

import java.util.ArrayList;

public class Customer extends User {
    private String address;
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Product> cart = new ArrayList<>();
    
    public Customer(String userId, String userName, String email, String address) {
        super(userId, userName, email);
        this.address = address;
    }
    
    @Override
    public void placeOrder(Order order) {
        orders.add(order);
    }

    public void displayCustomerInfo(){
        displayUserInfo();
        System.out.println("Addres      : " + getAddress());
    }

    public void addToCart(Product product){
        cart.add(product);
    }

    public void viewCart(){
        double totalPrice = 0;

        System.out.printf("%-5s | %-30s | %-13s\n", "ID", "Product Name", "Price");
        System.out.println("-------------------------------------------------");

        if(!(getCart().isEmpty())){
            for (Product product : getCart()) {
                System.out.printf("%-5s | %-30s | $%-13s\n", product.getProductId(), product.getProductName(), product.getPrice());
                totalPrice += product.getPrice();     
            }
        }else{
            System.out.printf("%30s\n", "Cart is empty");
        }

        System.out.println("-------------------------------------------------\n");

        System.out.printf("Total Price: $%.2f\n", totalPrice);
    }

    public void viewOrders(){
        System.out.printf("%-7s | %-20s | %-20s\n", "Order ID", "Order Date", "Total Price");
        System.out.println("------------------------------------------------");
        
        for (Order order : orders) {
            System.out.printf("%-8s | %-20s | $%-20.2f\n", order.getOrderId(), order.getOrderDate(), order.calculateTotalPrice());
        }
        System.out.println("------------------------------------------------");
    }

    public Order findOrder(String id){
        for (Order order : orders) {
            if(order.getOrderId().equals(id)){
                return order;
            }
        }
        return null;
    }

    public void clearCart(){
        cart.clear();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Product> getCart() {
        return (ArrayList<Product>) cart.clone();
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
}

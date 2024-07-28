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
        super.placeOrder(order);
        orders.add(order);
    }

    public void displayCustomerInfo(){
        System.out.println("Username: " + getUserName());
    }

    public void addToCart(Product product){
        cart.add(product);
    }

    public void viewCart(){
        double totalPrice = 0.0;

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

        System.out.println("Total Price: $" + totalPrice);
    }

    public void clearCart(){
        getCart().clear();
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

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }
}

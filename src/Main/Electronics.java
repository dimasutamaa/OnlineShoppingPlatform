package Main;

public class Electronics extends Product implements Orderable {
    private String brand;
    private int quantityInStock;
    
    public Electronics(String productId, String productName, Double price, String brand, int quantityInStock) {
        super(productId, productName, price);
        this.brand = brand;
        this.quantityInStock = quantityInStock;
    }

    @Override
    public void displayProductInfo() {
        String isAvalable = (isAvailable() == true) ? "In Stock" : "Out of Stock";
        System.out.printf("%-5s | %-30s | $%-12s | %-13s | %-15s\n", getProductId(), getProductName(), getPrice(), getBrand(), isAvalable);
    }

    @Override
    public Double getPrice() {
        return super.getPrice();
    }

    @Override
    public boolean isAvailable() {
        if(quantityInStock >= 1) return true;
        else return false;
    }

    // @Override
    // public void addToOrder(Order order) {}

    // @Override
    // public void displayOrderDetails() {}

    @Override
    public void reduceQuantity() {
        quantityInStock--;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getquantityInStock() {
        return quantityInStock;
    }

    public void setquantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}

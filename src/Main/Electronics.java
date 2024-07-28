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
        System.out.println("Brand: " + getBrand());
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

    @Override
    public void addToOrder(Order order) {}

    @Override
    public void displayOrderDetails() {}

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

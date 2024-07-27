package Main;

public class Electronics extends Product implements Orderable {
    private String brand;
    private int warrantyPeriod;
    
    public Electronics(String productId, String productName, Double price, String brand, int warrantyPeriod) {
        super(productId, productName, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
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
        if(warrantyPeriod >= 1) return true;
        else return false;
    }

    @Override
    public void addToOrder(Order order) {}

    @Override
    public void displayOrderDetails() {}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}

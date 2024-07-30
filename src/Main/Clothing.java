package Main;

public class Clothing extends Product {
    private String size;
    private int quantityInStock;
    
    public Clothing(String productId, String productName, Double price, String size, int quantityInStock) {
        super(productId, productName, price);
        this.size = size;
        this.quantityInStock = quantityInStock;
    }
    
    @Override
    public void displayProductInfo() {
        String isAvalable = (isAvailable() == true) ? "In Stock" : "Out of Stock";
        System.out.printf("%-5s | %-30s | $%-12s | %-13s | %-15s\n", getProductId(), getProductName(), getPrice(), getSize(), isAvalable);
    }

    @Override
    public boolean isAvailable() {
        if(quantityInStock >= 1) return true;
        else return false;
    }

    @Override
    public Double getPrice() {
        return super.getPrice();
    }

    @Override
    public void reduceQuantity() {
        quantityInStock--;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}

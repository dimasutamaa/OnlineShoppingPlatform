package Main;

public class Clothing extends Product implements Orderable {
    private String size;
    private int quantityInStock;
    
    public Clothing(String productId, String productName, Double price, String size, int quantityInStock) {
        super(productId, productName, price);
        this.size = size;
        this.quantityInStock = quantityInStock;
    }
    
    @Override
    public void displayProductInfo() {
        System.out.println("Size: " + getSize());
    }

    @Override
    public boolean isAvailable() {
        if(quantityInStock > 1) return true;
        else return false;
    }

    @Override
    public Double getPrice() {
        return super.getPrice();
    }

    @Override
    public void addToOrder(Order order) {}

    @Override
    public void displayOrderDetails() {}

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

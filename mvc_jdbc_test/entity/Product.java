package mvc_jdbc_test.entity;

public class Product extends Entity{
    String id;
    String productName;
    int productAmount;
    int productPrice;
    String manufacturer;

    public Product(String id, String productName, int productAmount, int productPrice, String manufacturer) {
        this.id = id;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}

package mvc_jdbc_test.entity;

public class Product {
    String productId;
    String productName;
    int productAmount;
    int productPrice;
    String manufacturer;

    public Product(String productId, String productName, int productAmount, int productPrice, String manufacturer) {
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productPrice = productPrice;
        this.manufacturer = manufacturer;
    }

    public String getProductId() {
        return productId;
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

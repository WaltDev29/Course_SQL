package mvc_jdbc_test.entity;

import java.util.Date;

public class Order extends Entity {
    private String id;
    private String customerId;
    private String orderedProduct;
    private int amount;
    private String deliveryAddress;
    private Date orderDate;

    public Order(String id, String customerId, String orderedProduct, int amount, String deliveryAddress, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderedProduct = orderedProduct;
        this.amount = amount;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderedProduct() {
        return orderedProduct;
    }
    public void setOrderedProduct(String orderedProduct) {
        this.orderedProduct = orderedProduct;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
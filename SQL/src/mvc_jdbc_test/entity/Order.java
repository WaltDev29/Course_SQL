package mvc_jdbc_test.entity;

import java.util.Date;

public class Order {
    private String orderId;
    private String customerId;
    private String customerName;
    private String orderedProduct;
    private int amount;
    private String deliveryAddress;
    private Date orderDate;

    public Order(String orderId, String customerId, String customerName, String orderedProduct, int amount, String deliveryAddress, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderedProduct = orderedProduct;
        this.amount = amount;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
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


// 주문 목록을 화면에 출력
// 3개의 테이블 조인
// Order Entity 생성 (클래스)

// 고객명 고객아이디 배송지 수량 주문일자 제품명
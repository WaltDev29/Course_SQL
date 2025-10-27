package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Order;

public class OrderView extends ObjectView<Order> {
    String title = "주문 정보";

    public void printHead() {
        System.out.println("\n========================");
        System.out.println("========" + title + "=======");
        System.out.println("========================\n");
        System.out.printf("%-6s %-10s %-10s %-15s %-4s %-10s\n", "고객명", "고객아이디", "제품명", "배송지", "수량", "주문일자");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Override
    public void printItem(Order order) {
        System.out.printf("%-6s ", order.getCustomerName());
        System.out.printf("%-10s ", order.getCustomerId());
        System.out.printf("%-10s ", order.getOrderedProduct());
        System.out.printf("%-15s ", order.getDeliveryAddress());
        System.out.printf("%-4d ", order.getAmount());
        System.out.printf("%-10s ", order.getOrderDate());
    }
}

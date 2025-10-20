package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Order;

public class OrderView {
    private String title = "주문 정보";

    public void printOrders(Order order) {
        System.out.printf("%-6s\t", order.getCustomerName());
        System.out.printf("%-10s\t", order.getCustomerId());
        System.out.printf("%-10s\t", order.getOrderedProduct());
        System.out.printf("%-15s\t", order.getDeliveryAddress());
        System.out.printf("%-4d\t", order.getAmount());
        System.out.printf("%-10s\t", order.getOrderDate());
    }

    public void printHead() {
        System.out.println("\n========================");
        System.out.println("========"+title+"=======");
        System.out.println("========================\n");
        System.out.printf("%-6s\t%-10s%-10s\t%-15s\t%-4s\t%-10s\n", "고객명", "고객아이디", "제품명", "배송지", "수량", "주문일자");
        System.out.println("-------------------------------------------------------------------------------");
    }

    public void printFoot() {
        System.out.println("\n========================");
        System.out.println("====== Print Done ======");
        System.out.println("========================\n");
    }
}

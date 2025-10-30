package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Order;

public class OrderView extends ObjectView<Order> {
    String title = "주문 정보";

    public void printHead() {
        System.out.println("\n========================");
        System.out.println("========" + title + "=======");
        System.out.println("========================\n");
    }

    public void printCols() {
        System.out.printf("%-6s %-10s %-10s %-15s %-4s %-10s\n", "주문번호", "고객아이디", "제품명", "배송지", "수량", "주문일자");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Override
    public void printItem(Order order) {
        System.out.printf("%-7s", order.getId());
        System.out.printf("%-13s ", order.getCustomerId());
        System.out.printf("%-10s ", order.getOrderedProduct());
        System.out.printf("%-13s ", order.getDeliveryAddress());
        System.out.printf("%-5d ", order.getAmount());
        System.out.printf("%-10s ", order.getOrderDate());
    }

    public void printItemWithIndex(Order order) {
        System.out.println("\n====== 제품 정보 ======");
        System.out.printf("1. 제품명 : %s", order.getOrderedProduct());
        System.out.printf("2. 배송지 : %s", order.getDeliveryAddress());
        System.out.printf("3. 수량 : %d", order.getAmount());
        System.out.printf("4. 주문일자 : %s", order.getOrderDate());
    }
}

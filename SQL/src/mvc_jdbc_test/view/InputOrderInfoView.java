package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputOrderInfoView {
    public Order inputOrderInfo(Scanner sc) {
        String orderId;
        String customerId;
        String orderedProduct;
        int amount;
        String deliveryAddress;
        Date orderDate;

        System.out.println("\n주문 정보를 입력해주세요.");
        System.out.println("(주문 번호, 주문 고객, 주문 제품, 수량, 배송지, 주문일자)");

        System.out.print("\n주문 번호 : ");
        orderId = sc.nextLine();
        System.out.print("\n주문 고객 : ");
        customerId = sc.nextLine();
        System.out.print("\n주문 제품 : ");
        orderedProduct = sc.nextLine();

        while (true) {
            try {
                System.out.print("\n수량 : ");
                String line = sc.nextLine();
                amount = Integer.parseInt(line.trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.\n");
            }
        }

        System.out.print("\n배송지 : ");
        deliveryAddress = sc.nextLine();

        while (true) {
            try {
                System.out.print("\n주문일자 : ");
                String line = sc.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                orderDate = sdf.parse(line);
                break;
            } catch (ParseException e) {
                System.out.println("잘못된 입력입니다. yyyy-MM-dd 형식으로 입력해주세요.\n");
            }
        }

        System.out.println("### 입력 정보 ###");
        System.out.println("주문 번호 : " + orderId);
        System.out.println("주문 고객 : " + customerId);
        System.out.println("주문 제품 : " + orderedProduct);
        System.out.println("수량 : " + amount);
        System.out.println("배송지 : " + deliveryAddress);
        System.out.println("주문일자 : " + orderDate);

        return new Order(orderId, customerId, orderedProduct, amount, deliveryAddress, orderDate);
    }
}

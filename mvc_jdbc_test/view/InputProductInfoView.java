package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Product;

import java.util.Scanner;

public class InputProductInfoView {
    public Product inputProductInfo(Scanner sc) {
        String productId;
        String productName;
        int productAmount;
        int productPrice;
        String manufacturer;

        System.out.println("\n제품 정보를 입력해주세요.");
        System.out.println("(제품번호, 제품명, 재고량, 단가, 제조업체)");

        System.out.print("\n제품번호 : ");
        productId = sc.nextLine();
        System.out.print("\n제품명 : ");
        productName = sc.nextLine();

        while (true) {
            try {
                System.out.print("\n재고량 : ");
                String line = sc.nextLine();
                productAmount = Integer.parseInt(line.trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.\n");
            }
        }

        while (true) {
            try {
                System.out.print("\n단가 : ");
                String line = sc.nextLine();
                productPrice = Integer.parseInt(line.trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.\n");
            }
        }
        
        System.out.print("\n제조업체 : ");
        manufacturer = sc.nextLine();

        System.out.println("### 입력 정보 ###");
        System.out.println("제품번호 : " + productId);
        System.out.println("제품명 : " + productName);
        System.out.println("재고량 : " + productAmount);
        System.out.println("단가 : " + productPrice);
        System.out.println("제조사 : " + manufacturer);


        return new Product(productId, productName, productAmount, productPrice, manufacturer);
    }
}

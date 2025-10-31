package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Product;

public class ProductView extends ObjectView<Product> {
    String title = "제품 정보";
    
    public void printHead() {
        System.out.println("\n========================");
        System.out.println("========" + title + "=======");
        System.out.println("========================\n");
    }

    public void printCols() {
        System.out.printf("%-8s %-12s %-8s %-10s %-16s\n", "제품번호", "제품명", "재고량", "단가", "제조업체");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Override
    public void printItem(Product product) {
        System.out.printf("%-10s ", product.getId());
        System.out.printf("%-12s ", product.getProductName());
        System.out.printf("%-10d ", product.getProductAmount());
        System.out.printf("%-11d ", product.getProductPrice());
        System.out.printf("%-16s ", product.getManufacturer());
    }

    public void printItemWithIndex(Product product) {
        System.out.println("\n====== 주문 정보 ======");
        System.out.printf("1. 제품명 : %s", product.getProductName());
        System.out.printf("2. 재고량 : %d", product.getProductAmount());
        System.out.printf("3. 단가 : %d", product.getProductPrice());
        System.out.printf("4. 제조업체 : %s\n", product.getManufacturer());
    }
}

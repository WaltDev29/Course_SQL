package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Product;

public class ProductView extends ObjectView<Product> {
    String title = "제품 정보";
    
    public void printHead() {
        System.out.println("\n========================");
        System.out.println("========" + title + "=======");
        System.out.println("========================\n");
        System.out.printf("%-8s %-16s %-8s %-10s %-16s\n", "제품번호", "제품명", "재고량", "단가", "제조업체");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Override
    public void printItem(Product product) {
        System.out.printf("%-8s ", product.getProductId());
        System.out.printf("%-16s ", product.getProductName());
        System.out.printf("%-8d ", product.getProductAmount());
        System.out.printf("%-10d ", product.getProductPrice());
        System.out.printf("%-16s ", product.getManufacturer());
    }
}

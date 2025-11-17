package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

public class CustomerView extends ObjectView<Customer> {
    String title = "고객 정보";

    public void printHead() {
        System.out.println("\n========================");
        System.out.println("========" + title + "=======");
        System.out.println("========================\n");
    }

    public void printCols() {
        System.out.printf("%-10s %-10s %-4s %-10s %-10s %-8s\n", "고객아이디", "고객이름", "나이", "등급", "직업", "적립금");
        System.out.println("-------------------------------------------------------------------------------");
    }

    @Override
    public void printItem(Customer customer) {
        System.out.printf("%-14s ", customer.getId());
        System.out.printf("%-10s ", customer.getName());
        System.out.printf("%-5d ", customer.getAge());
        System.out.printf("%-11s ", customer.getGrade());
        System.out.printf("%-10s ", customer.getJob());
        System.out.printf("%-8d ", customer.getPoint());
    }

    public void printItemWithIndex(Customer customer) {
        System.out.println("\n====== 고객 정보 ======");
        System.out.printf("1. 고객이름 : %s\n", customer.getName());
        System.out.printf("2. 나이 : %d\n", customer.getAge());
        System.out.printf("3. 등급 : %s\n", customer.getGrade());
        System.out.printf("4. 직업 : %s\n", customer.getJob());
        System.out.printf("5. 적립금 : %d\n", customer.getPoint());
    }
}

package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

public class CustomerView {
    public String title = "고객 정보";

//    public void printCustomer(Customer customer) {
//        System.out.printf("※ 고객명 : %s\n", customer.getName());
//        System.out.printf("※ 나이 : %d\n", customer.getAge());
//        System.out.printf("※ 등급 : %s\n", customer.getGrade());
//        System.out.printf("※ 직업 : %s\n", customer.getJob());
//        System.out.printf("※ 적립금 : %d\n", customer.getPoint());
//    }

    public void printCustomer(Customer customer) {
        System.out.printf("%-14s\t", customer.getId());
        System.out.printf("%-8s\t", customer.getName());
        System.out.printf("%-4d\t", customer.getAge());
        System.out.printf("%-10s\t", customer.getGrade());
        System.out.printf("%-10s\t", customer.getJob());
        System.out.printf("%-8d\t", customer.getPoint());
    }

    public void printHead() {
        System.out.println("\n========================");
        System.out.println("========"+title+"=======");
        System.out.println("========================\n");
        System.out.printf("%-10s\t%-10s%-4s\t%-10s\t%-10s\t%-8s\n", "고객아이디", "고객이름", "나이", "등급", "직업", "적립금");
        System.out.println("-------------------------------------------------------------------------------");
    }

    public void printFoot() {
        System.out.println("\n========================");
        System.out.println("====== Print Done ======");
        System.out.println("========================\n");
    }
}

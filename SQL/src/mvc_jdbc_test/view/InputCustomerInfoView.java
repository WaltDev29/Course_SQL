package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

import java.util.Scanner;

public class InputCustomerInfoView {

    public Customer inputCustomerInfo(Scanner sc) {
        String customerId = "";
        String customerName = "";
        int customerAge = 0;
        String customerGrade = "";
        String customerJob = "";
        int customerPoint = 0;

        System.out.println("\n고객 정보를 입력해주세요.");
        System.out.print("\n고객 아이디 : ");
        customerId = sc.nextLine();
        System.out.print("\n고객 이름 : ");
        customerName = sc.nextLine();

        while (true) {
            try {
                System.out.print("\n고객 나이 : ");
                String line = sc.nextLine();
                customerAge = Integer.parseInt(line.trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.\n");
            }
        }

        System.out.print("\n고객 등급 : ");
        customerGrade = sc.nextLine();
        System.out.print("\n고객 직업 : ");
        customerJob = sc.nextLine();

        while (true) {
            try {
                System.out.print("\n고객 적립금 : ");
                String line = sc.nextLine();
                customerPoint = Integer.parseInt(line.trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.\n");
            }
        }


        System.out.println("### 입력 정보 ###");
        System.out.println("고객 아이디 : " + customerId);
        System.out.println("고객 이름 : " + customerName);
        System.out.println("고객 나이 : " + customerAge);
        System.out.println("고객 등급 : " + customerGrade);
        System.out.println("고객 직업 : " + customerJob);
        System.out.println("고객 적립금 : " + customerPoint);

        Customer customer = new Customer(customerId, customerName, customerAge, customerGrade, customerJob, customerPoint);
        return customer;
    }
}

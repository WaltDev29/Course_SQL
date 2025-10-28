package mvc_jdbc_test.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import jdbc_test.JDBCConnector;
import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.entity.Order;
import mvc_jdbc_test.entity.Product;
import mvc_jdbc_test.view.*;

// todo
// 각 View 컬럼 길이 맞추기

// todo-later
// 각 기능 화면 구현
// 각 기능 구현
// 각 기능의 state, answer 추가

public class MainController {
    public static void main(String[] args) {
        // 변수 선언
        Scanner sc = new Scanner(System.in);
        Connection con;
        MainView mv = new MainView();
        int mainState = 0;

        // 결과 담을 List 선언
        ArrayList<Customer> customerList;
        ArrayList<Product> productList;
        ArrayList<Order> orderList;

        // JDBC 연결
        con = JDBCConnector.getConnection();

        // 메인 프로그램
        while (true) {
            switch (mainState) {
                // 메인
                case 0:
                    mv.showMainView();
                    mainState = mv.inputAnswer(sc, 0, 4);
                    if (mainState == 0) mainState = -1;
                    break;

                // 데이터 조회
                case 1:
                    mv.showQueryView();
                    int queryState = mv.inputAnswer(sc, 1, 3);
                    switch (queryState) {
                        case 1:
                            customerList = getCustomerList(con);
                            printItemList(customerList, new CustomerView());
                            break;
                        case 2:
                            productList = getProductList(con);
                            printItemList(productList, new ProductView());
                            break;
                        case 3:
                            orderList = getOrderList(con);
                            printItemList(orderList, new OrderView());
                            break;
                    }
                    inputEnter(sc);
                    mainState = 0;
                    break;

                // 데이터 추가 화면
                case 2:
                    mv.showInsertView();

                    inputCustomerInfo(con, sc);
                    break;

                // 데이터 수정 화면
                case 3:
                    mv.showUpdateView();
                    break;

                // 데이터 삭제 화면
                case 4:
                    mv.showDeleteView();
                    break;

                default:
                    System.out.println("잘못된 입력입니다. 1~3의 정수를 입력해주세요.");
            }
            if (mainState == -1) break;
        }

        System.out.println("\n프로그램을 종료합니다.");
        sc.close();
    }

    public static void inputEnter(Scanner sc) {
        System.out.println("Enter를 눌러 처음으로");
        sc.nextLine();
    }

    public static ArrayList<Customer> getCustomerList(Connection con) {
        ArrayList<Customer> customerList = new ArrayList<>();
        Customer customer;

        String sql = "SELECT * FROM 고객";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customer = new Customer(
                        rs.getString("고객아이디"),
                        rs.getString("고객이름"),
                        rs.getInt("나이"),
                        rs.getString("등급"),
                        rs.getString("직업"),
                        rs.getInt("적립금"));
                customerList.add(customer);
            }

            ps.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
            throw new RuntimeException(e);
        }
        return customerList;
    }

    public static ArrayList<Product> getProductList(Connection con) {
        ArrayList<Product> productList = new ArrayList<>();
        Product product;
        String sql = "SELECT * FROM 제품";

        try {
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            product = new Product(
                    rs.getString("제품번호"),
                    rs.getString("제품명"),
                    rs.getInt("재고량"),
                    rs.getInt("단가"),
                    rs.getString("제조업체")
            );
            productList.add(product);
        }

        ps.close();
        rs.close();

        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
            throw new RuntimeException(e);
        }

        return productList;
    }
    public static ArrayList<Order> getOrderList(Connection con) {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;

        String sql = "SELECT o.주문번호, o.주문고객, c.고객이름, p.제품명, o.수량, o.배송지, o.주문일자\n" +
                "FROM 고객 c, 주문 o, 제품 p\n" +
                "WHERE c.고객아이디 = o.주문고객 AND o.주문제품 = p.제품번호";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                order = new Order(
                        rs.getString("주문번호"),
                        rs.getString("주문고객"),
                        rs.getString("고객이름"),
                        rs.getString("제품명"),
                        rs.getInt("수량"),
                        rs.getString("배송지"),
                        rs.getDate("주문일자")
                );
                ordersList.add(order);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
            throw new RuntimeException(e);
        }
        return ordersList;
    }

    public static <T> void printItemList(ArrayList<T> itemList, ObjectView<T> view) {
        view.printHead();
        for (T item : itemList) {
            view.printItem(item);
            System.out.println();
        }
        view.printFoot();
    }

    public static void inputCustomerInfo(Connection con, Scanner sc) {
        InputCustomerInfoView iciv = new InputCustomerInfoView();
        ArrayList<Customer> inputCustomerList = new ArrayList<>();
        String answer;

        while (true) {
            Customer customer = iciv.inputCustomerInfo(sc);

            // 데이터 저장 여부 확인
            while (true) {
                System.out.println("\n데이터 저장 : S\n다시 입력 : R");
                System.out.print("입력 : ");
                answer = sc.nextLine();

                if (answer.equalsIgnoreCase("S")) break;
                else if (answer.equalsIgnoreCase("R")) break;
                else System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
            }
            if (answer.equalsIgnoreCase("R")) continue;

            // 데이터 INSERT
            try {
                String sql = "INSERT INTO 고객 VALUES(?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, customer.getId());
                ps.setString(2, customer.getName());
                ps.setInt(3, customer.getAge());
                ps.setString(4, customer.getGrade());
                ps.setString(5, customer.getJob());
                ps.setInt(6, customer.getPoint());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                System.out.println("Statement or SQL Error");
                throw new RuntimeException(e);
            }
            inputCustomerList.add(customer);

            // 계속 입력받을지 선택
            while (true) {
                System.out.println("\n계속 입력 : C\n입력 종료 : E");
                System.out.print("입력 : ");
                answer = sc.nextLine();

                if (answer.equalsIgnoreCase("E")) break;
                else if (answer.equalsIgnoreCase("C")) break;
                else System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
            }
            if (answer.equalsIgnoreCase("E")) break;
        }

        // 입력 내용 출력
        System.out.println("입력 종료");
        System.out.println("====== 입력 내용 ======\n");
        printItemList(inputCustomerList, new CustomerView());
    }
}

package mvc_jdbc_test.controller;

import jdbc_test.JDBCConnector;
import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.entity.Entity;
import mvc_jdbc_test.entity.Order;
import mvc_jdbc_test.entity.Product;
import mvc_jdbc_test.view.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController2 {
    public static void main(String[] args) {
        // 변수 선언
        Scanner sc = new Scanner(System.in);
        Connection con;
        MainView mv = new MainView();

        // 결과 담을 List 선언
        ArrayList<Customer> customerList;
        ArrayList<Product> productList;
        ArrayList<Order> orderList;

        // JDBC 연결
        con = JDBCConnector.getConnection();

        int mainState = 0;
        int subState;
        String mode;
        String pk = "";
        boolean proceed;
        // 메인 프로그램
        while (true) {
            // Mode 선택
            if (mainState == 0) { // 메인
                mv.showHomeView();
                mainState = mv.inputAnswer(sc, 0, 4);
                if (mainState == 0) break;
            }

            // 잘못된 입력
            if (mainState < 0 || mainState > 4) {
                System.out.println("잘못된 입력입니다. 1~3의 정수를 입력해주세요.");
            }

            // Mode 할당
            mode = switch (mainState) {
                case 1 -> "조회";
                case 2 -> "추가";
                case 3 -> "수정";
                case 4 -> "삭제";
                default -> "";
            };

            // DB 선택
            mv.showMainView(mode);
            subState = mv.inputAnswer(sc, 0, 3);

            // 처음으로 돌아가기
            if (subState == 0) {
                mainState = 0;
                continue;
            }


            // 데이터 추가
            if (mainState == 2) {
                if (subState == 1) {
                    insertCustomerInfo(con, sc);
                } else if (subState == 2) {
                    insertProductInfo(con, sc);
                } else if (subState == 3) {
                    insertOrderInfo(con, sc);
                }
                mv.inputEnter(sc);
                continue;
            }

            // DB 출력
            if (subState == 1) {
                customerList = getCustomerList(con, null);
                printItemList(customerList, new CustomerView());
            } else if (subState == 2) {
                productList = getProductList(con, null);
                printItemList(productList, new ProductView());
            } else if (subState == 3) {
                orderList = getOrderList(con, null);
                printItemList(orderList, new OrderView());
            }


            // 데이터 조회일 경우 바로 처음으로
            if (mainState == 1) {
                mv.inputEnter(sc);
                continue;
            }


            // PK 입력
            if (subState == 1) {
                pk = inputCustomerPk(con, sc);
                customerList = getCustomerList(con, pk);
                if (mainState == 3) printItemWithIndex(customerList.get(0), new CustomerView());
                else printItem(customerList.get(0), new CustomerView());
            } else if (subState == 2) {
                pk = inputProductPk(con, sc);
                productList = getProductList(con, pk);
                if (mainState == 3) printItemWithIndex(productList.get(0), new ProductView());
                else printItem(productList.get(0), new ProductView());
            } else if (subState == 3) {
                pk = inputOrderPk(con, sc);
                orderList = getOrderList(con, pk);
                if (mainState == 3) printItemWithIndex(orderList.get(0), new OrderView());
                else printItem(orderList.get(0), new OrderView());
            }

            // 삭제
            if (mainState == 4) {
                System.out.println("\n해당 데이터를 삭제하시겠습니까?");
                if (mv.askYorN(sc, "삭제", "Y", "취소", "N")) {
                    deleteInfo(con, subState, pk);
                    mv.inputEnter(sc);
                }
            }

            // 데이터 수정
            if (mainState == 3) {
                System.out.println("\n수정할 항목의 번호를 입력해주세요. 뒤로가기 : 0");
                proceed = updateInfo(con, sc, subState, pk);
                if (!proceed) continue;

                System.out.println("\n수정이 완료되었습니다.");
                System.out.print("\n--- 수정 정보 ---");
                if (subState == 1) {
                    customerList = getCustomerList(con, pk);
                    printItemWithIndex(customerList.get(0), new CustomerView());
                } else if (subState == 2) {
                    productList = getProductList(con, pk);
                    printItem(productList.get(0), new ProductView());
                } else if (subState == 3) {
                    orderList = getOrderList(con, pk);
                    printItem(orderList.get(0), new OrderView());
                }
                mv.inputEnter(sc);
            }
        }

        System.out.println("\n프로그램을 종료합니다.");
        sc.close();
    }


    // Input
    private static String inputCustomerPk(Connection con, Scanner sc) {
        ArrayList<Customer> customerList = getCustomerList(con, null);
        String target;
        System.out.println("고객의 고객 아이디를 입력하세요.\n");
        while (true) {
            System.out.print("고객 아이디 : ");
            target = sc.nextLine();
            if (validatePk(customerList, target)) break;
            else System.out.println("존재하지 않는 고객 아이디입니다. 다시 입력해주세요.\n");
        }
        return target;
    }

    private static String inputProductPk(Connection con, Scanner sc) {
        ArrayList<Product> productList = getProductList(con, null);
        String target;
        System.out.println("제품의 제품번호를 입력하세요.\n");
        while (true) {
            System.out.print("제품번호 : ");
            target = sc.nextLine();
            if (validatePk(productList, target)) break;
            else System.out.println("존재하지 않는 제품입니다. 다시 입력해주세요.\n");
        }
        return target;
    }

    private static String inputOrderPk(Connection con, Scanner sc) {
        ArrayList<Order> orderList = getOrderList(con, null);
        String target;
        System.out.println("주문의 주문번호를 입력하세요.\n");
        while (true) {
            System.out.print("주문번호 : ");
            target = sc.nextLine();
            if (validatePk(orderList, target)) break;
            else System.out.println("존재하지 않는 주문번호입니다. 다시 입력해주세요.\n");
        }
        return target;
    }


    // Validate
    private static boolean validatePk(ArrayList<? extends Entity> EntityList, String pk) {
        for (Entity e : EntityList) if (e.getId().equalsIgnoreCase(pk)) return true;
        return false;
    }


    // Print
    private static <T> void printItem(T item, ObjectView<T> view) {
        view.printHead();
        view.printCols();
        view.printItem(item);
        System.out.println();
    }

    private static <T> void printItemList(ArrayList<T> itemList, ObjectView<T> view) {
        view.printHead();
        view.printCols();
        for (T item : itemList) {
            view.printItem(item);
            System.out.println();
        }
        view.printFoot();
    }

    private static <T> void printItemWithIndex(T item, ObjectView<T> view) {
        view.printItemWithIndex(item);
    }


    // SELECT
    private static ArrayList<Customer> getCustomerList(Connection con, String target) {
        ArrayList<Customer> customerList = new ArrayList<>();
        Customer customer;
        String sql;

        if (target != null) sql = "SELECT * FROM 고객 WHERE 고객아이디 = ?";
        else sql = "SELECT * FROM 고객";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (target != null) ps.setString(1, target);
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

    private static ArrayList<Product> getProductList(Connection con, String target) {
        ArrayList<Product> productList = new ArrayList<>();
        Product product;
        String sql;

        if (target != null) sql = "SELECT * FROM 제품 WHERE 제품번호 = ?";
        else sql = "SELECT * FROM 제품";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (target != null) ps.setString(1, target);
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

    private static ArrayList<Order> getOrderList(Connection con, String target) {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        String sql;

        if (target != null) sql = "SELECT * FROM 주문 WHERE 주문번호 = ?";
        else sql = "SELECT * FROM 주문";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (target != null) ps.setString(1, target);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                order = new Order(
                        rs.getString("주문번호"),
                        rs.getString("주문고객"),
                        rs.getString("주문제품"),
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


    // INSERT
    private static void insertCustomerInfo(Connection con, Scanner sc) {
        MainView mv = new MainView();
        InputCustomerInfoView iciv = new InputCustomerInfoView();
        ArrayList<Customer> inputCustomerList = new ArrayList<>();

        while (true) {
            Customer customer = iciv.inputCustomerInfo(sc);

            // 데이터 저장 여부 확인
            if (!mv.askYorN(sc, "데이터 저장", "S", "다시 입력", "R")) continue;

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
            System.out.println("데이터가 저장되었습니다.\n");
            inputCustomerList.add(customer);

            // 계속 입력받을지 선택
            if (!mv.askYorN(sc, "계속 입력", "C", "입력 종료", "E")) break;
        }

        // 입력 내용 출력
        System.out.println("\n--- 입력 종료 ---");
        System.out.println("====== 입력 내용 ======\n");
        printItemList(inputCustomerList, new CustomerView());
    }

    private static void insertProductInfo(Connection con, Scanner sc) {
        MainView mv = new MainView();
        InputProductInfoView ipiv = new InputProductInfoView();
        ArrayList<Product> inputProductList = new ArrayList<>();

        while (true) {
            Product product = ipiv.inputProductInfo(sc);

            // 데이터 저장 여부 확인
            if (!mv.askYorN(sc, "데이터 저장", "S", "다시 입력", "R")) continue;

            // 데이터 INSERT
            try {
                String sql = "INSERT INTO 제품 VALUES(?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, product.getId());
                ps.setString(2, product.getProductName());
                ps.setInt(3, product.getProductAmount());
                ps.setInt(4, product.getProductPrice());
                ps.setString(5, product.getManufacturer());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                System.out.println("Statement or SQL Error");
                throw new RuntimeException(e);
            }
            System.out.println("데이터가 저장되었습니다.\n");
            inputProductList.add(product);

            // 계속 입력받을지 선택
            if (!mv.askYorN(sc, "계속 입력", "C", "입력 종료", "E")) break;
        }

        // 입력 내용 출력
        System.out.println("\n--- 입력 종료 ---");
        System.out.println("====== 입력 내용 ======\n");
        printItemList(inputProductList, new ProductView());
    }

    private static void insertOrderInfo(Connection con, Scanner sc) {
        MainView mv = new MainView();
        InputOrderInfoView ioiv = new InputOrderInfoView();
        ArrayList<Order> inputOrderList = new ArrayList<>();

        while (true) {
            Order order = ioiv.inputOrderInfo(sc);

            // 데이터 저장 여부 확인
            if (!mv.askYorN(sc, "데이터 저장", "S", "다시 입력", "R")) continue;

//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = Date.valueOf(sdf.format(order.getOrderDate()));
            Date date = new Date(order.getOrderDate().getTime());

            // 데이터 INSERT
            try {
                String sql = "INSERT INTO 주문 VALUES(?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, order.getId());
                ps.setString(2, order.getCustomerId());
                ps.setString(3, order.getOrderedProduct());
                ps.setInt(4, order.getAmount());
                ps.setString(5, order.getDeliveryAddress());
                ps.setDate(6, date);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                System.out.println("Statement or SQL Error");
                throw new RuntimeException(e);
            }
            System.out.println("데이터가 저장되었습니다.\n");
            inputOrderList.add(order);

            // 계속 입력받을지 선택
            if (!mv.askYorN(sc, "계속 입력", "C", "입력 종료", "E")) break;
        }

        // 입력 내용 출력
        System.out.println("\n--- 입력 종료 ---");
        System.out.println("====== 입력 내용 ======\n");
        printItemList(inputOrderList, new OrderView());
    }


    // UPDATE
    private static boolean updateInfo(Connection con, Scanner sc, int subState, String target) {
        MainView mv = new MainView();
        String value = "";
        int valueInt = 0;
        String[] cols = new String[]{};
        String table = "";
        String pk = "";
        int intCol1 = 0;
        int intCol2 = 0;

        if (subState == 1) {
            cols = new String[]{"고객이름", "나이", "등급", "직업", "적립금"};
            intCol1 = 2;
            intCol2 = 5;
            table = "고객";
            pk = "고객아이디";
        } else if (subState == 2) {
            cols = new String[]{"제품명", "재고량", "단가", "제조업체"};
            intCol1 = 2;
            intCol2 = 3;
            table = "제품";
            pk = "제품번호";
        } else if (subState == 3) {
            cols = new String[]{"제품명", "배송지", "수량", "주문일자"};
            intCol2 = 3;
            table = "주문";
            pk = "주문번호";
        }

        int index = mv.inputAnswer(sc, 0, cols.length);

        if (index == 0) return false;

        System.out.printf("\n수정할 %s 입력\n", cols[index - 1]);

        if (index == intCol1 || index == intCol2) valueInt = mv.inputAnswer(sc, 0, 100000);
        else {
            System.out.print("\n입력 : ");
            value = sc.nextLine();
        }

        String sql = "UPDATE " + table + " SET " + cols[index - 1] + " = ? WHERE " + pk + " = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (index == 2 || index == 5) ps.setInt(1, valueInt);
            else ps.setString(1, value);
            ps.setString(2, target);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
            throw new RuntimeException(e);
        }
        return true;
    }


    // DELETE
    private static void deleteInfo(Connection con, int subState, String target) {
        String table = "";
        String pk = "";

        if (subState == 1) {
            table = "고객";
            pk = "고객아이디";
        } else if (subState == 2) {
            table = "제품";
            pk = "제품번호";
        } else if (subState == 3) {
            table = "주문";
            pk = "주문번호";
        }

        String sql = "DELETE FROM " + table + " WHERE " + pk + " = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, target);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
            throw new RuntimeException(e);
        }
        System.out.println("\n데이터가 삭제되었습니다.");
    }
}

package mvc_jdbc_test.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc_test.JDBCConnector;
import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.entity.Order;
import mvc_jdbc_test.view.CustomerView;
import mvc_jdbc_test.view.OrderView;

public class MainController {
    public static void main(String[] args) {
        ArrayList<Customer> customerList;
        ArrayList<Order> orderList;

        try {
            Connection con = JDBCConnector.getConnection();
            customerList = getCustomerList(con);
            orderList = getOrderList(con);

        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
            throw new RuntimeException(e);
        }
        printCustomerList(customerList);
        printOrderList(orderList);
    }

    public static ArrayList<Customer> getCustomerList(Connection con) throws SQLException {
        ArrayList<Customer> customerList = new ArrayList<>();
        Customer customer;

        String sql = "SELECT * FROM 고객";
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
        return customerList;
    }

    public static void printCustomerList(ArrayList<Customer> customerList) {
        CustomerView cv = new CustomerView();
        cv.printHead();
        for (Customer c : customerList) {
            cv.printCustomer(c);
            System.out.println();
        }
        cv.printFoot();
    }

    public static ArrayList<Order> getOrderList(Connection con) throws SQLException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;

        String sql = "SELECT o.주문번호, o.주문고객, c.고객이름, p.제품명, o.수량, o.배송지, o.주문일자\n" +
                "FROM 고객 c, 주문 o, 제품 p\n" +
                "WHERE c.고객아이디 = o.주문고객 AND o.주문제품 = p.제품번호";

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
        return ordersList;
    }

    public static void printOrderList(ArrayList<Order> orderList) {
        OrderView ov = new OrderView();
        ov.printHead();
        for (Order o : orderList) {
            ov.printOrders(o);
            System.out.println();
        }
        ov.printFoot();
    }
}



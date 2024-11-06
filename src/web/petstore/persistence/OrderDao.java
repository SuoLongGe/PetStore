package web.petstore.persistence;

import web.petstore.domain.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    // SQL 查询语句
    private static final String GET_ORDER_BY_ID = "SELECT BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP, SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS FROM ORDERS, ORDERSTATUS WHERE ORDERS.ORDERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";
    private static final String GET_ORDERS_BY_USERNAME = "SELECT BILLADDR1 AS billAddress1, BILLADDR2 AS billAddress2, BILLCITY, BILLCOUNTRY, BILLSTATE, BILLTOFIRSTNAME, BILLTOLASTNAME, BILLZIP, SHIPADDR1 AS shipAddress1, SHIPADDR2 AS shipAddress2, SHIPCITY, SHIPCOUNTRY, SHIPSTATE, SHIPTOFIRSTNAME, SHIPTOLASTNAME, SHIPZIP, CARDTYPE, COURIER, CREDITCARD, EXPRDATE AS expiryDate, LOCALE, ORDERDATE, ORDERS.ORDERID, TOTALPRICE, USERID AS username, STATUS FROM ORDERS, ORDERSTATUS WHERE ORDERS.USERID = ? AND ORDERS.ORDERID = ORDERSTATUS.ORDERID ORDER BY ORDERDATE";
    private static final String INSERT_ORDER = "INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE, SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY, COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME, CREDITCARD, EXPRDATE, CARDTYPE, LOCALE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ORDER_STATUS = "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS) VALUES (?, ?, ?, ?)";

    // 根据订单 ID 获取订单信息
    public Order getOrder(int orderId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(GET_ORDER_BY_ID);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("ORDERID"));
                order.setUsername(rs.getString("username"));
                order.setOrderDate(rs.getDate("ORDERDATE"));
                order.setBillAddress1(rs.getString("billAddress1"));
                order.setBillAddress2(rs.getString("billAddress2"));
                order.setBillCity(rs.getString("BILLCITY"));
                order.setBillCountry(rs.getString("BILLCOUNTRY"));
                order.setBillState(rs.getString("BILLSTATE"));
                order.setBillToFirstName(rs.getString("BILLTOFIRSTNAME"));
                order.setBillToLastName(rs.getString("BILLTOLASTNAME"));
                order.setBillZip(rs.getString("BILLZIP"));
                order.setShipAddress1(rs.getString("shipAddress1"));
                order.setShipAddress2(rs.getString("shipAddress2"));
                order.setShipCity(rs.getString("SHIPCITY"));
                order.setShipCountry(rs.getString("SHIPCOUNTRY"));
                order.setShipState(rs.getString("SHIPSTATE"));
                order.setShipToFirstName(rs.getString("SHIPTOFIRSTNAME"));
                order.setShipToLastName(rs.getString("SHIPTOLASTNAME"));
                order.setShipZip(rs.getString("SHIPZIP"));
                order.setCardType(rs.getString("CARDTYPE"));
                order.setCourier(rs.getString("COURIER"));
                order.setCreditCard(rs.getString("CREDITCARD"));
                order.setExpiryDate(rs.getString("expiryDate"));
                order.setLocale(rs.getString("LOCALE"));
                order.setTotalPrice(rs.getBigDecimal("TOTALPRICE"));
                order.setStatus(rs.getString("STATUS"));
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
        return order;
    }

    // 根据用户名获取订单列表
    public List<Order> getOrdersByUsername(String username) throws SQLException {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(GET_ORDERS_BY_USERNAME);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("ORDERID"));
                order.setUsername(rs.getString("username"));
                order.setOrderDate(rs.getDate("ORDERDATE"));
                order.setBillAddress1(rs.getString("billAddress1"));
                order.setBillAddress2(rs.getString("billAddress2"));
                order.setBillCity(rs.getString("BILLCITY"));
                order.setBillCountry(rs.getString("BILLCOUNTRY"));
                order.setBillState(rs.getString("BILLSTATE"));
                order.setBillToFirstName(rs.getString("BILLTOFIRSTNAME"));
                order.setBillToLastName(rs.getString("BILLTOLASTNAME"));
                order.setBillZip(rs.getString("BILLZIP"));
                order.setShipAddress1(rs.getString("shipAddress1"));
                order.setShipAddress2(rs.getString("shipAddress2"));
                order.setShipCity(rs.getString("SHIPCITY"));
                order.setShipCountry(rs.getString("SHIPCOUNTRY"));
                order.setShipState(rs.getString("SHIPSTATE"));
                order.setShipToFirstName(rs.getString("SHIPTOFIRSTNAME"));
                order.setShipToLastName(rs.getString("SHIPTOLASTNAME"));
                order.setShipZip(rs.getString("SHIPZIP"));
                order.setCardType(rs.getString("CARDTYPE"));
                order.setCourier(rs.getString("COURIER"));
                order.setCreditCard(rs.getString("CREDITCARD"));
                order.setExpiryDate(rs.getString("expiryDate"));
                order.setLocale(rs.getString("LOCALE"));
                order.setTotalPrice(rs.getBigDecimal("TOTALPRICE"));
                order.setStatus(rs.getString("STATUS"));

                orders.add(order);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
        return orders;
    }

    // 插入订单信息
    public void insertOrder(Order order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(INSERT_ORDER);
            ps.setInt(1, order.getOrderId());
            ps.setString(2, order.getUsername());
            ps.setDate(3, new Date(order.getOrderDate().getTime()));
            ps.setString(4, order.getShipAddress1());
            ps.setString(5, order.getShipAddress2());
            ps.setString(6, order.getShipCity());
            ps.setString(7, order.getShipState());
            ps.setString(8, order.getShipZip());
            ps.setString(9, order.getShipCountry());
            ps.setString(10, order.getBillAddress1());
            ps.setString(11, order.getBillAddress2());
            ps.setString(12, order.getBillCity());
            ps.setString(13, order.getBillState());
            ps.setString(14, order.getBillZip());
            ps.setString(15, order.getBillCountry());
            ps.setString(16, order.getCourier());
            ps.setBigDecimal(17, order.getTotalPrice());
            ps.setString(18, order.getBillToFirstName());
            ps.setString(19, order.getBillToLastName());
            ps.setString(20, order.getShipToFirstName());
            ps.setString(21, order.getShipToLastName());
            ps.setString(22, order.getCreditCard());
            ps.setString(23, order.getExpiryDate());
            ps.setString(24, order.getCardType());
            ps.setString(25, order.getLocale());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
    }

    // 插入订单状态信息
    public void insertOrderStatus(Order order) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(INSERT_ORDER_STATUS);
            ps.setInt(1, order.getOrderId());
            ps.setInt(2, order.getOrderId()); // 假设 LINENUM 与 ORDERID 相同
            ps.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
            ps.setString(4, order.getStatus());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
    }
}

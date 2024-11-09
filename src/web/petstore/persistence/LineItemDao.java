package web.petstore.persistence;

import web.petstore.domain.LineItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class LineItemDao {

    // SQL 查询语句
    private static final String INSERT_LINE_ITEM = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";

    private static final String GET_LINE_ITEMS_BY_ORDER_ID = "SELECT ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";

    // 插入订单条目
    public boolean insertLineItem(LineItem lineItem) throws SQLException {
        boolean result =false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(INSERT_LINE_ITEM);
            ps.setInt(1, lineItem.getOrderId());
            ps.setInt(2, lineItem.getLineNumber());
            ps.setString(3, lineItem.getItemId());
            ps.setInt(4, lineItem.getQuantity());
            ps.setBigDecimal(5, lineItem.getUnitPrice());
            int rows = ps.executeUpdate();
            if(rows==1)
            {
                result=true;
            }
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
        return  result;
    }

    // 获取订单中的所有条目
    public List<LineItem> getLineItemsByOrderId(int orderId) throws SQLException {
        List<LineItem> lineItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(GET_LINE_ITEMS_BY_ORDER_ID);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(rs.getInt("ORDERID"));
                lineItem.setLineNumber(rs.getInt("LINENUM"));
                lineItem.setItemId(rs.getString("ITEMID"));
                lineItem.setQuantity(rs.getInt("QUANTITY"));
                lineItem.setUnitPrice(rs.getBigDecimal("UNITPRICE"));

                lineItems.add(lineItem);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
        return lineItems;
    }

    // 获取订单条目的总价（计算该订单中所有条目的总和）
    public BigDecimal getTotalAmountByOrderId(int orderId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BigDecimal total = BigDecimal.ZERO;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(GET_LINE_ITEMS_BY_ORDER_ID);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                BigDecimal unitPrice = rs.getBigDecimal("UNITPRICE");
                int quantity = rs.getInt("QUANTITY");
                total = total.add(unitPrice.multiply(new BigDecimal(quantity)));
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
        return total;
    }
}

package web.petstore.persistence;

import web.petstore.domain.Account;
import web.petstore.domain.CartItem;
import web.petstore.domain.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

    // SQL 查询语句
    private static final String CREATE_CART = "INSERT INTO carts (user_id) VALUES (?)";
    private static final String GET_CART_BY_USER_ID = "SELECT cart_id FROM carts WHERE user_id = ?";
    private static final String INSERT_CART_ITEM = "INSERT INTO cart_items (cart_id, item_id, quantity, in_stock) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CART_ITEM = "UPDATE cart_items SET quantity = ? WHERE cart_id = ? AND item_id = ?";
    private static final String DELETE_CART_ITEM = "DELETE FROM cart_items WHERE cart_id = ? AND item_id = ?";
    private static final String GET_CART_ITEMS = "SELECT ci.item_id, ci.quantity, ci.in_stock, " +
            "i.productid, i.listprice, i.unitcost, i.supplier, i.status, " +
            "i.attr1 AS attribute1, i.attr2 AS attribute2, i.attr3 AS attribute3, i.attr4 AS attribute4, i.attr5 AS attribute5 " +
            "FROM cart_items ci JOIN item i ON ci.item_id = i.itemid WHERE ci.cart_id = ?";
    private static final String GET_CART_TOTAL = "SELECT SUM(ci.quantity * i.listprice) FROM cart_items ci JOIN item i ON ci.item_id = i.itemid WHERE ci.cart_id = ?";

    // 获取用户购物车ID，如果没有则创建一个新的购物车
    public int getCartIdByUserId(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(GET_CART_BY_USER_ID);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("cart_id");
            } else {
                // 如果没有找到购物车，则创建一个新的购物车
                ps = conn.prepareStatement(CREATE_CART, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, userId);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // 返回新创建的购物车ID
                }
                throw new SQLException("创建购物车失败！");
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
    }

    // 获取购物车中的所有商品
    public List<CartItem> getCartItems(int cartId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(GET_CART_ITEMS);
            ps.setInt(1, cartId);
            rs = ps.executeQuery();

            while (rs.next()) {
                CartItem cartItem = new CartItem();

                // 创建 Item 对象并填充数据
                Item item = new Item();
                item.setItemId(rs.getString("item_id"));
                item.setProductId(rs.getString("productid"));
                item.setListPrice(rs.getBigDecimal("listprice"));
                item.setUnitCost(rs.getBigDecimal("unitcost"));
                item.setSupplierId(rs.getInt("supplier"));
                item.setStatus(rs.getString("status"));
                item.setAttribute1(rs.getString("attribute1"));
                item.setAttribute2(rs.getString("attribute2"));
                item.setAttribute3(rs.getString("attribute3"));
                item.setAttribute4(rs.getString("attribute4"));
                item.setAttribute5(rs.getString("attribute5"));

                // 设置 CartItem 中的 Item 和数量
                cartItem.setItem(item);
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setInStock(rs.getBoolean("in_stock"));

                // 将 cartItem 添加到列表
                cartItems.add(cartItem);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
        return cartItems;
    }

    // 添加商品到购物车
    public void addItemToCart(String userId, String itemId, int quantity, boolean inStock) throws SQLException {
      Integer cartId = getCartIdByUserId(userId); // 获取购物车 ID
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getconnection();

            // 首先检查购物车中是否已经存在该商品
            String checkItemSql = "SELECT quantity FROM cart_items WHERE cart_id = ? AND item_id = ?";
            ps = conn.prepareStatement(checkItemSql);
            ps.setInt(1, cartId);
            ps.setString(2, itemId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // 如果商品已经存在，更新数量
                int existingQuantity = rs.getInt("quantity");
                String updateSql = "UPDATE cart_items SET quantity = ? WHERE cart_id = ? AND item_id = ?";
                ps = conn.prepareStatement(updateSql);
                ps.setInt(1, existingQuantity + quantity);
                ps.setInt(2, cartId);
                ps.setString(3, itemId);
                ps.executeUpdate();
            } else {
                // 如果商品不存在，插入新记录
                String insertSql = "INSERT INTO cart_items (cart_id, item_id, quantity, in_stock) VALUES (?, ?, ?, ?)";
                ps = conn.prepareStatement(insertSql);
                ps.setInt(1, cartId);
                ps.setString(2, itemId);
                ps.setInt(3, quantity);
                ps.setBoolean(4, inStock);
                ps.executeUpdate();
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
    }

    // 更新购物车中的商品数量
    public void updateItemQuantity(String userId, String itemId, int quantity) throws SQLException {
        int cartId = getCartIdByUserId(userId); // 获取购物车 ID
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(UPDATE_CART_ITEM);
            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            ps.setString(3, itemId);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
    }

    // 从购物车中删除商品
    public void removeItemFromCart(String userId, String itemId) throws SQLException {
        int cartId = getCartIdByUserId(userId); // 获取购物车 ID
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(DELETE_CART_ITEM);
            ps.setInt(1, cartId);
            ps.setString(2, itemId);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
    }

    // 获取购物车的总价
    public BigDecimal getCartTotal(String userId) throws SQLException {
       int cartId = getCartIdByUserId(userId); // 获取购物车 ID
        BigDecimal total = new BigDecimal("0");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getconnection();
            ps = conn.prepareStatement(GET_CART_TOTAL);
            ps.setInt(1, cartId);
            rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getBigDecimal(1);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }
        return total;
    }

    public boolean itemExistsInCart(int cartId, String itemId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getconnection();
            String sql = "SELECT COUNT(*) FROM cart_items WHERE cart_id = ? AND item_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.setString(2, itemId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(conn);
        }

        return false;
    }

}

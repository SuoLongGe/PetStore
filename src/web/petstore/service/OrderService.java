package web.petstore.service;

import web.petstore.domain.*;
import web.petstore.persistence.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    private ItemDao itemDao;
    private OrderDao orderDao;
    private SequenceDao sequenceDao;
    private LineItemDao lineItemDao;


    public OrderService() {
        // 手动实例化 DAO 层对象
        this.itemDao = new ItemDao();
        this.orderDao = new OrderDao();
        this.sequenceDao = new SequenceDao();
        this.lineItemDao = new LineItemDao();
    }

    // 插入订单
    public boolean insertOrder(Order order) throws SQLException {
        //order.setOrderId(this.getNextId("ordernum"));
        int itemnum=0;int j=0;
        // 更新库存数量

        CartDao cartDao=new CartDao();
        int cartId = cartDao.getCartIdByUserId(order.getUsername()); // 获取购物车 ID
        List <CartItem> cartItems = cartDao.getCartItems(cartId);

        for (LineItem lineItem : order.getLineItems()) {

            String itemId = lineItem.getItemId();
            Integer increment = lineItem.getQuantity();
            Map<String, Object> param = new HashMap<>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            this.itemDao.updateInventoryQuantity(param);  // 更新库存
        }

        // 插入订单
        boolean flag1 = this.orderDao.insertOrder(order);

        boolean flag2 = this.orderDao.insertOrderStatus(order);
        boolean flag3 = false;
        // 插入订单项
        for (LineItem lineItem : order.getLineItems()) {
            lineItem.setOrderId(order.getOrderId());
            if(this.lineItemDao.insertLineItem(lineItem))
            {
                j++;// 插入每个订单项
            }
        }

        return (flag1&&flag2  );
    }


    // 获取订单
    public Order getOrder(int orderId) throws SQLException {
        Order order = this.orderDao.getOrder(orderId);
        order.setLineItems(this.lineItemDao.getLineItemsByOrderId(orderId));

        // 为订单项添加商品信息
        for (LineItem lineItem : order.getLineItems()) {
            Item item = this.itemDao.getItem(lineItem.getItemId());
            item.setQuantity(this.itemDao.getInventoryQuantity(lineItem.getItemId()));  // 获取库存量
            lineItem.setItem(item);  // 设置订单项中的商品
        }

        return order;
    }

    // 根据用户名获取订单列表
    public List<Order> getOrdersByUsername(String username) throws SQLException {
        return this.orderDao.getOrdersByUsername(username);
    }

    // 获取下一个序列ID
    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = this.sequenceDao.getSequence(name);

        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name + " sequence).");
        } else {
            // 更新序列号
            Map<String, Object> param = new HashMap<>();
            param.put("name", name);
            param.put("nextId", sequence.getNextId() + 1);
            // 更新序列号
            this.sequenceDao.updateSequence(param);
            return sequence.getNextId();
        }
    }
}

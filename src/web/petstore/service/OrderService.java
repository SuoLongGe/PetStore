package web.petstore.service;

import web.petstore.domain.Item;
import web.petstore.domain.LineItem;
import web.petstore.domain.Order;
import web.petstore.domain.Sequence;
import web.petstore.persistence.ItemDao;
import web.petstore.persistence.LineItemDao;
import web.petstore.persistence.OrderDao;
import web.petstore.persistence.SequenceDao;

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
    public void insertOrder(Order order) {
        order.setOrderId(this.getNextId("ordernum"));

        // 更新库存数量
        for (LineItem lineItem : order.getLineItems()) {
            String itemId = lineItem.getItemId();
            Integer increment = lineItem.getQuantity();
            Map<String, Object> param = new HashMap<>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            this.itemDao.updateInventoryQuantity(param);  // 更新库存
        }

        // 插入订单
        this.orderDao.insertOrder(order);
        this.orderDao.insertOrderStatus(order);

        // 插入订单项
        for (LineItem lineItem : order.getLineItems()) {
            lineItem.setOrderId(order.getOrderId());
            this.lineItemDao.insertLineItem(lineItem);  // 插入每个订单项
        }
    }

    // 获取订单
    public Order getOrder(int orderId) {
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
    public List<Order> getOrdersByUsername(String username) {
        return this.orderDao.getOrdersByUsername(username);
    }

    // 获取下一个序列ID
    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = this.sequenceDao.getSequence(sequence);

        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name + " sequence).");
        } else {
            // 更新序列号
            Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
            this.sequenceDao.updateSequence(parameterObject);
            return sequence.getNextId();
        }
    }
}

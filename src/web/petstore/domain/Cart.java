package web.petstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    private static final long serialVersionUID = 8329559983943337176L;
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());
    private final List<CartItem> itemList = new ArrayList<CartItem>();

    // 获取购物车项的迭代器
    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }

    // 获取购物车项的列表
    public List<CartItem> getCartItemList() {
        return itemList;
    }

    // 获取购物车中的商品数量
    public int getNumberOfItems() {
        return itemList.size();
    }

    // 获取所有购物车项的迭代器
    public Iterator<CartItem> getAllCartItems() {
        return itemList.iterator();
    }

    // 判断购物车中是否包含某个商品ID
    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    // 添加商品到购物车
    public void addItem(Item item, boolean isInStock) {
        CartItem cartItem = itemMap.get(item.getItemId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(0);
            cartItem.setInStock(isInStock);
            itemMap.put(item.getItemId(), cartItem);
            itemList.add(cartItem);
        }
        cartItem.incrementQuantity();
    }

    // 根据商品ID从购物车中移除商品
    public Item removeItemById(String itemId) {
        CartItem cartItem = itemMap.remove(itemId);
        if (cartItem == null) {
            return null;
        } else {
            itemList.remove(cartItem);
            return cartItem.getItem();
        }
    }

    // 根据商品ID增加商品数量
    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = itemMap.get(itemId);
        if (cartItem != null) {
            cartItem.incrementQuantity();
        } else {
            System.out.println("商品ID " + itemId + " 在当前购物车中未找到。");
        }
    }

    // 根据商品ID设置商品数量
    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = itemMap.get(itemId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
        } else {
            System.out.println("商品ID " + itemId + " 在当前购物车中未找到。");
        }
    }

    // 获取购物车的总价格
    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        for (CartItem cartItem : itemList) {
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }

    // 设置购物车中的商品列表，同时更新 itemMap
    public void setCartItems(List<CartItem> cartItems) {
        this.itemMap.clear(); // 清空现有的 itemMap
        this.itemList.clear(); // 清空现有的 itemList

        for (CartItem cartItem : cartItems) {
            String itemId = cartItem.getItem().getItemId();
            this.itemMap.put(itemId, cartItem); // 添加到 itemMap
            this.itemList.add(cartItem); // 添加到 itemList
        }
    }
}

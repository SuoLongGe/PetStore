package web.petstore.persistence;

import web.petstore.domain.Item;

import web.petstore.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDao {

    private static final String GET_ITEMLIST_BY_PRODUCT = "SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS productName,DESCN AS productDescription,CATEGORY AS categoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";

    private static final String GET_ITEM = "select I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS productId,NAME AS productName,DESCN AS productDescription,CATEGORY AS CategoryId,STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity from ITEM I, INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID=?";

    private static final String GET_INVENTORY_QUANTITY = "SELECT QTY AS QUANTITY FROM INVENTORY WHERE ITEMID = ?";

    private static final String UPDATE_INVENTORY_QUANTITY = "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";

    public void updateInventoryQuantity(Map<String, Object> param) {
        try {
            Connection connection = DBUtiil.getconnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(UPDATE_INVENTORY_QUANTITY);
            String itemId = param.keySet().iterator().next();
            Integer increment = (Integer) param.get(itemId);
            pStatement.setInt(1, increment.intValue());
            pStatement.setString(2, itemId);
            pStatement.executeUpdate();
            DBUtiil.closePreparedStatement(pStatement);
            DBUtiil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getInventoryQuantity(String itemId) {
        int result = -1;
        try {
            Connection connection = DBUtiil.getconnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_INVENTORY_QUANTITY);
            pStatement.setString(1, itemId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            DBUtiil.closeResultSet(resultSet);
            DBUtiil.closePreparedStatement(pStatement);
            DBUtiil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

   public List<Item> getItemListByProduct(String var1){
        List<Item> ItemList=new ArrayList<>();
        try {
            Connection connection=DBUtiil.getconnection();
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery(GET_ITEMLIST_BY_PRODUCT);
            while (resultSet.next()) {
                Item item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product=new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);

                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                ItemList.add(item);

            }
            DBUtiil.closeConnection(connection);
            DBUtiil.closeResultSet(resultSet);
            DBUtiil.closeStatement(statement);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ItemList;
    }

    public Item getItem(String itemId) {
        Item item = null;
        try {
            Connection connection = DBUtiil.getconnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_ITEM);
            pStatement.setString(1, itemId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product = new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                item.setQuantity(resultSet.getInt(15));
            }
            DBUtiil.closeResultSet(resultSet);
            DBUtiil.closePreparedStatement(pStatement);
            DBUtiil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return item;

    }
}

package web.petstore.persistence;

import web.petstore.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final String getProductListByCategoryString =
            "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY=?";
    private static final String getProductString =
            "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String searchProductListString =
            "SELECT PRODUCTID,NAME,DESCN as description,CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";



    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> products = new ArrayList<Product>();
        try {
            Connection connection = DBUtil.getconnection();
            PreparedStatement pStatement = connection
                    .prepareStatement(getProductListByCategoryString);
            pStatement.setString(1, categoryId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                products.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;

    }

    public Product getProduct(String productId) {
        Product product = null;
        try {

            Connection connection= DBUtil.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getProductString);
            preparedStatement.setString(1,productId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                product =new Product();

                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return product;
    }

    public List<Product> searchProductList(String keywords) {
        List<Product> productList = new ArrayList<Product>();

        try {

            Connection connection= DBUtil.getconnection();
            PreparedStatement preparedStatement =connection.prepareStatement(searchProductListString);
            preparedStatement.setString(1,"%"+keywords+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                Product product =new Product();

                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return productList;
    }
}

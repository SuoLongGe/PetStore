package web.petstore.persistence;

import web.petstore.domain.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private  static final String GET_CATEGORY_LIST="SELECT  CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY";

    private  static final String GET_CATEGORY="SELECT  CATID AS categoryId,NAME,DESCN AS description FROM CATEGORY WHERE CATID = ?";


    public List<Category> getCategoryList(){
        List<Category> categoryList =new ArrayList<>();
        try {
            Connection connection=DBUtiil.getconnection();
            Statement statement =connection.createStatement();
            ResultSet resultSet=statement.executeQuery(GET_CATEGORY_LIST);
            while(resultSet.next())
            {
                Category category=new Category();
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("NAME"));
                category.setDescription(resultSet.getString("description"));
                categoryList.add(category);
            }
            DBUtiil.closeResultSet(resultSet);
            DBUtiil.closeStatement(statement);
            DBUtiil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  categoryList;

    }

    public Category getCategory(String categoryId){
        Category category=null;
        try {
            Connection connection=DBUtiil.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY);
            preparedStatement.setString(1,categoryId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                category=new Category();
                category.setCategoryId(resultSet.getString("categoryId"));
                category.setName(resultSet.getString("NAME"));
                category.setDescription(resultSet.getString("description"));
            }
            DBUtiil.closeResultSet(resultSet);
            DBUtiil.closePreparedStatement(preparedStatement);
            DBUtiil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }
}

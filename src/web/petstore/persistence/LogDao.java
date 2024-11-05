package web.petstore.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LogDao {

    public void saveUserActivity(String userId, String activityType, String activityDetail,String item,String order) {
        String sql = "INSERT INTO USERACTIONLOG (user_id, action_type, action_description,item_or_product_id,order_id) VALUES ( ? , ?, ? ,  ? , ?)";

        try (Connection connection = DBUtil.getconnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {


            System.out.println("连接对象: " + connection);
            System.out.println("日志数据库连接成功");
            stmt.setString(1, userId);
            stmt.setString(2, activityType);
            stmt.setString(3, activityDetail);
            //stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // action_time 字段
            stmt.setString(4, item );
            stmt.setString(5, order );

            stmt.executeUpdate();
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(stmt);
            System.out.println("执行到了这里");

        } catch (SQLException e) {
            System.out.println("日志数据库连接失败");
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

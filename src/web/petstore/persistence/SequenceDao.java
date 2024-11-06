package web.petstore.persistence;

import web.petstore.domain.Sequence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class SequenceDao {

    private static final String GET_SEQUENCE = "SELECT NAME, NEXTID FROM SEQUENCE WHERE NAME = ?";
    private static final String UPDATE_SEQUENCE = "UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";

    // 获取Sequence
    public Sequence getSequence(String name) {
        Sequence sequence = null;
        try {
            // 获取数据库连接
            Connection connection = DBUtil.getconnection();

            // 准备查询语句
            PreparedStatement pStatement = connection.prepareStatement(GET_SEQUENCE);
            pStatement.setString(1, name);

            // 执行查询
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                sequence = new Sequence();
                sequence.setName(resultSet.getString("NAME"));
                sequence.setNextId(resultSet.getInt("NEXTID"));
            }

            // 关闭资源
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sequence;
    }

    // 更新Sequence
    public void updateSequence(Map<String, Object> param) {
        try {
            // 获取数据库连接
            Connection connection = DBUtil.getconnection();

            // 准备更新语句
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_SEQUENCE);

            // 从param获取新的NEXTID和NAME
            String name = (String) param.keySet().iterator().next();
            Integer nextId = (Integer) param.get(name);

            // 设置参数并执行更新
            pStatement.setInt(1, nextId);
            pStatement.setString(2, name);
            pStatement.executeUpdate();

            // 提交事务（如果适用）
            connection.commit();

            // 关闭资源
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

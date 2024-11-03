package web.petstore.persistence;

import web.petstore.domain.Account;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDao {

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD="SELECT " +
            "SIGNON.USERNAME," +
            "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String INSERT_ACCOUNT="INSERT INTO ACCOUNT"+
           " (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES"+
            "(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?,?)";

    private static final String INSERT_ACCOUNT_INTO_SIGNON="INSERT INTO SIGNON (username,password)"+
            "VALUES (?,?)";

    private static final String INSERT_PROFILE="INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID,MYLISTOPT,BANNEROPT)"+
    " VALUES (?, ?, ?,?,?)";

    private static final String GET_ACCOUNT_BY_USERNAME="SELECT " +
            "SIGNON.USERNAME," +
            "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +"WHERE ACCOUNT.USERID = ?";




    public Account getAccountByUsernameAndPassword(Account account){
        Account accountResult = null;
        try {
            Connection connection = DBUtil.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                accountResult = this.resultSetToAccount(resultSet);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return accountResult;
    }

    public boolean getAccountByUsername(String username){
        Boolean result=false;
        try {
            Connection connection = DBUtil.getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result=true;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertAccount(Account account){
        boolean result =false;
        Connection connection = DBUtil.getconnection();
        try {
            //2.获取执行对象
            PreparedStatement preparedStatement= connection.prepareStatement(INSERT_ACCOUNT);
            //3.将执行对象补充完整
//            " (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES"
            preparedStatement.setString(1,account.getEmail());
            preparedStatement.setString(2,account.getFirstName());
            preparedStatement.setString(3,account.getLastName());
            preparedStatement.setString(4,account.getStatus());
            preparedStatement.setString(5,account.getAddress1());
            preparedStatement.setString(6,account.getAddress2());
            preparedStatement.setString(7,account.getCity());
            preparedStatement.setString(8,account.getState());
            preparedStatement.setString(9,account.getZip());
            preparedStatement.setString(10,account.getCountry());
            preparedStatement.setString(11,account.getPhone());
            preparedStatement.setString(12,account.getUsername());
            int rows= preparedStatement.executeUpdate();
            if(rows==1)
            {
                result=true;
            }
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(preparedStatement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  result;
    }

    public boolean insertProfile(Account account){
        int LO,BO;
        if(account.isListOption())LO=1;
        else LO=0;
        if(account.isBannerOption())BO=1;
        else BO=0;
        boolean result =false;
        Connection connection = DBUtil.getconnection();
        try {
            //2.获取执行对象
            PreparedStatement preparedStatement= connection.prepareStatement(INSERT_PROFILE);
            //3.将执行对象补充完整
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());
            preparedStatement.setInt(4,LO);
            preparedStatement.setInt(5,BO);

            int rows= preparedStatement.executeUpdate();
            if(rows==1)
            {
                result=true;
            }
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(preparedStatement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  result;
    }

    public boolean insertSignon(Account account){
        boolean result =false;
        Connection connection = DBUtil.getconnection();
        try {
            //2.获取执行对象
            PreparedStatement preparedStatement= connection.prepareStatement(INSERT_ACCOUNT_INTO_SIGNON);
            //3.将执行对象补充完整
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            int rows= preparedStatement.executeUpdate();
            if(rows==1)
            {
                result=true;
            }
            DBUtil.closeConnection(connection);
            DBUtil.closePreparedStatement(preparedStatement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  result;
    }

    public void updateAccount(Account account){

    }

    public void updateProfile(Account account){

    }

    public void updateSignon(Account account){

    }

    private Account resultSetToAccount(ResultSet resultSet) throws Exception{
        Account account = new Account();
        account.setUsername(resultSet.getString("username"));
//        account.setPassword(resultSet.getString("password"));
        account.setEmail(resultSet.getString("email"));
        account.setFirstName(resultSet.getString("firstName"));
        account.setLastName(resultSet.getString("lastName"));
        account.setStatus(resultSet.getString("status"));
        account.setAddress1(resultSet.getString("address1"));
        account.setAddress2(resultSet.getString("address2"));
        account.setCity(resultSet.getString("city"));
        account.setState(resultSet.getString("state"));
        account.setZip(resultSet.getString("zip"));
        account.setCountry(resultSet.getString("country"));
        account.setPhone(resultSet.getString("phone"));
        account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
        account.setLanguagePreference(resultSet.getString("languagePreference"));
        account.setListOption(resultSet.getInt("listOption") == 1);
        account.setBannerOption(resultSet.getInt("bannerOption") == 1);
        account.setBannerName(resultSet.getString("bannerName"));
        return account;
    }


}

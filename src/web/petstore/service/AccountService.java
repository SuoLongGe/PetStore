package web.petstore.service;

import web.petstore.domain.Account;
import web.petstore.persistence.AccountDao;

public class AccountService {
    private AccountDao accountDao;
    private  String msg;

    public AccountService(){
        this.accountDao = new AccountDao();
    }


    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
//        account.setCity("6");
//        account.setCountry("6");
//        account.setAddress1("6");
//        account.setAddress2("6");account.setEmail("6");
//        account.setBannerName("6");account.setBannerOption(true);account.setFirstName("6");account.setLastName("6");
//        account.setFavouriteCategoryId("6");account.setLanguagePreference("6");
//        account.setListOption(true);account.setZip("6");account.setState("6");
        Account account1=accountDao.getAccountByUsernameAndPassword(account);
        return account1;
    }

    public boolean addaccount(Account registeraccount){
        String username= registeraccount.getUsername();
        if(accountDao.getAccountByUsername(username)){
            this.msg="用户名已存在";
            return false;
        }
        return  (accountDao.insertSignon(registeraccount)&& accountDao.insertAccount(registeraccount)&&accountDao.insertProfile(registeraccount));
    }
    public String getMsg() {
        return msg;
    }


}

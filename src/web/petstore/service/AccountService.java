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


    public boolean editaccount(Account editaccount) {
        return (accountDao.updateAccount(editaccount)&&accountDao.updateProfile(editaccount)&&accountDao.updateSignon(editaccount));
    }
}

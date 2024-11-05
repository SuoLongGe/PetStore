import web.petstore.domain.Account;
import web.petstore.persistence.AccountDao;
import web.petstore.service.AccountService;

public class textDemo {
    public static void main(String[] args) {
//        Account account=new Account();
//        account.setUsername("刘邦");account.setPassword("123");
//        AccountDao accountDao =new AccountDao();
//        boolean flag=accountDao.insertSignon(account);
//        if(flag){
//            System.out.println("新增成功");
//        }
//        else{
//            System.out.println("新增失败");
//        }


                Account account=new Account();
        account.setUsername("刘邦");account.setPassword("123");
        account.setCity("湖南");
        account.setCountry("中国");
        account.setAddress1("长沙");
        account.setAddress2("路南");
        account.setEmail("666@qq.com");
        account.setBannerName("6");
        account.setBannerOption(true);
        account.setFirstName("邦");
        account.setLastName("刘");
        account.setFavouriteCategoryId("BIRDS");
        account.setLanguagePreference("english");
        account.setListOption(true);account.setZip("94303");account.setState("CA");account.setStatus("ok");
        account.setPhone("666-666-666");
        AccountDao accountDao =new AccountDao();
        boolean flag=accountDao.insertProfile(account);
        if(flag){
            System.out.println("新增成功");
        }
        else{
            System.out.println("新增失败");
        }
    }
}

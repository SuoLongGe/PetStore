package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private String username;
    private String password;
    private String repeatpassword;
    private String email;
    private String firstname;
    private String lastname;
    private String status;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String language;
    private String favouriteCategory;
    private String StringisListOption;
    private String StringisBannerOption;
    private boolean isListOption;
    private boolean isBannerOption;
    private String registerMsg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        this.username=req.getParameter("username");
        this.password=req.getParameter("password");
        this.repeatpassword=req.getParameter("repeatpassword");
        this.email=req.getParameter("email");
        this.firstname=req.getParameter("firstName");
        this.lastname=req.getParameter("lastName");
        this.status=req.getParameter("status");
        this.addr1=req.getParameter("address1");
        this.addr2=req.getParameter("address2");
        this.city=req.getParameter("city");
        this.state=req.getParameter("state");
        this.zip=req.getParameter("zip");
        this.country=req.getParameter("country");
        this.phone=req.getParameter("phone");
        this.language=req.getParameter("account.languagePreference");
        this.favouriteCategory=req.getParameter("account.favouriteCategoryId");
        this.StringisListOption=req.getParameter("account.listOption");
        this.StringisBannerOption=req.getParameter("account.bannerOption");
        this.isListOption=(this.StringisListOption!=null);
        this.isBannerOption=(this.StringisBannerOption!=null);

        if(!this.validate()){
            req.setAttribute("registerMsg",this.registerMsg);
            req.getRequestDispatcher("registerForm").forward(req,resp);
        }
        else{
            Account registeraccount= new Account();
            registeraccount.setUsername(username);
            registeraccount.setPassword(password);
            registeraccount.setEmail(email);
            registeraccount.setFirstName(firstname);
            registeraccount.setLastName(lastname);
            registeraccount.setStatus(status);
            registeraccount.setAddress1(addr1);
            registeraccount.setAddress2(addr2);
            registeraccount.setCity(city);
            registeraccount.setState(state);
            registeraccount.setZip(zip);
            registeraccount.setCountry(country);
            registeraccount.setPhone(phone);
            registeraccount.setLanguagePreference(language);
            registeraccount.setFavouriteCategoryId(favouriteCategory);
            registeraccount.setListOption(isListOption);
            registeraccount.setBannerOption(isBannerOption);


            AccountService accountService=new AccountService();
            if(accountService.addaccount(registeraccount))
            {
                resp.sendRedirect("signonForm");
            }
            else{
                req.setAttribute("registerMsg",accountService.getMsg());
                req.getRequestDispatcher("registerForm").forward(req,resp);
            }
        }

    }
    private  boolean validate(){
        if(this.username==null||this.username.equals(""))
        {
            this.registerMsg="用户名不能为空";
            return false;
        }
        if(this.password==null||this.password.equals(""))
        {
            this.registerMsg="密码不能为空";
            return false;
        }
        if(this.repeatpassword==null||this.repeatpassword.equals(""))
        {
            this.registerMsg="密码不能为空";
            return false;
        }
        if(!this.repeatpassword.equals(this.password))
        {
            this.registerMsg="两次密码不一致";
            return false;
        }
        if(this.firstname==null||this.firstname.equals(""))
        {
            this.registerMsg="名不能为空";
            return false;
        }
        if(this.lastname==null||this.lastname.equals(""))
        {
            this.registerMsg="姓不能为空";
            return false;
        }
        if(this.email==null||this.email.equals(""))
        {
            this.registerMsg="邮箱不能为空";
            return false;
        }
        if(this.phone==null||this.phone.equals(""))
        {
            this.registerMsg="电话不能为空";
            return false;
        }
        if(this.addr1==null||this.addr1.equals(""))
        {
            this.registerMsg="联系地址1不能为空";
            return false;
        }
        if(this.city==null||this.city.equals(""))
        {
            this.registerMsg="居住城市不能为空";
            return false;
        }
        if(this.state==null||this.state.equals(""))
        {
            this.registerMsg="状态不能为空";
            return false;
        }
        if(this.zip==null||this.zip.equals(""))
        {
            this.registerMsg="邮编号码不能为空";
            return false;
        }
        if(this.country==null||this.country.equals(""))
        {
            this.registerMsg="居住国家不能为空";
            return false;
        }

        return  true;
    }
}

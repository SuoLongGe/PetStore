package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditAccountServlet extends HttpServlet {

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
    private String editMsg;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        this. username = loginAccount.getUsername();
        this.password=req.getParameter("newpassword");
        this.repeatpassword=req.getParameter("newrepeatpassword");
        this.email=req.getParameter("newemail");
        this.firstname=req.getParameter("newfirstName");
        this.lastname=req.getParameter("newlastName");
        this.status=req.getParameter("newstatus");
        this.addr1=req.getParameter("newaddress1");
        this.addr2=req.getParameter("newaddress2");
        this.city=req.getParameter("newcity");
        this.state=req.getParameter("newstate");
        this.zip=req.getParameter("newzip");
        this.country=req.getParameter("newcountry");
        this.phone=req.getParameter("newphone");
        this.language=req.getParameter("newaccount.languagePreference");
        this.favouriteCategory=req.getParameter("newaccount.favouriteCategoryId");
        this.StringisListOption=req.getParameter("newaccount.listOption");
        this.StringisBannerOption=req.getParameter("newaccount.bannerOption");
        this.isListOption=(this.StringisListOption!=null);
        this.isBannerOption=(this.StringisBannerOption!=null);

        if(!this.validate()){
            req.setAttribute("editMsg",this.editMsg);
            req.getRequestDispatcher("editAccountForm").forward(req,resp);
        }
        else{
            Account editaccount= new Account();
            editaccount.setUsername(username);
            editaccount.setPassword(password);
            editaccount.setEmail(email);
            editaccount.setFirstName(firstname);
            editaccount.setLastName(lastname);
            editaccount.setStatus(status);
            editaccount.setAddress1(addr1);
            editaccount.setAddress2(addr2);
            editaccount.setCity(city);
            editaccount.setState(state);
            editaccount.setZip(zip);
            editaccount.setCountry(country);
            editaccount.setPhone(phone);
            editaccount.setLanguagePreference(language);
            editaccount.setFavouriteCategoryId(favouriteCategory);
            editaccount.setListOption(isListOption);
            editaccount.setBannerOption(isBannerOption);


            AccountService accountService=new AccountService();
            if(accountService.editaccount(editaccount))
            {
                System.out.println("用户信息修改成功");
                resp.sendRedirect("signonForm");
            }
            else{
                System.out.println("用户修改信息失败");
                req.setAttribute("editMsg",accountService.getMsg());
                req.getRequestDispatcher("editAccountForm").forward(req,resp);
            }
        }

    }
    private  boolean validate(){
        if(this.username==null||this.username.equals(""))
        {
            this.editMsg="用户名不能为空";
            return false;
        }
        if(this.password==null||this.password.equals(""))
        {
            this.editMsg="密码不能为空";
            return false;
        }
        if(this.repeatpassword==null||this.repeatpassword.equals(""))
        {
            this.editMsg="再次输入密码不能为空";
            return false;
        }
        if(!this.repeatpassword.equals(this.password))
        {
            this.editMsg="两次密码不一致";
            return false;
        }
        if(this.firstname==null||this.firstname.equals(""))
        {
            this.editMsg="名不能为空";
            return false;
        }
        if(this.lastname==null||this.lastname.equals(""))
        {
            this.editMsg="姓不能为空";
            return false;
        }
        if(this.email==null||this.email.equals(""))
        {
            this.editMsg="邮箱不能为空";
            return false;
        }
        if(this.phone==null||this.phone.equals(""))
        {
            this.editMsg="电话不能为空";
            return false;
        }
        if(this.addr1==null||this.addr1.equals(""))
        {
            this.editMsg="联系地址1不能为空";
            return false;
        }
        if(this.city==null||this.city.equals(""))
        {
            this.editMsg="居住城市不能为空";
            return false;
        }
        if(this.state==null||this.state.equals(""))
        {
            this.editMsg="状态不能为空";
            return false;
        }
        if(this.zip==null||this.zip.equals(""))
        {
            this.editMsg="邮编号码不能为空";
            return false;
        }
        if(this.country==null||this.country.equals(""))
        {
            this.editMsg="居住国家不能为空";
            return false;
        }

        return  true;
    }
}

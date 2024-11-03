package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Product;
import web.petstore.service.AccountService;
import web.petstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private String username;
    private String password;
    private String msg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        if(!validate()){
            req.setAttribute("signOnMsg", this.msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else{
            AccountService accountService = new AccountService();
            Account loginAccount = accountService.getAccount(username, password);
            if(loginAccount == null){
                this.msg = "用户名或密码错误";
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
            }else {
                loginAccount.setPassword(null);
                HttpSession session = req.getSession();
                session.setAttribute("loginAccount" , loginAccount);


//                if(loginAccount.isListOption()){
//                    CatalogService catalogService = new CatalogService();
//                    List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
//                    session.setAttribute("myList", myList);
//                }

                resp.sendRedirect("mainForm");
            }
        }


    }

    private boolean validate(){
        if(this.username == null || this.username.equals("")){
            this.msg = "用户名不能为空";
            return false;
        }
        if(this.password == null || this.password.equals("")){
            this.msg = "密码不能为空";
            return false;
        }
        return true;
    }
}

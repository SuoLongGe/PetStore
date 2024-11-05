//package web.petstore.inerWeb.servlet;
//
//import web.petstore.domain.Product;
//import web.petstore.persistence.ProductDao;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//public class SearchProductFormServlet extends HttpServlet {
//
//    private static final String SEARCH_PRODUCT_FORM = "/WEB-INF/jsp/catalog/searchProduct.jsp";
//
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String keyword = req.getParameter("keyword");
//
//        // 如果没有输入关键字，返回提示或者执行其他逻辑
//        if (keyword == null || keyword.equals("")) {
//            System.out.println("请输入相关信息！");
//        }
//        else{
//            ProductDao productDao=new ProductDao();
//            List<Product> productList =productDao.searchProductList(keyword);
////            req.setAttribute("productList",productList);
////            req.setAttribute("keyword",keyword);
//            HttpSession session=req.getSession();
//            session.setAttribute("productList",productList);
//            req.getRequestDispatcher(SEARCH_PRODUCT_FORM).forward(req,resp);
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher(SEARCH_PRODUCT_FORM).forward(req,resp);
//    }
//}

//xzx
package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Product;
import web.petstore.persistence.ProductDao;
import web.petstore.service.LogService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductFormServlet extends HttpServlet {

    private static final String SEARCH_PRODUCT_FORM = "/WEB-INF/jsp/catalog/searchProduct.jsp";
    private final LogService logService = new LogService(); // 日志服务

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");

        // 获取用户信息
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");

        // 如果没有输入关键字，返回提示或者执行其他逻辑
        if (keyword == null || keyword.equals("")) {
            System.out.println("请输入相关信息！");
        } else {
            // 执行商品搜索
            ProductDao productDao = new ProductDao();
            List<Product> productList = productDao.searchProductList(keyword);

            // 设置搜索结果到会话属性
            session.setAttribute("productList", productList);

            // 记录搜索操作的日志
            if (loginAccount != null) {
                String userId = loginAccount.getUsername();
                String actionType = "搜索商品";
                String actionDescription = "用户搜索了关键字：" + keyword;

                // 记录日志（无商品ID和订单ID，传 null）
                logService.logUserActivity(userId, actionType, actionDescription, null, null);
            }

            // 转发到搜索结果页面
            req.getRequestDispatcher(SEARCH_PRODUCT_FORM).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SEARCH_PRODUCT_FORM).forward(req, resp);
    }
}
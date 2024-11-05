//package web.petstore.inerWeb.servlet;
//
//import web.petstore.domain.Item;
//import web.petstore.domain.Product;
//import web.petstore.service.CatalogService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//public class ProductFormServlet extends HttpServlet {
//
//    private CatalogService catalogService;
//    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String productId = req.getParameter("productId");
//        catalogService =new CatalogService();
//        Product product=catalogService.getProduct(productId);
//        List<Item> itemList = catalogService.getItemListByProduct(productId);
//        HttpSession session=req.getSession();
//        session.setAttribute("product",product);
//        session.setAttribute("itemList",itemList);
//        session.setAttribute("isReload",true);
//        req.getRequestDispatcher(PRODUCT_FORM).forward(req,resp);
//    }
//}


//xzx
//package web.petstore.inerWeb.servlet;
//
//import web.petstore.domain.Item;
//import web.petstore.domain.Product;
//import web.petstore.service.CatalogService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//public class ProductFormServlet extends HttpServlet {
//
//    private CatalogService catalogService;
//    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String productId = req.getParameter("productId");
//        catalogService =new CatalogService();
//        Product product=catalogService.getProduct(productId);
//        List<Item> itemList = catalogService.getItemListByProduct(productId);
//        HttpSession session=req.getSession();
//        session.setAttribute("product",product);
//        session.setAttribute("itemList",itemList);
//        req.getRequestDispatcher(PRODUCT_FORM).forward(req,resp);
//    }
//}
package web.petstore.inerWeb.servlet;

import web.petstore.domain.Account;
import web.petstore.domain.Item;
import web.petstore.domain.Product;
import web.petstore.service.CatalogService;
import web.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspEngineInfo;
import java.io.IOException;
import java.util.List;

public class ProductFormServlet extends HttpServlet {

    private CatalogService catalogService;
    private LogService logService; // 新增 LogService 实例
    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";

    @Override
    public void init() throws ServletException {
        catalogService = new CatalogService();
        logService = new LogService(); // 初始化 LogService
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        System.out.println(productId+"nb");

        // 获取商品和商品项列表
        Product product = catalogService.getProduct(productId);
        List<Item> itemList = catalogService.getItemListByProduct(productId);

        // 将商品和商品项列表放入会话中
        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);

        // 获取用户ID，用于日志记录（假设用户ID已保存在session中）
//        String userId = (String) session.getAttribute("userId");
//        System.out.println(userId+"znb");

        Account loginAccount = (Account) session.getAttribute("loginAccount");
        String userId = loginAccount.getUsername();
        System.out.println(userId+"znb");
        String order_id = null;
        // 记录用户查看商品的日志
        if (userId != null) { // 检查用户是否已登录
            logService.logUserActivity(userId, "浏览", "查看商品",  productId,order_id);
        }

        // 转发请求到商品详情页面
        req.getRequestDispatcher(PRODUCT_FORM).forward(req, resp);
    }
}

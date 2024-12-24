package web.petstore.inerWeb.servlet;

import web.petstore.domain.Category;
import web.petstore.domain.Product;
import web.petstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CategoryFormServlet extends HttpServlet {

    private CatalogService catalogService;
    private static final String CATEGORY_FORM = "/WEB-INF/jsp/catalog/category.jsp";
    private static final String CATEGORY_TABLE_PARTIAL = "/WEB-INF/jsp/catalog/categoryTable.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");

        catalogService = new CatalogService();


        Category category = catalogService.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);


        HttpSession session = req.getSession();


        session.setAttribute("category", category);
        session.setAttribute("productList", productList);


        String ajaxRequest = req.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(ajaxRequest)) {
            System.out.println("AJAX");
            req.getRequestDispatcher(CATEGORY_TABLE_PARTIAL).forward(req, resp);
        } else {
            System.out.println("Handling normal request");
            req.getRequestDispatcher(CATEGORY_FORM).forward(req, resp);
        }
    }
}

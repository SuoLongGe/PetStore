package web.petstore.inerWeb.servlet;

import web.petstore.domain.Item;
import web.petstore.domain.Product;
import web.petstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

  public class ItemFormServlet extends HttpServlet {
    private CatalogService catalogService;

    private static final String ITEM_FORM = "/WEB-INF/jsp/catalog/item.jsp";


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        catalogService = new CatalogService();
        Item item = catalogService.getItem(itemId);
        Product product = item.getProduct();

        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("item", item);
        session.setAttribute("isReload",true);
        req.getRequestDispatcher(ITEM_FORM).forward(req, resp);
    }
}

package web.petstore.inerWeb.servlet;

import web.petstore.domain.Product;
import web.petstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowMsgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        System.out.println(categoryId);
        CatalogService catalogService = new CatalogService();
        PrintWriter out = resp.getWriter();
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        String respstring = "Product ID - - Name\n";
        int i = 0;
        while(i < productList.size()){
            Product product = productList.get(i);
            respstring += product.getProductId() + " - - " + product.getName() + "\n";
            i++;
        }


        resp.setContentType("text/html");

        out.write(respstring);
    }
}

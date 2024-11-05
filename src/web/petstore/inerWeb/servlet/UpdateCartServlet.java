package web.petstore.inerWeb.servlet;

import web.petstore.domain.Cart;
import web.petstore.domain.CartItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartServlet  extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            req.setAttribute("errorMessage", "购物车为空，无法进行更新！");
            req.getRequestDispatcher("/WEB-INF/jsp/cart/emptyCart.jsp").forward(req, resp);  // 你可以根据需要跳转到空购物车的页面
            return;
        }
        Iterator<CartItem> cartItems = cart.getAllCartItems();


        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                String quantityString = req.getParameter(itemId);
                int quantity = Integer.parseInt(quantityString);

                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception e) {

            }
        }

        req.getRequestDispatcher(CART_FORM).forward(req,resp);
    }
}

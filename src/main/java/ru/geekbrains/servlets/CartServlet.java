package ru.geekbrains.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Cart;
import ru.geekbrains.persist.CartRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "cartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(CartServlet.class);
    private CartRepository cartRepository;

    @Override
    public void init() throws ServletException {
        cartRepository = (CartRepository) getServletContext().getAttribute("cartRepository");
        if (cartRepository == null) {
            throw new ServletException("Cart repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Cart page");

        if (req.getServletPath().equals("/cart")) {
            try {
                req.setAttribute("cart", cartRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(req, resp);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        } else if (req.getServletPath().equals("/new")) {
            req.setAttribute("cart", new Cart());
            getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(req, resp);
        } else if (req.getServletPath().equals("/edit")) {
            String id = req.getParameter("id");
            try {
                Optional<Cart> opt = cartRepository.findById(Long.parseLong(id));
                if (opt.isPresent()) {
                    req.setAttribute("cart", opt.get());
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/upsert")) {
            try {
                String strId = req.getParameter("id");
                if (strId.isEmpty()) {
                    cartRepository.insert(new Cart(-1L,
                            req.getParameter("idProduct"),
                            req.getParameter("price"),
                            req.getParameter("qty")));
                } else {
                    cartRepository.update(new Cart(Long.parseLong(strId),
                            req.getParameter("idProduct"),
                            req.getParameter("price"),
                            req.getParameter("qty")));
                }
                resp.sendRedirect(getServletContext().getContextPath());
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}

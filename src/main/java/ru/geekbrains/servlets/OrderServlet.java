package ru.geekbrains.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.OrderRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "orderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(OrderServlet.class);
    private OrderRepository orderRepository;

    @Override
    public void init() throws ServletException {
        orderRepository = (OrderRepository) getServletContext().getAttribute("orderRepository");
        if (orderRepository == null) {
            throw new ServletException("Order repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Order page");

        if (req.getServletPath().equals("/order")) {
            try {
                req.setAttribute("order", orderRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        } else if (req.getServletPath().equals("/new")) {
            req.setAttribute("order", new Order());
            getServletContext().getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
        } else if (req.getServletPath().equals("/edit")) {
            String id = req.getParameter("id");
            try {
                Optional<Order> opt = orderRepository.findById(Long.parseLong(id));
                if (opt.isPresent()) {
                    req.setAttribute("order", opt.get());
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
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
                    orderRepository.insert(new Order(-1L,
                            req.getParameter("idProduct"),
                            req.getParameter("price"),
                            req.getParameter("qty"),
                            req.getParameter("client"),
                            req.getParameter("datePurchase")));

                } else {
                    orderRepository.update(new Order(Long.parseLong(strId),
                            req.getParameter("idProduct"),
                            req.getParameter("price"),
                            req.getParameter("qty"),
                            req.getParameter("client"),
                            req.getParameter("datePurchase")));
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

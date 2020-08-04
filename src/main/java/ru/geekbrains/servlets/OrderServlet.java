package ru.geekbrains.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "orderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Order page get request");

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.getWriter().printf("<h1>Оформление заказа</h1>");

        getServletContext().getRequestDispatcher("/menuHeader").include(req, resp);
    }
}

package ru.geekbrains.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "catalogServlet", urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(CatalogServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Catalog get request");

        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.getWriter().printf("<h1>Список товаров</h1>");

        getServletContext().getRequestDispatcher("/menuHeader").include(req, resp);
    }
}

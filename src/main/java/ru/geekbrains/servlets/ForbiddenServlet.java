package ru.geekbrains.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "forbidden", urlPatterns = "/forbidden")
public class ForbiddenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1 align=center>403 Forbidden </h1>");
        resp.getWriter().println("<h3> You are not allowed to enter here </h3> ");
    }
}

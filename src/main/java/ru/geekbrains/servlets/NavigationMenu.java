package ru.geekbrains.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "menuHeader", urlPatterns = "/menuHeader")
public class NavigationMenu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<style > ul.hr {\n" +
                "    margin: 0; /* Обнуляем значение отступов */\n" +
                "    padding: 4px; /* Значение полей */\n" +
                "   }\n" +
                "   ul.hr li {\n" +
                "    display: inline; /* Отображать как строчный элемент */\n" +
                "    margin-right: 5px; /* Отступ слева */\n" +
                "    padding: 3px; /* Поля вокруг текста */\n" +
                "   } </style>");
        resp.getWriter().println("<ul class ='hr'>");
        resp.getWriter().println("<li><a href='main'>Home</a></li>");
        resp.getWriter().println("<li><a href='catalog'>Catalog</a></li>");
        resp.getWriter().println("<li><a href='product'>Product</a></li>");
        resp.getWriter().println("<li><a href='cart'>Cart</a></li>");
        resp.getWriter().println("<li><a href='order'>Order</li>");
        resp.getWriter().println("</ul>");
    }
}

package ru.geekbrains.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppBootstrapListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(AppBootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Initializing application");
        ServletContext sc = sce.getServletContext();

        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcConnectionString, username, password);
            sc.setAttribute("connection", conn);










        } catch (SQLException throwables) {
            log.error("", throwables);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        ServletContext sc = sce.getServletContext();
        Connection conn = (Connection) sc.getAttribute("connection");
        try {
            if (conn != null && conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            log.error("", ex);
        }
    }
}

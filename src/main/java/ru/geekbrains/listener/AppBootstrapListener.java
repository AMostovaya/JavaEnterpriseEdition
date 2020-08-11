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

            ProductRepository productRepository = new ProductRepository(conn);
            sc.setAttribute("productRepository", productRepository);

            if (productRepository.findAll().isEmpty()) {
                productRepository.insert(new Product(-1L, "Apple Macbook pro 2015", "Apple profession laptop", new BigDecimal(3000)));
                productRepository.insert(new Product(-1L, "Apple Macbook air 2015", "Apple netbook", new BigDecimal(2000)));
                productRepository.insert(new Product(-1L, "Apple iPad", "Apple tablet", new BigDecimal(1000)));
            }

            CartRepository cartRepository = new CartRepository(conn);
            sc.setAttribute("cartRepository", cartRepository);

            if (cartRepository.findAll().isEmpty()) {
                cartRepository.insert(new Cart(-1L, (long) 1, new BigDecimal(23344), new BigDecimal(3)));
                cartRepository.insert(new Cart(-1L, (long) 3, new BigDecimal(23457), new BigDecimal(2)));
                cartRepository.insert(new Cart(-1L, (long) 5, new BigDecimal(23324), new BigDecimal(1)));
            }

            OrderRepository orderRepository = new OrderRepository(conn);
            sc.setAttribute("orderRepository", orderRepository);




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

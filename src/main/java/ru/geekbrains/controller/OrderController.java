package ru.geekbrains.controller;

import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.OrderRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class OrderController implements Serializable {

    @Inject
    OrderRepository orderRepository;

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getAllOrders() throws SQLException {
        return orderRepository.findAll();
    }

    public String editOrder(Order order) {
        this.order = order;
        return "order.xhtml?faces-redirect=true";
    }

    public void deleteOrder(Order order) throws SQLException {
        orderRepository.delete(order.getId());
    }

    public String createOrder() {
        this.order = new Order();
        return "order.xhtml?faces-redirect=true";
    }

    public String saveOrder() throws SQLException {
        if (order.getId() != null) {
            orderRepository.update(order);
        } else {
            orderRepository.insert(order);
        }
        return "/index.xhtml?faces-redirect=true";
    }
}

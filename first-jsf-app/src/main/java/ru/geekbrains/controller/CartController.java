package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

@SessionScoped
@Named
public class CartController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @EJB
    private CartService cartService;

    public CartController() {
    }

    public ArrayList<Map.Entry<ProductRepr, Integer>> getAllProducts() {
        return cartService.getAllProducts(); // TODO
    }

    public void add(ProductRepr productRepr, Integer qty) {
        cartService.add(productRepr, qty);
    }

}

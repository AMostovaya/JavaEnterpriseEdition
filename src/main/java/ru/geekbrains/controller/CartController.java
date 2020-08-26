package ru.geekbrains.controller;

import ru.geekbrains.persist.Cart;
import ru.geekbrains.persist.CartRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    @Inject
    CartRepository cartRepository;

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Cart> getAllCart() throws SQLException {
        return cartRepository.findAll();
    }

    public String editCart(Cart cart) {
        this.cart = cart;
        return "cart.xhtml?faces-redirect=true";
    }

    public void deleteCart(Cart cart) throws SQLException {
        cartRepository.delete(cart.getId());
    }

    public String createCart() {
        this.cart = new Cart();
        return "cart.xhtml?faces-redirect=true";
    }

    public String saveCart() throws SQLException {
        if (cart.getId() != null) {
            cartRepository.update(cart);
        } else {
            cartRepository.insert(cart);
        }
        return "/index.xhtml?faces-redirect=true";
    }
}

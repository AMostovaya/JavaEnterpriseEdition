package ru.geekbrains.persist;

import java.math.BigDecimal;

public class Cart {

    private Long id;

    private Long idProduct;

    private BigDecimal price;

    private BigDecimal qty;

    public Cart() {
    }

    public Cart(Long id, Long idProduct, BigDecimal price, BigDecimal qty) {
        this.id = id;
        this.idProduct = idProduct;
        this.price = price;
        this.qty = qty;
    }

    public Cart(long id, String idProduct, String price, String qty) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }
}

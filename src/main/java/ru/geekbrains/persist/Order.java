package ru.geekbrains.persist;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

    private Long id;
    private Long idProduct;
    private BigDecimal price;
    private BigDecimal qty;
    private String client;
    private Date datePurchase;

    public Order() {
    }

    public Order(Long id, Long idProduct, BigDecimal price, BigDecimal qty, String client, Date datePurchase) {
        this.id = id;
        this.idProduct = idProduct;
        this.price = price;
        this.qty = qty;
        this.client = client;
        this.datePurchase = datePurchase;
    }

    public Order(long id, String idProduct, String price, String qty, String client, String datePurchase) {
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Date datePurchase) {
        this.datePurchase = datePurchase;
    }
}

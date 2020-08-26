package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class CartRepository {

    @Inject
    ServletContext context;

    private  Connection conn;

    public CartRepository() {
    }

    @PostConstruct
    public void init() throws SQLException {
        conn = (Connection) context.getAttribute("connection");
        createTableIfNotExists(conn);

        if (this.findAll().isEmpty()) {
            this.insert(new Cart(-1L, (long) 1, new BigDecimal(23344), new BigDecimal(3)));
            this.insert(new Cart(-1L, (long) 3, new BigDecimal(23457), new BigDecimal(2)));
            this.insert(new Cart(-1L, (long) 5, new BigDecimal(23324), new BigDecimal(1)));
        }
    }

    public void insert(Cart cart) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into cart(idProduct, qty, price) values (?, ?, ?);")) {
            stmt.setLong(1, cart.getIdProduct());
            stmt.setBigDecimal(2, cart.getQty());
            stmt.setBigDecimal(3, cart.getPrice());
            stmt.execute();
        }
    }

    public void update(Cart cart) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update cart set idProduct = ?, qty = ?, price = ? where id = ?;")) {
            stmt.setLong(1, cart.getIdProduct());
            stmt.setBigDecimal(2, cart.getQty());
            stmt.setBigDecimal(3, cart.getPrice());
            stmt.setLong(4, cart.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from cart where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Optional<Cart> findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, idProduct, qty, price from cart where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Cart(rs.getLong(1), rs.getLong(2), rs.getBigDecimal(3), rs.getBigDecimal(4)));
            }
        }
        return Optional.empty();
    }

    public List<Cart> findAll() throws SQLException {
        List<Cart> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, idProduct, qty, price from cart");

            while (rs.next()) {
                res.add(new Cart(rs.getLong(1), rs.getLong(2), rs.getBigDecimal(3), rs.getBigDecimal(4)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists cart (\n" +
                    "    id int auto_increment primary key,\n" +
                    "    idProduct bigint(25),\n" +
                    "    qty decimal(5, 1),\n" +
                    "    price decimal(12, 8) \n" +
                    ");");
        }
    }
}

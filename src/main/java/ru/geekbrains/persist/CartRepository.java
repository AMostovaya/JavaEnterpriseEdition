package ru.geekbrains.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartRepository {

    private final Connection conn;

    public CartRepository(Connection conn) throws SQLException {
        this.conn = conn;
        createTableIfNotExists(conn);
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

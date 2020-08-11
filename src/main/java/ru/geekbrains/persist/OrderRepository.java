package ru.geekbrains.persist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private final Connection conn;

    public OrderRepository(Connection conn) throws SQLException {
        this.conn = conn;
        createTableIfNotExists(conn);
    }

    public void insert(Order order) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into orderT(idProduct, qty, price, client, datePurchase) values (?, ?, ?, ?, ?);")) {
            stmt.setLong(1, order.getIdProduct());
            stmt.setBigDecimal(2, order.getQty());
            stmt.setBigDecimal(3, order.getPrice());
            stmt.setString(4, order.getClient());
            stmt.setDate(5, (Date) order.getDatePurchase());
            stmt.execute();
        }
    }

    public void update(Order order) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update orderT set idProduct = ?, qty = ?, price = ?, client = ?, datePurchase = ? where id = ?;")) {
            stmt.setLong(1, order.getIdProduct());
            stmt.setBigDecimal(2, order.getQty());
            stmt.setBigDecimal(3, order.getPrice());
            stmt.setLong(4, order.getId());
            stmt.setString(5, order.getClient());
            stmt.setDate(6, (Date) order.getDatePurchase());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from orderT where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Optional<Order> findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, idProduct, qty, price, client, datePurchase from orderT where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Order(rs.getLong(1),
                        rs.getLong(2),
                        rs.getBigDecimal(3),
                        rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getDate(6)));
            }
        }
        return Optional.empty();
    }

    public List<Order> findAll() throws SQLException {
        List<Order> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, idProduct, qty, price, client, datePurchase from orderT");

            while (rs.next()) {
                res.add(new Order(rs.getLong(1),
                        rs.getLong(2),
                        rs.getBigDecimal(3),
                        rs.getBigDecimal(4),
                        rs.getString(5),
                        rs.getDate(6)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists orderT (\n" +
                    "    id int auto_increment primary key,\n" +
                    "    idProduct bigint(25),\n" +
                    "    qty decimal(5, 1),\n" +
                    "    price decimal(12, 8), \n" +
                    "    client varchar(50), \n" +
                    "    datePurchase date \n" +
                    ");");
        }
    }
}

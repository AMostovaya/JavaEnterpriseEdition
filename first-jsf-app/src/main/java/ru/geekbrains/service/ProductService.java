package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface ProductService {

    void insert(ProductRepr productRepr);

    void update(ProductRepr productRepr);

    void delete(long id);

    Optional<ProductRepr> findById(long id);

    List<ProductRepr> findAll();

}

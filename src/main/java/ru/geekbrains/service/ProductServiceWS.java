package ru.geekbrains.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceWS {

    @WebMethod
    void insert(ProductRepr productRepr);

    @WebMethod
    void delete(Long id);

    @WebMethod
    List<ProductRepr> findAll();

    @WebMethod
    ProductRepr getProductById(Long id);

    @WebMethod
    ProductRepr getProductByName(String name);

    @WebMethod
    List<ProductRepr> getProductsByCategoryId(Long id);

    @WebMethod
    void addCategory(CategoryRepr categoryRepr);
}

package ru.geekbrains.service;

import ru.geekbrains.persist.Product;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Local
public interface CartService {

    void add(ProductRepr productRepr, Integer qty);

    ArrayList<Map.Entry<ProductRepr, Integer>> getAllProducts();


}

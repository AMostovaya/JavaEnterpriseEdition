package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.controller.ProductController;

import javax.ejb.Stateful;
import java.util.*;

@Stateful
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    //private final List<ProductRepr> productReprList;
    private final HashMap<ProductRepr, Integer> productReprList;

    public CartServiceImpl() {
        this.productReprList = new HashMap<ProductRepr, Integer>();
    }

    @Override
    public void add(ProductRepr productRepr, Integer qty) {

        Iterator<Map.Entry<ProductRepr, Integer> >
                iterator = productReprList.entrySet().iterator();

        boolean isKeyPresent = false;

        ProductRepr currentProduct = null;
        Integer currentQty = 0;
        while (iterator.hasNext()) {
           Map.Entry<ProductRepr, Integer> entry = iterator.next();
           if (productRepr.getId() == entry.getKey().getId()) {
               // такой ключ уже существует
               isKeyPresent = true;
               currentQty = entry.getValue();
               currentProduct = entry.getKey();
           }
        }

        if (isKeyPresent) {
            // обновляем количество в списке
            productReprList.replace(currentProduct, currentQty+1);
        } else  {
            productReprList.put(productRepr, qty);
        }
    }

    @Override
    public ArrayList<Map.Entry<ProductRepr, Integer>> getAllProducts() {

        Set<Map.Entry<ProductRepr, Integer>> entrySet = productReprList.entrySet();
        ArrayList<Map.Entry<ProductRepr, Integer>> listOfEntry = new ArrayList<Map.Entry<ProductRepr, Integer>>(entrySet);

        return listOfEntry;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        ProductRepr otherPos = (ProductRepr) o;
        return ((ProductRepr) o).getName() == otherPos.getName()
                && ((ProductRepr) o).getId() == otherPos.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}

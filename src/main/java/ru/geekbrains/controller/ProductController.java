package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Inject
    ProductRepository productRepository;

    private Product product;

    @Inject
    private CategoryRepository categoryRepository;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getAllProducts()  {
        return productRepository.findAll();
    }

    public String editProduct(Product product) {
        this.product = product;

        return "product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product)  {
        productRepository.delete(product.getId());
    }

    public String createProduct() {
        this.product = new Product();
        return "product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        logger.info("SAVE");
        if (product.getId() != null) {
            productRepository.update(product);
        } else {
            productRepository.insert(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories() {
        logger.info("GETTING CATEGORY" + categoryRepository.findAll());
        return categoryRepository.findAll();
    }
}

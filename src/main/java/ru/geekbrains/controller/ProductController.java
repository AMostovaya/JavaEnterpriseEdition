package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @EJB
    ProductService productService;

    private ProductRepr product;

    @EJB
    private CategoryRepository categoryRepository;

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(ProductRepr product) {
        this.product = product;
    }

    public List<ProductRepr> getAllProducts()  {
        return productService.findAll();
    }

    public String editProduct(ProductRepr product) {
        this.product = product;

        return "product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductRepr product)  {
        productService.delete(product.getId());
    }

    public String createProduct() {
        this.product = new ProductRepr();
        return "product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        logger.info("SAVE");
        if (product.getId() != null) {
            productService.update(product);
        } else {
            productService.insert(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategories() {
        logger.info("GETTING CATEGORY" + categoryRepository.findAll());
        return categoryRepository.findAll();
    }
}

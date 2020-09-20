package ru.geekbrains.service;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.rest.ProductServiceRS;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService, ProductServiceRS, ProductServiceWS {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @TransactionAttribute
    @Override
    public void insert(ProductRepr productRepr) {
        Category category = categoryRepository.findById(productRepr.getCategoryId())
                .orElse(null);
        Product product = new Product(productRepr.getId(),
                productRepr.getName(),
                productRepr.getDescription(),
                productRepr.getPrice(),
                category);
        productRepository.insert(product);
    }

    @Override
    public void delete(Long id) {

    }

    @TransactionAttribute
    @Override
    public void update(ProductRepr productRepr) {
        Category category = categoryRepository.findById(productRepr.getCategoryId())
                .orElse(null);
        Product product = new Product(productRepr.getId(),
                productRepr.getName(),
                productRepr.getDescription(),
                productRepr.getPrice(),
                category);
        productRepository.update(product);
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public Optional<ProductRepr> findById(long id) {
        return productRepository.findById(id)
                .map(ProductRepr::new);
    }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream().map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRepr getProductById(Long id) {
        return new ProductRepr(productRepository.findById(id).get());
    }

    @Override
    public ProductRepr getProductByName(String name) {
        return new ProductRepr(productRepository.findByName(name));
    }

    @Override
    public List<ProductRepr> getProductsByCategoryId(Long id) {
        return null;
    }

    @Override
    public ProductRepr findByIdRS(long id) {
        return findById(id).get();
    }

    @Override
    public void addCategory(CategoryRepr categoryRepr) {
        Category category = new Category(categoryRepr.getId(), categoryRepr.getName());
        categoryRepository.insert(category);
    }

    @Override
    public List<ProductRepr> findByCategoryIdRs(Long id) {
        return productRepository.findProductByCategoryId(id).stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }
}

package ru.geekbrains.persist;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.controller.ProductController;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Stateless
@SecurityDomain("servlet-security-quickstart")
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @EJB
    private CategoryRepository categoryRepository;

    @Resource
    private SessionContext ejbContext;

    public ProductRepository()  {

    }

    @RolesAllowed("ADMIN")
    public void insert(Product product)  {
        em.persist(product);
    }

    @RolesAllowed({"ADMIN"})
    public void update(Product product)  {
        em.merge(product);
    }

    @RolesAllowed({"ADMIN"})
    public void delete(long id)  {
        Product product = em.find(Product.class, id);
        if (product!= null) {
            em.remove(product);
        }
    }

    public Optional<Product> findById(long id)  {
        Product product = em.find(Product.class, id);
        if (product != null) {
            return Optional.of(product);
        }
        return Optional.empty();
    }

    @PermitAll
    public List<Product> findAll()  {
        return em.createQuery("from Product", Product.class)
                .getResultList();
    }

    public List<Product> findProductByCategoryId(Long id) {
        Category category = em.find(Category.class, id);
        return em.createQuery("from Product where category = ?1", Product.class)
                .setParameter(1, category)
                .getResultList();
    }

    public Product findByName(String name) {
        return em.createQuery("from Product where name = ?1", Product.class)
                .setParameter(1, name)
                .getSingleResult();
    }
}

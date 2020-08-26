package ru.geekbrains.persist;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class ProductRepository {

    @Resource
    UserTransaction userTransaction;

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public ProductRepository()  {

    }

    @PostConstruct
    public void init() throws  SystemException, NotSupportedException {

        if (this.findAll().isEmpty()) {
            try {

                userTransaction.begin();

            /*    this.insert(new Product(-1L, "Apple Macbook pro 2015", "Apple profession laptop", new BigDecimal(3000)));
                this.insert(new Product(-1L, "Apple Macbook air 2015", "Apple netbook", new BigDecimal(2000)));
                this.insert(new Product(-1L, "Apple iPad", "Apple tablet", new BigDecimal(1000)));
*/
                userTransaction.commit();

            } catch (Exception exception) {
                userTransaction.rollback();
            }
        }
    }


    @Transactional
    public void insert(Product product)  {
        em.persist(product);
    }

    @Transactional
    public void update(Product product)  {
        em.merge(product);
    }

    @Transactional
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

    public List<Product> findAll()  {
        return em.createQuery("from Product", Product.class)
                .getResultList();
    }

}

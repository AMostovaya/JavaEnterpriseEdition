package ru.geekbrains.persist;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class RoleRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public RoleRepository() {
    }

    @TransactionAttribute
    public void insert(Role role) {
        em.persist(role);
    }

    @TransactionAttribute
    public void update(Role role) {
        em.merge(role);
    }

    @TransactionAttribute
    public void delete(long id) {
        Role role = em.find(Role.class, id);
        if (role != null) {
            em.remove(role);
        }
    }

    public Optional<Role> findById(long id) {
        Role role = em.find(Role.class, id);
        if (role != null) {
            return Optional.of(role);
        }
        return Optional.empty();
    }

    public List<Role> findAll() {
        return em.createQuery("from Role", Role.class)
                .getResultList();
    }

    public Optional<Role> findByName(String name) {
        Role role = em.createQuery("from Role c where c.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
        if (role != null) {
            return Optional.of(role);
        } else {
            return Optional.empty();
        }
    }


}

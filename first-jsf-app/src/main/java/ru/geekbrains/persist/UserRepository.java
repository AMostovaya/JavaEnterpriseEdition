package ru.geekbrains.persist;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @EJB
    private RoleRepository roleRepository;

    public UserRepository() {
    }

    public void insert(User user)  {
        em.persist(user);
    }

    public void update(User user)  {
        em.merge(user);
    }

    public void delete(long id)  {
        User user = em.find(User.class, id);
        if (user!= null) {
            em.remove(user);
        }
    }

    public Optional<User> findById(long id)  {
        User user = em.find(User.class, id);
        if (user != null) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public List<User> findAll()  {
        return em.createQuery("from User", User.class)
                .getResultList();
    }

    public List<User> findUserByRoleId(Long id) {
        Role role = em.find(Role.class, id);
        return em.createQuery("from User where role = ?1", User.class)
                .setParameter(1, role)
                .getResultList();
    }

    public User findByName(String name) {
        return em.createQuery("from User where name = ?1", User.class)
                .setParameter(1, name)
                .getSingleResult();
    }


}

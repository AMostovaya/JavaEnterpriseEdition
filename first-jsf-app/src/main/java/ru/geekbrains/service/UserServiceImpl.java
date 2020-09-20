package ru.geekbrains.service;

import ru.geekbrains.persist.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    private UserRepository userRepository;

    @EJB
    private RoleRepository roleRepository;

    @TransactionAttribute
    @Override
    public void insert(UserRepr userRepr) {
        Role role = roleRepository.findById(userRepr.getRoleId())
                .orElse(null);
        User user = new User(userRepr.getId(),
                userRepr.getName(),
                userRepr.getPassword(),
                role);
        userRepository.insert(user);
    }

    @TransactionAttribute
    @Override
    public void update(UserRepr userRepr) {

        Role role = roleRepository.findById(userRepr.getRoleId())
                .orElse(null);
        User user = new User(userRepr.getId(),
                userRepr.getName(),
                userRepr.getPassword(),
                role);
        userRepository.update(user);
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Optional<UserRepr> findById(long id) {
        return userRepository.findById(id)
                .map(UserRepr::new);
    }

    @Override
    public List<UserRepr> findAll() {
        return userRepository.findAll().stream().map(UserRepr::new)
                .collect(Collectors.toList());
    }
}

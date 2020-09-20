package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface UserService {

    void insert(UserRepr userRepr);

    void update(UserRepr userRepr);

    void delete(long id);

    Optional<UserRepr> findById(long id);

    List<UserRepr> findAll();
}

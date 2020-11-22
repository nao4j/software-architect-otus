package ru.yegorpilipenko.otus.architect.crud.repository;

import org.springframework.data.repository.Repository;
import ru.yegorpilipenko.otus.architect.crud.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    boolean existsById(Long id);

    Collection<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);

}

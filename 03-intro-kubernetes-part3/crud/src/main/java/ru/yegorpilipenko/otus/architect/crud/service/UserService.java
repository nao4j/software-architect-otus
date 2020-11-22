package ru.yegorpilipenko.otus.architect.crud.service;

import ru.yegorpilipenko.otus.architect.crud.dto.UserDto;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Collection<UserDto> getAll();

    Optional<UserDto> getById(long id);

    UserDto create(UserDto user);

    Optional<UserDto> update(UserDto user);

    boolean remove(long id);

}

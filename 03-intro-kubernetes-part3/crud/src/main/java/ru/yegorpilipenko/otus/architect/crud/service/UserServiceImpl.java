package ru.yegorpilipenko.otus.architect.crud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yegorpilipenko.otus.architect.crud.dto.UserDto;
import ru.yegorpilipenko.otus.architect.crud.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Collection<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(toList());
    }

    @Override
    public Optional<UserDto> getById(final long id) {
        return userRepository.findById(id).map(UserDto::new);
    }

    @Override
    @Transactional
    public UserDto create(final UserDto user) {
        final var entity = user.toEntity();
        entity.setId(null);
        return new UserDto(userRepository.save(entity));
    }

    @Override
    @Transactional
    public Optional<UserDto> update(final UserDto user) {
        final var id = user.getId();
        if (id == null) {
            return Optional.of(create(user));
        }
        if (!userRepository.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(new UserDto(userRepository.save(user.toEntity())));
    }

    @Override
    @Transactional
    public boolean remove(final long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}

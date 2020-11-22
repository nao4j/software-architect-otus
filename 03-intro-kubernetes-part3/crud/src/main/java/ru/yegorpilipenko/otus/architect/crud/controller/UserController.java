package ru.yegorpilipenko.otus.architect.crud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.yegorpilipenko.otus.architect.crud.dto.UserDto;
import ru.yegorpilipenko.otus.architect.crud.service.UserService;

import java.util.Collection;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Collection<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> get(@PathVariable final long id) {
        return userService.getById(id).map(ResponseEntity::ok).orElseGet(notFound()::build);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody final UserDto user) {
        try {
            return status(CREATED).body(userService.create(user));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(BAD_REQUEST, "Username, email or phone is not unique", e);
        }
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody final UserDto user) {
        try {
            return userService.update(user).map(ResponseEntity::ok).orElseGet(notFound()::build);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(BAD_REQUEST, "Username, email or phone is not unique", e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> remove(@PathVariable final long id) {
        return userService.remove(id) ? status(NO_CONTENT).build() : notFound().build();
    }

}

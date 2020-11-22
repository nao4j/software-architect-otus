package ru.yegorpilipenko.otus.architect.crud.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.yegorpilipenko.otus.architect.crud.entity.User;

import static lombok.AccessLevel.PRIVATE;

@Data
@RequiredArgsConstructor(access = PRIVATE)
public class UserDto {

    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;

    public UserDto(final User user) {
        id = user.getId();
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        phone = user.getPhone();
    }

    public User toEntity() {
        final User entity = new User();
        entity.setId(id);
        entity.setUsername(username);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setEmail(email);
        entity.setPhone(phone);
        return entity;
    }

}

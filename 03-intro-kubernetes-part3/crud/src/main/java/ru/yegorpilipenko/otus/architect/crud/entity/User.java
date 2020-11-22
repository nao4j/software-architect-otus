package ru.yegorpilipenko.otus.architect.crud.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "users", schema = "crud")
public class User {

    @Id
    @GeneratedValue(generator = "users_id_generator", strategy = SEQUENCE)
    @SequenceGenerator(name = "users_id_generator", sequenceName = "users_id_seq", schema = "crud")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", unique = true, length = 15)
    private String phone;

}

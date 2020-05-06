package ru.gurtovenko.dao;

import ru.gurtovenko.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();

    User getOne(String email);

    void add(User user);
}

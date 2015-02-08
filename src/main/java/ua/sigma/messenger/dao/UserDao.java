package ua.sigma.messenger.dao;

import ua.sigma.messenger.model.User;

import java.util.List;

/**
 * Created by vlad on 28.01.15.
 */
public interface UserDao extends GenerycCRUD<User> {
    User findByLogin(String login);
    User findByEmail(String email);
    List<User> findAll();
    User findOne(int id);
}

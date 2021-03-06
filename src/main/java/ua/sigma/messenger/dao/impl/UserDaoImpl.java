package ua.sigma.messenger.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.UserDao;
import ua.sigma.messenger.model.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by vlad on 28.01.15.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "messengerPersistence")
    private EntityManager entityManager;

    @Override
    public User findByLogin(String login) {
        try {
            Query q = entityManager.createQuery("SELECT x FROM User x WHERE x.login = :loginParam");
            User user = (User) q.setParameter("loginParam", login).getSingleResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            Query q = entityManager.createQuery("SELECT x FROM User x WHERE x.email = :emailParam");
            User user = (User) q.setParameter("emailParam", email).getSingleResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findOne(int id) {
        //TODO
        return null;
    }

    @Override
    public void create(User value) {
        entityManager.merge(value);
        entityManager.flush();
    }

    @Override
    public void update(User value) {
        entityManager.merge(value);
    }

    @Override
    public void delete(User value) {
        entityManager.remove(value);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT x FROM User x", User.class);
        List<User> users = query.getResultList();
        return users;
    }
}

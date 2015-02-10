package ua.sigma.messenger.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.PasswordResetTokenDao;
import ua.sigma.messenger.model.PasswordResetToken;
import ua.sigma.messenger.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Maks on 08.02.2015.
 */
@Repository
@Transactional
public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao {
    @PersistenceContext(unitName = "messengerPersistence")
    private EntityManager entityManager;

    @Override
    public PasswordResetToken findByToken(String token) {
        try {
            Query q = entityManager.createQuery("SELECT x FROM PasswordResetToken  x WHERE x.token = :tokenParam");
            PasswordResetToken passwordResetToken = (PasswordResetToken) q.setParameter("tokenParam", token).getSingleResult();
            return passwordResetToken;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public PasswordResetToken findByUser(User user) {
        //TODO
        return null;
    }

    @Override
    public void create(PasswordResetToken value) {
        entityManager.merge(value);
        entityManager.flush();
    }

    @Override
    public void update(PasswordResetToken value) {
        entityManager.merge(value);
    }

    @Override
    public void delete(PasswordResetToken value) {
        entityManager.remove(value);
    }
}

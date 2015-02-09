package ua.sigma.messenger.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.VerificationTokenDao;
import ua.sigma.messenger.model.User;
import ua.sigma.messenger.model.VerificationToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Maks on 02.02.2015.
 */
@Repository
@Transactional
public class VerificationTokenDaoImpl implements VerificationTokenDao {

    @PersistenceContext(unitName = "messengerPersistence")
    private EntityManager entityManager;

    @Override
    public VerificationToken findByToken(String token) {
        //TODO
        return null;
    }

    @Override
    public VerificationToken findByUser(User user) {
        //TODO
        return null;
    }

    @Override
    public void create(VerificationToken value) {
        entityManager.merge(value);
        entityManager.flush();
    }

    @Override
    public void update(VerificationToken value) {
        entityManager.merge(value);
    }

    @Override
    public void delete(VerificationToken value) {
        entityManager.remove(value);
    }

    @Override
    public List<VerificationToken> findAll() {
        TypedQuery<VerificationToken> query= entityManager.createQuery("SELECT x FROM VerificationToken x", VerificationToken.class);
        List<VerificationToken> verificationTokens = query.getResultList();
        return verificationTokens;
    }
}

package ua.sigma.messenger.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.ChatDao;
import ua.sigma.messenger.model.Chat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by vlad on 28.01.15.
 */
@Repository
@Transactional
public class ChatDaoImpl implements ChatDao {

    @PersistenceContext(unitName = "messengerPersistence")
    private EntityManager entityManager;

    @Override
    public Chat findById(int id) {
        Query q = entityManager.createQuery("SELECT x FROM Chat x WHERE x.id = :idParam");
        Chat topic = (Chat) q.setParameter("idParam", id).getSingleResult();
        return topic;
    }

    @Override
    public void create(Chat value) {
        entityManager.merge(value);
        entityManager.flush();
    }

    @Override
    public void update(Chat value) {
        entityManager.merge(value);
    }

    @Override
    public void delete(Chat value) {
        entityManager.remove(value);
    }

}

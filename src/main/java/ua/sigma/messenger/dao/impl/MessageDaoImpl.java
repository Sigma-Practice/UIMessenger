package ua.sigma.messenger.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.MessageDao;
import ua.sigma.messenger.model.Chat;
import ua.sigma.messenger.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 28.01.15.
 */
@Repository
@Transactional
public class MessageDaoImpl implements MessageDao {

    @PersistenceContext(unitName = "messengerPersistence")
    private EntityManager entityManager;

    @Override
    public void create(Message value) {
        entityManager.merge(value);
        entityManager.flush();
    }

    @Override
    public void update(Message value) {
        entityManager.merge(value);
    }

    @Override
    public void delete(Message value) {
        entityManager.remove(value);
    }

    @Override
    public List<Message> findNewMessage(Chat chat, Date from) {
        return null;
    }

    @Override
    public List<Message> findByChatId(int id) {
        TypedQuery<Message> query = entityManager.createQuery("SELECT x FROM Message x WHERE x.chatId= :idParam", Message.class);
        query.setParameter("idParam", id);
        List<Message> messages = query.getResultList();
        return messages;
    }
}

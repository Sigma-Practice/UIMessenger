package ua.sigma.messenger.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.TopicDao;
import ua.sigma.messenger.model.Topic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by vlad on 28.01.15.
 */
@Repository
@Transactional
public class TopicDaoImpl implements TopicDao {

    @PersistenceContext(unitName = "messengerPersistence")
    private EntityManager entityManager;


    @Override
    public Topic findById(int id) {
        Query q = entityManager.createQuery("SELECT x FROM Topic x WHERE x.id = :idParam");
        Topic topic = (Topic) q.setParameter("idParam", id).getSingleResult();
        return topic;
    }

    @Override
    public void create(Topic value) {
        entityManager.merge(value);
        entityManager.flush();
    }

    @Override
    public void update(Topic value) {
        entityManager.merge(value);
    }

    @Override
    public void delete(Topic value) {
        entityManager.remove(value);
    }

    @Override
    public List<Topic> findAll() {
        TypedQuery<Topic> query= entityManager.createQuery("SELECT x FROM Topic x", Topic.class);
        List<Topic> topocs = query.getResultList();
        return topocs;
    }
}

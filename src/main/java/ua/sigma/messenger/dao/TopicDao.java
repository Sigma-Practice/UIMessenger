package ua.sigma.messenger.dao;

import ua.sigma.messenger.model.Topic;

import java.util.List;

/**
 * Created by vlad on 28.01.15.
 */
public interface TopicDao extends GenerycCRUD<Topic> {
    Topic findById(int id);
    List<Topic> findAll();
}

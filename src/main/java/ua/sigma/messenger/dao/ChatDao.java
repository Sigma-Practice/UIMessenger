package ua.sigma.messenger.dao;

import ua.sigma.messenger.model.Chat;

import java.util.List;

/**
 * Created by vlad on 28.01.15.
 */
public interface ChatDao extends GenerycCRUD<Chat> {
    Chat findById(int id);

    List<Chat> findAll();

    List<Chat> findAllForTopicId(int id);
}

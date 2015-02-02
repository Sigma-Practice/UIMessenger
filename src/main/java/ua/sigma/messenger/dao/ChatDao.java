package ua.sigma.messenger.dao;

import ua.sigma.messenger.model.Chat;

/**
 * Created by vlad on 28.01.15.
 */
public interface ChatDao extends GenerycCRUD<Chat> {
    Chat findById(int id);
}

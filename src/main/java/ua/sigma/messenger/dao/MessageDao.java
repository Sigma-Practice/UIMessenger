package ua.sigma.messenger.dao;

import ua.sigma.messenger.model.Chat;
import ua.sigma.messenger.model.Message;

import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 28.01.15.
 */
public interface MessageDao extends GenerycCRUD<Message> {

    List<Message> findNewMessage(Chat chat, Date from);

    List<Message> findByChatId(int id);
}

package ua.sigma.messenger.model;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vlad on 24.01.15.
 */
@Entity
@Access(FIELD)
public class Message {
    @GeneratedValue(strategy = IDENTITY)
    @Id
    int id;
    String text;
    Date sent;
    int chatId;
    int userId;

    public Message(String text, Date sent, int chatId, int userId) {
        this.text = text;
        this.sent = sent;
        this.chatId = chatId;
        this.userId = userId;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getSent() {
        return sent;
    }

    public int getChatId() {
        return chatId;
    }

    public int getUserId() {
        return userId;
    }
}

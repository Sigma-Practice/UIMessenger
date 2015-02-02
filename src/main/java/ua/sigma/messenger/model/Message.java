package ua.sigma.messenger.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vlad on 24.01.15.
 */
@Entity
@Access(FIELD)
public class Message {
    @GeneratedValue(strategy = IDENTITY)
    @Id int id;
    String text;
    Date sent;
    @ManyToOne(fetch = LAZY, optional = false)
    Chat chat;
    @ManyToOne(fetch = EAGER)
    User user;

    public Message(String text, Date sent, Chat chat, User user) {
        this.text = text;
        this.sent = sent;
        this.chat = chat;
        this.user = user;
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

    public Chat getChat() {
        return chat;
    }

    public User getUser() {
        return user;
    }
}

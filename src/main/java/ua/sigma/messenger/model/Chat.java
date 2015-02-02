package ua.sigma.messenger.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vlad on 23.01.15.
 */
@Entity
@Access(FIELD)
public class Chat {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Topic getTopic() {
        return topic;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public User getUser() {
        return user;
    }

    @GeneratedValue(strategy = IDENTITY)
    @Id int id;
    String name;
    @ManyToOne(fetch = LAZY, optional = false)
    Topic topic;
    @OneToMany(fetch = LAZY, mappedBy = "chat")
    List<Message> messages=new ArrayList<Message>();
    @ManyToOne(fetch = EAGER, optional = false)
    User user;

    public Chat() {
    }

    public Chat(String name, Topic topic, List<Message> messages, User user) {
        this.name = name;
        this.topic = topic;
        this.messages = messages;
        this.user = user;
    }
}

package ua.sigma.messenger.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vlad on 23.01.15.
 */
@Entity @Access(FIELD)
public class Topic {
    @GeneratedValue(strategy = IDENTITY)
    @Id int id;
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    @OneToMany(fetch = LAZY, mappedBy = "topic")
    Set<Chat> chats=new HashSet<Chat>(0);
    public Topic() {
    }

    public Topic(String name, Set<Chat> chats) {
        this.name = name;
        this.chats = chats;
    }
}

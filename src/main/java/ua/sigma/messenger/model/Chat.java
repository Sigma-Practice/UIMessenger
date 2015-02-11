package ua.sigma.messenger.model;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

import static javax.persistence.AccessType.FIELD;
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

    public int getTopicId() {
        return topicId;
    }

    @GeneratedValue(strategy = IDENTITY)
    @Id
    int id;
    String name;
    int topicId;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public Chat() {

    }

    public Chat(String name, int topicId, List<Message> messages) {
        this.name = name;
        this.topicId = topicId;
    }
}

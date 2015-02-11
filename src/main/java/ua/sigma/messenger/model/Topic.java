package ua.sigma.messenger.model;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by vlad on 23.01.15.
 */
@Entity
@Access(FIELD)
public class Topic {
    @GeneratedValue(strategy = IDENTITY)
    @Id
    int id;
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Topic() {
    }

    public Topic(String name) {
        this.name = name;
    }
}

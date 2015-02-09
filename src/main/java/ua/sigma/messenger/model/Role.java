package ua.sigma.messenger.model;

import javax.persistence.*;

import static javax.persistence.AccessType.FIELD;

/**
 * Created by Maks on 03.02.2015.
 */
@Entity
@Access(FIELD)
@Table(name = "user_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String role;

    public Role(String login, String role) {
        this.login = login;
        this.role = role;
    }

    public Role() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

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
@Entity @Access(FIELD)
public class User {
    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getInfo() {
        return info;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public boolean isMute() {
        return isMute;
    }

    @GeneratedValue(strategy = IDENTITY)
    @Id int id;
    String login;
    String email;
    String password;
    String fullname;
    String phone;
    String info;
    boolean isActivated;
    boolean isMute;

    public User(String login, String email, String password, String fullname, String phone, String info, boolean isActivated, boolean isMute) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.info = info;
        this.isActivated = isActivated;
        this.isMute = isMute;
    }

    public User() {

    }
}

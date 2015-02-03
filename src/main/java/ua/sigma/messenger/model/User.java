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

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public void setMute(boolean isMute) {
        this.isMute = isMute;
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

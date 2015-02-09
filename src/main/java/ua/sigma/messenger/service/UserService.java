package ua.sigma.messenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.PasswordResetTokenDao;
import ua.sigma.messenger.dao.RoleDao;
import ua.sigma.messenger.dao.UserDao;
import ua.sigma.messenger.dao.VerificationTokenDao;
import ua.sigma.messenger.dao.impl.PasswordResetTokenDaoImpl;
import ua.sigma.messenger.dao.impl.VerificationTokenDaoImpl;
import ua.sigma.messenger.model.PasswordResetToken;
import ua.sigma.messenger.model.Role;
import ua.sigma.messenger.model.User;
import ua.sigma.messenger.model.VerificationToken;
import ua.sigma.messenger.validation.EmailExistsException;
import ua.sigma.messenger.validation.LoginExistsException;

import java.util.UUID;

/**
 * Created by Maks on 02.02.2015.
 */

@Service
@Transactional
public class UserService implements IUserService {
    private final static String ROLE_USER = "ROLE_USER";
    @Autowired
    UserDao userDao;

    @Autowired
    private VerificationTokenDao tokenDao;

    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException, LoginExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress: " + accountDto.getEmail());
        } else if (loginExist(accountDto.getEmail())) {
            throw new LoginExistsException("There is an account with that login" + accountDto.getLogin());
        }
        final User user = new User();

        user.setFullname(accountDto.getFullName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setLogin(accountDto.getLogin());
        user.setPhone(accountDto.getPhone());
        user.setInfo(accountDto.getInfo());
        userDao.create(user);
        roleDao.create(new Role(user.getLogin(), ROLE_USER));
        return userDao.findByEmail(user.getEmail());
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenDao.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userDao.create(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken vToken = new VerificationToken(token, user);
        tokenDao.create(vToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenDao.findByToken(verificationToken);
    }

    public VerificationToken updateVerificationToken(String verificationToken) {
        VerificationToken vToken = tokenDao.findByToken(verificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        tokenDao.update(vToken);
        return vToken;
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken pToken = new PasswordResetToken(token, user);
        passwordResetTokenDao.create(pToken);
    }

    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenDao.findByToken(token);
    }

    public User getUserByPasswordResetToken(String token) {
        return passwordResetTokenDao.findByToken(token).getUser();
    }

    public User getUserByID(int id) {
        return userDao.findOne(id);
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userDao.update(user);
    }

    private boolean emailExist(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    private boolean loginExist(String login) {
        User user = userDao.findByLogin(login);
        if (user != null) {
            return true;
        }
        return false;
    }
}

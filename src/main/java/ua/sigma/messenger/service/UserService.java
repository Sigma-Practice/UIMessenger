package ua.sigma.messenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.impl.RoleDaoImpl;
import ua.sigma.messenger.dao.impl.UserDaoImpl;
import ua.sigma.messenger.dao.impl.VerificationTokenDaoImpl;
import ua.sigma.messenger.model.Role;
import ua.sigma.messenger.model.User;
import ua.sigma.messenger.model.VerificationToken;
import ua.sigma.messenger.validation.EmailExistsException;

/**
 * Created by Maks on 02.02.2015.
 */

@Service
@Transactional
public class UserService implements IUserService {
    private final static String ROLE_USER = "ROLE_USER";
    UserDaoImpl userDao;

    private VerificationTokenDaoImpl tokenDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private RoleDaoImpl roleDao;

    @Override
    public User registerNewUserAccount(UserDto accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress: " + accountDto.getEmail());
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
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {

    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return null;
    }

    private boolean emailExist(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}

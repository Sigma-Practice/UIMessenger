package ua.sigma.messenger.service;

import ua.sigma.messenger.model.User;
import ua.sigma.messenger.model.VerificationToken;
import ua.sigma.messenger.validation.EmailExistsException;

/**
 * Created by Maks on 02.02.2015.
 */
public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
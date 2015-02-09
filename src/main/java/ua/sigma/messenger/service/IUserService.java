package ua.sigma.messenger.service;

import ua.sigma.messenger.model.PasswordResetToken;
import ua.sigma.messenger.model.User;
import ua.sigma.messenger.model.VerificationToken;
import ua.sigma.messenger.validation.EmailExistsException;
import ua.sigma.messenger.validation.LoginExistsException;

/**
 * Created by Maks on 02.02.2015.
 */
public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException, LoginExistsException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken updateVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    User getUserByID(int id);

    void changeUserPassword(User user, String password);
}
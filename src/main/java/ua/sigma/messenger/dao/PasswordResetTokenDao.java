package ua.sigma.messenger.dao;

import ua.sigma.messenger.model.PasswordResetToken;
import ua.sigma.messenger.model.User;

/**
 * Created by Maks on 08.02.2015.
 */
public interface PasswordResetTokenDao extends GenerycCRUD<PasswordResetToken> {
    public PasswordResetToken findByToken(String token);

    public PasswordResetToken findByUser(User user);
}

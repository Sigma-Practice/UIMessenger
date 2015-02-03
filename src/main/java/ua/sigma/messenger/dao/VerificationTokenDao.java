package ua.sigma.messenger.dao;

import ua.sigma.messenger.model.User;
import ua.sigma.messenger.model.VerificationToken;

import java.util.List;

/**
 * Created by Maks on 02.02.2015.
 */
public interface VerificationTokenDao extends GenerycCRUD<VerificationToken> {
    public VerificationToken findByToken(String token);

    public VerificationToken findByUser(User user);

    public List<VerificationToken> findAll();
}

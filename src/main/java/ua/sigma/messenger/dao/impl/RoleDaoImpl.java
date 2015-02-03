package ua.sigma.messenger.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.sigma.messenger.dao.RoleDao;
import ua.sigma.messenger.model.Role;
import ua.sigma.messenger.model.VerificationToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Maks on 03.02.2015.
 */
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext(unitName = "messengerPersistence")
    private EntityManager entityManager;

    @Override
    public void create(Role value) {
        entityManager.merge(value);
        entityManager.flush();
    }

    @Override
    public void update(Role value) {
        entityManager.merge(value);
    }

    @Override
    public void delete(Role value) {
        entityManager.remove(value);
    }

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> query= entityManager.createQuery("SELECT x FROM Role x", Role.class);
        List<Role> roles = query.getResultList();
        return roles;
    }
}

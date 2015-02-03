package ua.sigma.messenger.dao;

import ua.sigma.messenger.model.Role;

import java.util.List;

/**
 * Created by Maks on 03.02.2015.
 */
public interface RoleDao extends GenerycCRUD<Role> {
    List<Role> findAll();
}

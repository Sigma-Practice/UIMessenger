package ua.sigma.messenger.dao;

/**
 * Created by vlad on 28.01.15.
 */
public interface GenerycCRUD<T> {
    void create(T value);
    void update(T value);
    void delete(T value);
}

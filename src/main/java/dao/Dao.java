package dao;

import java.sql.SQLException;

public interface Dao<T> {
    public T save(T t) throws SQLException;

    public T get(String id) throws SQLException;

    public T update(T t) throws SQLException;

    public T delete(String id) throws SQLException;
}

package dao;

import dto.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PersonDao extends Dao<Person> {
    public List<Person> selectAll(String TableName) throws SQLException;
}

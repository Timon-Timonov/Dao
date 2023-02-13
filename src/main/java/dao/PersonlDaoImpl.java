package dao;

import connection.Connector;
import dto.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonlDaoImpl implements PersonDao {

    Connector connector = null;

    public PersonlDaoImpl(Connector connector) {
        this.connector = connector;
    }


    @Override
    public List<Person> selectAll(String tableName) throws SQLException {
        List<Person> listWithAllDB = new ArrayList<>();
        Statement statement = connector.getStatement();

        ResultSet resultSet = statement.executeQuery(dao.ConstDao.SELECT_FROM + " " + tableName);

        int columnNumber = 1;

        while (resultSet.next()) {
            listWithAllDB.add(new Person()
                    .setId(Integer.valueOf(resultSet.getString(columnNumber++)))
                    .setSurname(resultSet.getString(columnNumber++))
                    .setName(resultSet.getString(columnNumber++)));
            columnNumber = 1;
        }
        resultSet.close();
        return listWithAllDB;
    }

    @Override
    //id в объекте person игнорируется(может вообще не передаваться),т.к. установлен автоинкремент. Данные вносятся в новую строку.
    public Person save(Person person) throws SQLException {
        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append(dao.ConstDao.INSERT_QUERY);
        sqlQuery.append(person.getSurname());
        sqlQuery.append(dao.ConstDao.BETWEEN_INSERT_VALUES);
        sqlQuery.append(person.getName());
        sqlQuery.append(dao.ConstDao.AFTER_LAST_INSERT_VALUE + dao.ConstDao.END_LINE);

        Statement statement = connector.getStatement();
        int row=statement.executeUpdate(sqlQuery.toString(), Statement.RETURN_GENERATED_KEYS);

        int primaryKey;
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            resultSet.next();
            primaryKey = (int) resultSet.getLong(dao.ConstDao.FIRST_COLUMN_INDEX);
        }

        person.setId(primaryKey);
        return person;
    }

    @Override
    public Person get(String id) throws SQLException {
        Person person = new Person();
        Statement statement = connector.getStatement();
        StringBuilder sqlQuery = new StringBuilder(dao.ConstDao.SELECT_FROM + dao.ConstDao.PERSONS_WHERE_ID);
        sqlQuery.append(id + dao.ConstDao.END_LINE);

        try (ResultSet resultSet = statement.executeQuery(sqlQuery.toString())) {

            int columnNumber = dao.ConstDao.FIRST_COLUMN_INDEX;
            while (resultSet.next()) {
                person.setId(Integer.valueOf(resultSet.getString(columnNumber++)));
                person.setSurname(resultSet.getString(columnNumber++));
                person.setName(resultSet.getString(columnNumber++));
                columnNumber = 1;
            }
        }
        return person;
    }

    @Override
    //проверяет наличие записи в базе по id из person, затем вносит остальные данные из полей объекта в колонки выбранной записи
    public Person update(Person person) throws SQLException {
        Person updated = get(String.valueOf(person.getId()));
        if (updated.getId() == 0) {
            return null;
        }
        //System.out.println("Will updated: " + updated);

        StringBuilder sqlQuery = new StringBuilder(dao.ConstDao.UPDATE_PERSONS_SET_SURNAME);
        sqlQuery.append(person.getSurname());
        sqlQuery.append(dao.ConstDao.NAME);
        sqlQuery.append(person.getName());
        sqlQuery.append(dao.ConstDao.AFTER_VALUE + dao.ConstDao.WHERE_ID);
        sqlQuery.append(person.getId());
        sqlQuery.append(dao.ConstDao.END_LINE);

        connector.getStatement().executeUpdate(sqlQuery.toString());
        return get(String.valueOf(person.getId()));
    }

    @Override
    public Person delete(String id) throws SQLException {
        Person deleted = get(id);
        if (deleted.getId() == 0) {
            return null;
        }

        StringBuilder sqlQuery = new StringBuilder(dao.ConstDao.DELETE_FROM_PERSONS_);
        sqlQuery.append(dao.ConstDao.WHERE_ID);
        sqlQuery.append(id);
        sqlQuery.append(dao.ConstDao.END_LINE);

        int count = connector.getStatement().executeUpdate(sqlQuery.toString());

        //System.out.println("Deleted " + count + " person");

        return deleted;
    }
}

package dao;

public interface ConstDao {
	String SELECT_FROM = "SELECT *FROM";
	String END_LINE = ";";
	String PERSON_WHERE_ID = " person WHERE id=";
	String INSERT_QUERY = "INSERT INTO person (surname, name) VALUES ('";
	String AFTER_LAST_INSERT_VALUE = "')";
	String BETWEEN_INSERT_VALUES = "','";
	int FIRST_COLUMN_INDEX = 1;
	String UPDATE_PERSON_SET_SURNAME = "UPDATE person SET surname='";
	String NAME = "', name='";
	String AFTER_VALUE = "' ";
	String DELETE_FROM_PERSON = "DELETE FROM person ";
	String WHERE_ID = "WHERE id =";
}

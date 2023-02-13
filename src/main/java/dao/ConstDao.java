package dao;

public interface ConstDao {
    public static final String SELECT_FROM = "SELECT *FROM";
    public static final String END_LINE = ";";
    public static final String PERSONS_WHERE_ID = " Persons WHERE id=";
    public static final String INSERT_QUERY = "INSERT INTO persons (Surname, Name) VALUES ('";
    public static final String AFTER_LAST_INSERT_VALUE = "')";
    public static final String BETWEEN_INSERT_VALUES = "','";
    public static final int FIRST_COLUMN_INDEX = 1;
    public static final String UPDATE_PERSONS_SET_SURNAME = "UPDATE persons SET Surname='";
    public static final String NAME = "', Name='";
    public static final String AFTER_VALUE = "' ";
    public static final String DELETE_FROM_PERSONS_ = "DELETE FROM persons ";
    public static final String WHERE_ID = "WHERE Id =";
}

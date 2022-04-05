package database;

public interface Database {

    boolean insert(Object object);

    boolean update(Object object);

    boolean delete(Object object);

}

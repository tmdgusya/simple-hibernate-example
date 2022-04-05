package database;

import java.util.ArrayList;
import java.util.List;

/**
 * 딱히 중요하지 않아서 ArrayList 로 구현함.
 */
public class MySQL implements Database {

    private final List<Object> store = new ArrayList<>();

    @Override
    public boolean insert(Object object) {
        return store.add(object);
    }

    @Override
    public boolean update(Object object) {
        if (store.contains(object)) {
            store.remove(object);
            return store.add(object);
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        if (store.contains(object)) {
            return store.remove(object);
        }
        return false;
    }
}

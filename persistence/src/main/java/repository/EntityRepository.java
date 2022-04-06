package repository;

public interface EntityRepository<T, E> {

    boolean save(T id, E entity);

    boolean remove(T id);

    E findById(T id);

}

package repository;

public interface EntityRepository<T, E> {

    boolean save(E entity);

    boolean remove(E entity);

    E findById(T id);

}

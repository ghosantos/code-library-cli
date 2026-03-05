package dao;

import java.util.List;

public interface CrudDao <T> {
    void insert (T t);
    boolean update (T t);
    T findByID(Integer id);
    boolean deleteById(Integer id);
    List<T> findAll();
}

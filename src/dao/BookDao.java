package dao;

import entities.Book;

public interface BookDao extends CrudDao<Book>{

    boolean existsByTitleAndAuthor(String name, String author);
}

package service;

import entities.Book;

import java.util.List;

public interface BookService {

   void save(Book book);
   void update(Book book);
   Book findById(Integer id);
   List<Book> findAll();
   void deleteById(Integer id);
}

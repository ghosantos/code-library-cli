package service.impl;

import dao.BookDao;
import entities.Book;
import service.BookService;

import java.util.List;
import java.util.NoSuchElementException;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save(Book book) {
        if (bookDao.existsByTitleAndAuthor(book.getTitulo(), book.getAutor())){
            throw new  IllegalArgumentException("❌ Não foi possível salvar! Já existe um livro com este Título e Autor na base de dados.");
        }

        bookDao.insert(book);
    }

    @Override
    public void update(Book book) {
        if (!bookDao.update(book)){
            throw new IllegalArgumentException("Livro de ID '" + book.getId() + "' não foi encontrado na base de dados.");
        }
    }

    @Override
    public Book findById(Integer id) {
        Book book = bookDao.findByID(id);

        if (book == null){
            throw new IllegalArgumentException("🤔 Livro de ID '" + id + "' não foi encontrado na base de dados.");
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> list = bookDao.findAll();
        if (list.isEmpty()){
            throw new NoSuchElementException("Nenhum livro foi encontrado na base de dados!");
        }
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        if (!bookDao.deleteById(id)){
            throw new IllegalArgumentException("Nenhum livro encontrado com o ID fornecido.");
        }
    }




}

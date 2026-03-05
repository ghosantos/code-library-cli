package dao.impl;

import dao.BookDao;
import db.DB;
import db.DbException;
import entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJDBC implements BookDao {

    @Override
    public void insert(Book obj) {

        String sql = """
                INSERT INTO book
                (titulo, autor, categoria, preco)
                VALUES
                (?, ?, ?, ?)
                """;

        try (Connection conn = DB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, obj.getTitulo());
            ps.setString(2, obj.getAutor());
            ps.setString(3, obj.getCategoria());
            ps.setDouble(4, obj.getPreco());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                try (ResultSet rs = ps.getGeneratedKeys()){
                    if (rs.next()){
                        int id = rs.getInt(1);
                        obj.setId(id);
                    }
                }
            }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public boolean update(Book obj) {

        String sql = """
                UPDATE book
                SET titulo = ?, autor = ?, categoria = ?, preco = ?
                WHERE id = ?
                """;

        try (Connection conn = DB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, obj.getTitulo());
            ps.setString(2, obj.getAutor());
            ps.setString(3, obj.getCategoria());
            ps.setDouble(4, obj.getPreco());
            ps.setInt(5, obj.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Book findByID(Integer id) {

        String sql = """
                SELECT *
                FROM book
                WHERE id = ?
                """;

        try (Connection conn = DB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Book obj = instantiateBook(rs);
                    obj.setId(rs.getInt(1));
                    return obj;
                }
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }


    public List<Book> findAll(){

        String sql = """
                SELECT *
                FROM book
                """;

        try (Connection conn = DB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            List<Book> listObj = new ArrayList<>();

            while (rs.next()){
                Book book = instantiateBook(rs);
                book.setId(rs.getInt(1));
                listObj.add(book);
            }

            return listObj;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Integer id) {

        String sql = """
                DELETE FROM book
                WHERE id = ?
                """;

        try (Connection conn = DB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }

    }

    private Book instantiateBook(ResultSet rs) throws SQLException {
        return new Book(
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("categoria"),
                rs.getDouble("preco")
        );
    }

    public boolean existsByTitleAndAuthor(String name, String author){

        String sql = """
                SELECT 1
                FROM book
                WHERE titulo = ? AND autor = ?
                """;

        try (Connection conn = DB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, name);
            ps.setString(2, author);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return true;
                }
            }
            return false;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }
}

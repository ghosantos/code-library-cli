package dao;

import dao.impl.BookDaoJDBC;

public class DaoFactory {

    public static BookDao createBookDao(){
        return new BookDaoJDBC();
    }

}

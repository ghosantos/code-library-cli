import cli.MenuCLI;
import dao.BookDao;
import dao.DaoFactory;
import service.BookService;
import service.impl.BookServiceImpl;

public class Main {
    public static void main(String[] args) {

        BookDao bookDao = DaoFactory.createBookDao();
        BookService bookService = new BookServiceImpl(bookDao);

        MenuCLI menuCLI = new MenuCLI(bookService);
        menuCLI.start();
    }
}

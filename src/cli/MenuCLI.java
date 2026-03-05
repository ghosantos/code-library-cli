package cli;

import entities.Book;
import service.BookService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuCLI {

    private final Scanner sc = new Scanner(System.in);
    private final BookService bookService;

    public MenuCLI(BookService bookService) {
        this.bookService = bookService;
    }

    public void start(){
        int option = -1;

        while (option != 0){
            System.out.println("\n=== CodeLibrary CLI ===");
            System.out.print("""
                    1. Adicionar Livro
                    2. Editar Livro
                    3. Buscar Livro pelo ID
                    4. Listar Todos
                    5. Excluir livro
                    6. Relatório por Preço
                    0. Sair
                    """);
            System.out.print("🔢Escolha uma opção: ");
            option = Integer.parseInt(sc.nextLine());

            processOption(option);
        }
    }

    private void processOption(int option){
        switch (option){
            case 1 -> addNewBook();
            case 2 -> updateBook();
            case 3 -> findById();
            case 4 -> findAll();
            case 5 -> deleteById();
            case 6 -> reportByPrice();
            case 0 -> System.out.println("Encerrando o sistema...");
            default -> System.out.println("Opção inválida!");
        }
    }

    public void addNewBook() {
        System.out.print("\nDigite o título do livro: ");
        String title = sc.nextLine();

        System.out.print("Digite o nome do Autor: ");
        String author = sc.nextLine();

        System.out.print("Digite a categoria do livro: ");
        String category = sc.nextLine();

        System.out.print("Digite o preço do livro: ");
        String priceStr = sc.nextLine();
        double price = Double.parseDouble(priceStr);

        Book book = new Book(title, author, category, price);

        try {
            bookService.save(book);
            System.out.println("Livro salvo com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateBook(){
        System.out.print("\nDigite o ID do Livro: ");
        int id = Integer.parseInt(sc.nextLine());

        Book bookInMemory;

        try {
            bookInMemory = bookService.findById(id);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\nTitulo atual: " + bookInMemory.getTitulo());
        System.out.print("Digite o novo título (ou pressione Enter para manter o atual): ");
        String newTitle = sc.nextLine();

        System.out.println("\nAutor atual: " + bookInMemory.getAutor());
        System.out.print("Digite o novo autor (ou pressione Enter para manter o atual): ");
        String newAuthor = sc.nextLine();

        System.out.println("\nCategoria atual: " + bookInMemory.getCategoria());
        System.out.print("Digite o novo categoria (ou pressione Enter para manter o atual): ");
        String newCategory = sc.nextLine();

        System.out.println("\nPreço atual: " + bookInMemory.getPreco());
        System.out.print("Digite o novo preço (ou pressione Enter para manter o atual): ");
        String newPriceStr = sc.nextLine();

        if (!newTitle.trim().isEmpty()) bookInMemory.setTitulo(newTitle);
        if (!newAuthor.trim().isEmpty()) bookInMemory.setAutor(newAuthor);
        if (!newCategory.trim().isEmpty()) bookInMemory.setCategoria(newCategory);
        if (!newPriceStr.trim().isEmpty()){
            double newPrice = Double.parseDouble(newPriceStr);
            bookInMemory.setPreco(newPrice);
        }

        try {
            bookService.update(bookInMemory);
            System.out.println("✅ Livro Atualizado!");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void findById(){
        System.out.print("\nDigite o ID do Livro: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            Book bookInMemory = bookService.findById(id);
            System.out.println("Produto encontrado!" + bookInMemory);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void findAll(){
        System.out.println();
        try {
            List<Book> bookListInMemory = bookService.findAll();
            bookListInMemory.forEach(System.out::println);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteById(){
        System.out.print("\nDigite o ID do Livro: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            bookService.deleteById(id);
            System.out.println("🗑️ Livro deletado com sucesso!");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void reportByPrice(){
        System.out.print("\nQual o valor MÁXIMO que você deseja pagar? R$ ");
        double maxPrice = Double.parseDouble(sc.nextLine());

        System.out.println("\n--- 📚 Livros até R$ " + maxPrice + " ---");

        try {
            List<Book> bookList = bookService.findAll();
            bookList.stream().filter(b -> b.getPreco() <= maxPrice).forEach(System.out::println);
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }
}

package by.andrei.library.services;

import by.andrei.library.dto.BookDTO;
import by.andrei.library.models.Book;
import by.andrei.library.models.Client;
import by.andrei.library.repositories.BookRepository;
import by.andrei.library.repositories.ClientRepository;
import by.andrei.library.util.BookConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private ClientRepository clientRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ClientRepository clientRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(int id) {
        return bookRepository.findById(id).get();

    }

    public void addBook(BookDTO bookDTO) {
        bookRepository.save(BookConverter.convertToBook(bookDTO));
    }

    public void updateBook(BookDTO bookDTO, int id) {
        Book book = BookConverter.convertToBook(bookDTO);
        book.setId(id);
        bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public void releaseBook(Book book) {
        book.setMainClient(null);
        bookRepository.save(book);
    }

    public void assignBook(int id, Client client) {
        Book book = bookRepository.findById(id).get();
        Client client1 = clientRepository.findById(client.getId()).get();
        book.setMainClient(client1);
        bookRepository.save(book);
        client1.getBooks().add(book);
    }

}

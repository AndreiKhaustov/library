package by.andrei.library.util;

import by.andrei.library.dto.BookDTO;
import by.andrei.library.models.Book;

public class BookConverter {
    public static Book convertToBook(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setYear(bookDTO.getYear());
        return book;
    }
}

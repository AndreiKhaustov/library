package by.andrei.library.controllers;

import by.andrei.library.dto.BookDTO;
import by.andrei.library.models.Book;
import by.andrei.library.models.Client;
import by.andrei.library.services.BookService;
import by.andrei.library.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private ClientService clientService;

    @Autowired
    public BookController(BookService bookService, ClientService clientService) {
        this.bookService = bookService;
        this.clientService = clientService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String getBook(Model model, @PathVariable("id") int id, @ModelAttribute("client") Client client) {
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        if (book.getMainClient() != null) {
            model.addAttribute("owner", book.getMainClient());
        } else {
            model.addAttribute("people", clientService.getClients());
        }
        return "books/book";
    }

    @RequestMapping(value = "/{id}/release", method = RequestMethod.PATCH)
    public String releaseBook(@PathVariable("id") int id) {
        Book selectedBook = bookService.getBook(id);
        bookService.releaseBook(selectedBook);
        return "redirect:/books/{id}";
    }

    @GetMapping("/add")
    public String addBook(@ModelAttribute("book") BookDTO bookDTO) {
        return "books/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewBook(@ModelAttribute("book") @Valid BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/add";
        }
        bookService.addBook(bookDTO);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.getBook(id));
        return "books/edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.PATCH)
    public String editBook(@ModelAttribute("book") @Valid BookDTO bookDTO, @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.updateBook(bookDTO, id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id}/assign", method = RequestMethod.PATCH)
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("client") Client client) {
        bookService.assignBook(id, client);
        return "redirect:/books/{id}";
    }
}

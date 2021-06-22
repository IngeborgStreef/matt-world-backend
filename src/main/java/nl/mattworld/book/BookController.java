package nl.mattworld.book;

import nl.mattworld.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return bookService.retrieveBooks();
    }

    @PostMapping("/api/books")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/api/books/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.findBookById(id).orElseThrow(()->new NotFoundException("Book not found by ID: " + id));
    }
}

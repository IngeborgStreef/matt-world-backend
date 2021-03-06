package nl.mattworld.book;

import nl.mattworld.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> retrieveBooks() {
        return repository.findAll();
    }

    public Optional<Book> findBookById(String id) {
        return repository.findOneById(id);
    }

    public Book createBook(Book book) {
        return repository.save(book);
    }

    public List<Book> retrieveBooksByMinimumLevel(int level) {
        return repository.findByLevelIsGreaterThanEqual(level);
    }

    public void updateBook(String id, Book update) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Unable to update. Book not found by ID: " + id);
        }
        update.setId(id);
        repository.save(update);
    }
}

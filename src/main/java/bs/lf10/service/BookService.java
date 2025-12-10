package bs.lf10.service;

import bs.lf10.entity.Book;
import bs.lf10.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        if (book.getAdditionalImages() == null) {
            book.setAdditionalImages(null);
        }
        return bookRepository.save(book);
    }

    public Book saveBook(Book book, MultipartFile coverImage, MultipartFile mainImage, List<MultipartFile> additionalImages) {
        // TODO: Bilder verarbeiten
        return saveBook(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}

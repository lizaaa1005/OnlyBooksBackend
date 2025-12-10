package bs.lf10.controller;

import bs.lf10.entity.Book;
import bs.lf10.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> uploadSingleBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam boolean sale,
            @RequestParam boolean swap,
            @RequestParam String price,
            @RequestParam boolean ageRestriction,
            @RequestParam String language,
            @RequestParam String genre,
            @RequestParam String condition,
            @RequestParam String pages,
            @RequestParam String year,
            @RequestParam String publisher,
            @RequestParam String isbn,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile coverImage,
            @RequestParam(required = false) MultipartFile spineImage,
            @RequestParam(required = false) List<MultipartFile> additionalImages
    ) throws IOException {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setSwap(swap);
        book.setPrice(price);
        book.setLanguage(language);
        book.setGenre(genre);
        book.setCondition(condition);
        book.setPages(pages);
        book.setYear(year);
        book.setPublisher(publisher);
        book.setIsbn(isbn);
        book.setDescription(description);

        Book savedBook = bookService.saveBook(book, coverImage, spineImage, additionalImages);
        return ResponseEntity.ok(savedBook);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
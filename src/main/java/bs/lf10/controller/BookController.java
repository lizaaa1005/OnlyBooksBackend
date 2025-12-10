package bs.lf10.controller;

import bs.lf10.entity.Book;
import bs.lf10.service.BookService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(@RequestParam Book book,
                           @RequestParam(required = false) MultipartFile coverImage,
                           @RequestParam(required = false) MultipartFile mainImage,
                           @RequestParam(required = false) List<MultipartFile> additionalImages) {
        return bookService.saveBook(book, coverImage, mainImage, additionalImages);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
}

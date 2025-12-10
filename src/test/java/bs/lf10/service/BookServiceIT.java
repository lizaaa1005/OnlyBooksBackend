package bs.lf10.service;

import bs.lf10.entity.Book;
import bs.lf10.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // <- nutzt H2-Datenbank
class BookServiceIT {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void saveBook_withCoverImage_shouldSaveAndReturnBook() throws IOException {
        Book book = new Book();
        book.setTitle("Integration Test Book");

        MockMultipartFile cover = new MockMultipartFile(
                "coverImage", "cover.jpg", "image/jpeg", "coverData".getBytes()
        );

        Book savedBook = bookService.saveBook(book, cover, null, null);

        assertNotNull(savedBook.getId());
        assertEquals("Integration Test Book", savedBook.getTitle());
        assertEquals(java.util.Base64.getEncoder().encodeToString("coverData".getBytes()),
                savedBook.getCoverImage());

        Optional<Book> fromDb = bookRepository.findById(savedBook.getId());
        assertTrue(fromDb.isPresent());
        assertEquals(savedBook.getTitle(), fromDb.get().getTitle());
    }

    @Test
    void saveBook_withAllImages_shouldSaveAndReturnBook() throws IOException {
        Book book = new Book();
        book.setTitle("Full Image Book");

        MockMultipartFile cover = new MockMultipartFile("coverImage","cover.jpg","image/jpeg","cover".getBytes());
        MockMultipartFile spine = new MockMultipartFile("spineImage","spine.jpg","image/jpeg","spine".getBytes());
        MockMultipartFile add1 = new MockMultipartFile("additional","add1.jpg","image/jpeg","add1".getBytes());
        MockMultipartFile add2 = new MockMultipartFile("additional","add2.jpg","image/jpeg","add2".getBytes());

        Book savedBook = bookService.saveBook(book, cover, spine, List.of(add1, add2));

        assertNotNull(savedBook.getId());
        assertEquals(2, savedBook.getAdditionalImages().size());
        assertEquals(java.util.Base64.getEncoder().encodeToString("spine".getBytes()),
                savedBook.getSpineImage());
    }

    @Test
    void getAllBooks_shouldReturnAllSavedBooks() {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Book 2");
        bookRepository.save(book2);

        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
    }

    @Test
    void getBookById_shouldReturnBookIfExists() {
        Book book = new Book();
        book.setTitle("Book by ID");
        Book saved = bookRepository.save(book);

        Optional<Book> result = bookService.getBookById(saved.getId());
        assertTrue(result.isPresent());
        assertEquals(saved.getTitle(), result.get().getTitle());
    }

    @Test
    void getBookById_shouldReturnEmptyIfNotExists() {
        Optional<Book> result = bookService.getBookById(999L);
        assertTrue(result.isEmpty());
    }
}

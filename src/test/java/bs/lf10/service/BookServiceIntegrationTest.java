package bs.lf10.service;

import bs.lf10.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BookService.class) // BookService in den Testkontext importieren
public class BookServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Test
    void testSaveAndRetrieveBook() throws IOException {
        // Mock-Dateien erstellen
        MockMultipartFile coverImage = new MockMultipartFile(
                "cover", "cover.jpg", "image/jpeg", "cover image content".getBytes()
        );

        MockMultipartFile spineImage = new MockMultipartFile(
                "spine", "spine.jpg", "image/jpeg", "spine image content".getBytes()
        );

        // Buch erstellen
        Book book = new Book();
        book.setTitle("Integration Test Book");
        book.setAuthor("Test Author");

        // Buch speichern
        Book savedBook = bookService.saveBook(book, coverImage, spineImage, null);

        // Überprüfungen
        assertNotNull(savedBook.getId());
        assertEquals("Integration Test Book", savedBook.getTitle());
        assertNotNull(savedBook.getCoverImage());
        assertNotNull(savedBook.getSpineImage());

        // Buch abrufen über Service
        Optional<Book> retrieved = bookService.getBookById(savedBook.getId());
        assertTrue(retrieved.isPresent());
        assertEquals("Integration Test Book", retrieved.get().getTitle());
    }

    @Test
    void testGetAllBooks() throws IOException {
        // Buch 1 speichern
        Book book1 = new Book();
        book1.setTitle("Book 1");
        bookService.saveBook(book1, null, null, null);

        // Buch 2 speichern
        Book book2 = new Book();
        book2.setTitle("Book 2");
        bookService.saveBook(book2, null, null, null);

        // Alle Bücher abrufen
        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
    }
}

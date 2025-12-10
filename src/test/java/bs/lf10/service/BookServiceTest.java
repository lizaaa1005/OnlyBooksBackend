package bs.lf10.service;

import bs.lf10.entity.Book;
import bs.lf10.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBook_shouldNotFailWhenImagesAreNull() {
        Book book = new Book(1L, "Test", "Author", true, "Description");
        book.setAdditionalImages(null);

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        // Erwartet null für additionalImages
        assertNull(savedBook.getAdditionalImages());
    }
}

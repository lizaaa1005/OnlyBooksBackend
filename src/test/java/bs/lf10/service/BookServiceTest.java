package bs.lf10.service;

import bs.lf10.entity.Book;
import bs.lf10.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        // KORRIGIERT: Verwenden des no-args Konstruktors und Settern
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test");
        book.setAuthor("Author");
        book.setAvailable(true);
        // "Description" wurde wahrscheinlich dem 'description' Feld zugewiesen, 
        // da es nicht in den 5 Argumenten des alten Aufrufs enthalten war.
        book.setDescription("Description"); 
        
        book.setAdditionalImages(null);

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.saveBook(book);

        // Erwartet null für additionalImages
        assertNull(savedBook.getAdditionalImages());
    }
}

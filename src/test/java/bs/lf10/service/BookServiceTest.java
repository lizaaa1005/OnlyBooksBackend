package bs.lf10.service;

import bs.lf10.entity.Book;
import bs.lf10.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setup() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void saveBook_shouldConvertImagesToBase64_andSave() throws IOException {
        Book book = new Book();

        MockMultipartFile cover = new MockMultipartFile(
                "coverImage", "cover.jpg", "image/jpeg", "coverData".getBytes()
        );
        MockMultipartFile spine = new MockMultipartFile(
                "spineImage", "spine.jpg", "image/jpeg", "spineData".getBytes()
        );
        MockMultipartFile additional1 = new MockMultipartFile(
                "additional", "add1.jpg", "image/jpeg", "addData1".getBytes()
        );
        MockMultipartFile additional2 = new MockMultipartFile(
                "additional", "add2.jpg", "image/jpeg", "addData2".getBytes()
        );

        when(bookRepository.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));

        Book savedBook = bookService.saveBook(book, cover, spine, List.of(additional1, additional2));

        assertEquals(
                java.util.Base64.getEncoder().encodeToString("coverData".getBytes()),
                savedBook.getCoverImage()
        );

        assertEquals(
                java.util.Base64.getEncoder().encodeToString("spineData".getBytes()),
                savedBook.getSpineImage()
        );

        List<String> additionalImages = savedBook.getAdditionalImages();
        assertNotNull(additionalImages);
        assertEquals(2, additionalImages.size());
        assertEquals(
                java.util.Base64.getEncoder().enco

package bs.lf10.service;

import bs.lf10.entity.Book;
import bs.lf10.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        // Falls null -> bleibt null (Tests erwarten das)
        if (book.getAdditionalImages() == null) {
            book.setAdditionalImages(null);
        }
        return bookRepository.save(book);
    }

    public Book saveBook(Book book, MultipartFile coverImage, MultipartFile spineImage, List<MultipartFile> additionalImages) {

        try {
            // COVER IMAGE
            if (coverImage != null && !coverImage.isEmpty()) {
                book.setCoverImage(Base64.getEncoder().encodeToString(coverImage.getBytes()));
            }

            // SPINE IMAGE
            if (spineImage != null && !spineImage.isEmpty()) {
                book.setSpineImage(Base64.getEncoder().encodeToString(spineImage.getBytes()));
            }

            // ADDITIONAL IMAGES
            if (additionalImages != null && !additionalImages.isEmpty()) {
                List<String> encoded = additionalImages.stream()
                        .map(file -> {
                            try {
                                return Base64.getEncoder().encodeToString(file.getBytes());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();

                book.setAdditionalImages(encoded);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error processing images", e);
        }

        return saveBook(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}

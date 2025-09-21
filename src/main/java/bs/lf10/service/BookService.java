package bs.lf10.service;

import bs.lf10.entity.Book;
import bs.lf10.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book saveBook(Book book, MultipartFile coverImage, MultipartFile spineImage, List<MultipartFile> additionalImages) throws IOException {

        if (coverImage != null && !coverImage.isEmpty()) {
            book.setCoverImage(Base64.getEncoder().encodeToString(coverImage.getBytes()));
        }

        if (spineImage != null && !spineImage.isEmpty()) {
            book.setSpineImage(Base64.getEncoder().encodeToString(spineImage.getBytes()));
        }

        if (additionalImages != null && !additionalImages.isEmpty()) {
            List<String> additionalBase64 = new ArrayList<>();
            for (MultipartFile file : additionalImages) {
                if (!file.isEmpty()) {
                    additionalBase64.add(Base64.getEncoder().encodeToString(file.getBytes()));
                }
            }
            book.setAdditionalImages(additionalBase64);
        }

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}
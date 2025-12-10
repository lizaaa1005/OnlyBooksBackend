package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor // Behalten wir, da Lombok einen leeren Konstruktor bereitstellt
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private boolean available;

    private boolean swap;
    private String price;
    private String language;
    private String genre;
    private String condition;
    private String pages;
    private String year;
    private String publisher;
    private String isbn;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String coverImage;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String spineImage;

    @Lob
    private String description;

    @ElementCollection
    @CollectionTable(name = "book_additional_images", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "image", columnDefinition = "LONGTEXT")
    private List<String> additionalImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    // Manueller Konstruktor ersetzt @AllArgsConstructor, um EI_EXPOSE_REP2 zu vermeiden
    public Book(Long id, String title, String author, boolean available, boolean swap, String price, String language,
                String genre, String condition, String pages, String year, String publisher, String isbn,
                String coverImage, String spineImage, String description, List<String> additionalImages, User owner) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
        this.swap = swap;
        this.price = price;
        this.language = language;
        this.genre = genre;
        this.condition = condition;
        this.pages = pages;
        this.year = year;
        this.publisher = publisher;
        this.isbn = isbn;
        this.coverImage = coverImage;
        this.spineImage = spineImage;
        this.description = description;
        // Defensive Kopie für Listen
        this.additionalImages = (additionalImages == null) ? null : new ArrayList<>(additionalImages);
        // Defensive Kopie für das User-Objekt (nutzt den User-Kopierkonstruktor von vorhin)
        this.owner = (owner == null) ? null : new User(owner);
    }

    // Defensive copy für additionalImages Getter
    public List<String> getAdditionalImages() {
        return additionalImages == null ? null : new ArrayList<>(additionalImages);
    }

    // Defensive copy für additionalImages Setter
    public void setAdditionalImages(List<String> additionalImages) {
        this.additionalImages = additionalImages == null ? null : new ArrayList<>(additionalImages);
    }

    // Defensive copy für owner Getter
    public User getOwner() {
        return owner == null ? null : new User(owner);
    }

    // Defensive copy für owner Setter
    public void setOwner(User owner) {
        this.owner = owner == null ? null : new User(owner);
    }
}

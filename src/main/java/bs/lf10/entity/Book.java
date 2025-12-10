package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String description;

    @ElementCollection
    @CollectionTable(name = "book_additional_images", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "image", columnDefinition = "LONGTEXT")
    private List<String> additionalImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    // Defensive copy für additionalImages
    public List<String> getAdditionalImages() {
        return additionalImages == null ? null : new ArrayList<>(additionalImages);
    }

    public void setAdditionalImages(List<String> additionalImages) {
        this.additionalImages = additionalImages == null ? null : new ArrayList<>(additionalImages);
    }

    // Defensive copy für owner
    public User getOwner() {
        return owner == null ? null : new User(owner);
    }

    public void setOwner(User owner) {
        this.owner = owner == null ? null : new User(owner);
    }
}

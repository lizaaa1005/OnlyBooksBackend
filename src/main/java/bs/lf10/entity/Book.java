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

    // Optional: Konstruktor ohne additionalImages und owner
    public Book(Long id, String title, String author, boolean available, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = available;
        this.description = description;
        this.additionalImages = null;
        this.owner = null;
    }
}

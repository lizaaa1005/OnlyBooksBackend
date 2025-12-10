package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private boolean swap;
    private String price;
    private String language;
    private String genre;
    @Column(name = "bookCondition")
    private String condition;
    private String pages;
    private String year;
    private String publisher;
    private String isbn;
    @Column(length = 5000)
    private String description;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String coverImage;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String spineImage;

    @ElementCollection
    @CollectionTable(name = "book_additional_images", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "image", columnDefinition = "LONGTEXT")
    private List<String> additionalImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    // ----------------- Getter / Setter -----------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isSwap() { return swap; }
    public void setSwap(boolean swap) { this.swap = swap; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getPages() { return pages; }
    public void setPages(String pages) { this.pages = pages; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public String getSpineImage() { return spineImage; }
    public void setSpineImage(String spineImage) { this.spineImage = spineImage; }

    // Defensive copies für mutable Felder
    public List<String> getAdditionalImages() {
        return additionalImages == null ? null : new ArrayList<>(additionalImages);
    }

    public void setAdditionalImages(List<String> additionalImages) {
        this.additionalImages = additionalImages == null ? null : new ArrayList<>(additionalImages);
    }

    public User getOwner() {
        return owner; // optional: defensive copy falls User mutable ist
    }

    public void setOwner(User owner) {
        this.owner = owner; // optional: defensive copy falls User mutable ist
    }
}

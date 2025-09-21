package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//    private boolean sale;
    private boolean swap;
    private String price;
//    private boolean ageRestriction;
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
//    private String tags;
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
}
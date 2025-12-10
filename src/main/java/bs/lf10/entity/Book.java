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

    // Defensive copy für zusätzliche Bilder
    public List<String> getAdditionalImages() {
        return additionalImages == null ? null : new ArrayList<>(additionalImages);
    }

    public void setAdditionalImages(List<String> additionalImages) {
        this.additionalImages = additionalImages == null ? null : new ArrayList<>(additionalImages);
    }

    // Defensive copy für Owner
    public User getOwner() {
        if (owner == null) return null;
        return new User(owner.getId(), owner.getUsername(), owner.getPassword(), null);
    }

    public void setOwner(User owner) {
        if (owner == null) {
            this.owner = null;
        } else {
            this.owner = new User(owner.getId(), owner.getUsername(), owner.getPassword(), null);
        }
    }
}

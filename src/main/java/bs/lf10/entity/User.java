@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    // Defensive copy für Bücher
    public List<Book> getBooks() {
        return books == null ? null : new ArrayList<>(books);
    }

    public void setBooks(List<Book> books) {
        this.books = books == null ? null : new ArrayList<>(books);
    }
}

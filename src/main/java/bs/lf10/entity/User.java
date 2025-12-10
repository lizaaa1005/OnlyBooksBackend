package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;

    // ----------------- Konstruktor mit defensiven Kopien -----------------
    public User(Long id, String username, String password, List<Book> books) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.books = books == null ? null : new ArrayList<>(books);
    }

    // ----------------- Getter / Setter -----------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Book> getBooks() {
        return books == null ? null : new ArrayList<>(books);
    }

    public void setBooks(List<Book> books) {
        this.books = books == null ? null : new ArrayList<>(books);
    }
}

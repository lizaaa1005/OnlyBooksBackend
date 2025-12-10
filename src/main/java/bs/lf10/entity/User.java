package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    public List<Book> getBooks() {
        return books == null ? null : new ArrayList<>(books);
    }

    public void setBooks(List<Book> books) {
        this.books = books == null ? null : new ArrayList<>(books);
    }

    // Copy constructor für defensive copy
    public User(User user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.books = user.getBooks() == null ? null : new ArrayList<>(user.getBooks());
        }
    }
}

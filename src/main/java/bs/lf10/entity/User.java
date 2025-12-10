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
    private List<Book> books = new ArrayList<>();

    // Copy-Konstruktor für defensive Kopien
    public User(User other) {
        this.id = other.id;
        this.username = other.username;
        this.password = other.password;
        this.books = other.books == null ? null : new ArrayList<>(other.books);
    }
}

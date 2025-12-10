package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    /**
     * Kopierkonstruktor, um einen Benutzer aus einem anderen Benutzerobjekt zu erstellen.
     * Dieser behebt den Kompilierungsfehler in Book.java.
     */
    public User(User originalUser) {
        if (originalUser != null) {
            this.id = originalUser.id;
            this.username = originalUser.username;
            this.email = originalUser.email;
            this.password = originalUser.password;
        }
    }
}

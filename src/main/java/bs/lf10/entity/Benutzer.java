package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Benutzer {
    @Id
    @GeneratedValue
    private UUID id;
    private String email;
    private String username;
    private String password;
    private String role = "USER";
    private String name;
    private LocalDate geburtsdatum;
    private String address;
    private String bankAccount;
    private LocalDateTime createdAt = LocalDateTime.now();
}
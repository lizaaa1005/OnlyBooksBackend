package bs.lf10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Buch {
    @Id
    @GeneratedValue
    private UUID id;
    private String titel;
    private String autor;
    private String sprache;
    private String genre;
    @Enumerated(EnumType.STRING)
    private Zustand zustand;
    private int seitenanzahl;
    private int veroeffentlichungsjahr;
    private String verlag;
    private String isbn;
    private String beschreibung;
    private boolean ab18;
    private Double preis;
    private List<String> tags;
}
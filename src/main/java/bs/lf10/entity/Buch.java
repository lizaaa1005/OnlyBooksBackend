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
public class Buch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titel;
    private String autor;
    private Double preis;
    private String sprache;
    private String genre;
    @Enumerated(EnumType.STRING)
    private Zustand zustand;
    private int seitenanzahl;
    private int veroeffentlichungsjahr;
    private String verlag;
    private String username;
    private String isbn;
    private String beschreibung;
    private boolean zumVerkauf;
    private boolean zumTausch;
    private boolean ab18;
    private List<String> tags;
}
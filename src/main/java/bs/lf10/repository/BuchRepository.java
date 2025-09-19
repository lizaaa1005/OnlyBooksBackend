package bs.lf10.repository;

import bs.lf10.entity.Buch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuchRepository extends JpaRepository<Buch, Long> {
    List<Buch> findByZumVerkaufTrue();

    List<Buch> findByZumTauschTrue();

    List<Buch> findBySprache(String sprache);

//    List<Buch> findByTitelOderAutor(String titel, String autor);
}
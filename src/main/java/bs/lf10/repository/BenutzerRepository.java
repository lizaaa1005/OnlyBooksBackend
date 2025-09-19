package bs.lf10.repository;

import bs.lf10.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
//    Optional<Benutzer> findByEmail(String email);
    Optional<Benutzer> findByUsername(String username);
}
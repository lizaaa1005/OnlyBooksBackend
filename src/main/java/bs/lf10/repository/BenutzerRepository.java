package bs.lf10.repository;

import bs.lf10.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BenutzerRepository extends JpaRepository<Benutzer, UUID> {
    Optional<Benutzer> findByEmail(String email);
    Optional<Benutzer> findByUsername(String username);
}
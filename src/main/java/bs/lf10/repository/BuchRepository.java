package bs.lf10.repository;

import bs.lf10.entity.Buch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuchRepository extends JpaRepository<Buch, UUID> {

}

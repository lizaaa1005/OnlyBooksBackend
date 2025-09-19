package bs.lf10.service;

import bs.lf10.entity.Buch;
import bs.lf10.repository.BuchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuchService {
    private final BuchRepository buchRepository;

    public Buch buchErstellen(Buch buch) {
        return buchRepository.save(buch);
    }

    public List<Buch> alleBuecherAbrufen() {
        return buchRepository.findAll();
    }

    public Optional<Buch> buchNachIdAbrufen(Long id) {
        return buchRepository.findById(id);
    }

    public List<Buch> buecherSuchen(String suchbegriff, String sprache) {
        if (suchbegriff != null && !suchbegriff.isEmpty()) {
//            return buchRepository.findByTitelOderAutor(suchbegriff, suchbegriff);
        } else if (sprache != null && !sprache.isEmpty()) {
            return buchRepository.findBySprache(sprache);
        }
        return buchRepository.findAll();
    }
}
package bs.lf10.service;

import bs.lf10.entity.Benutzer;
import bs.lf10.repository.BenutzerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BenutzerService {
    private final BenutzerRepository benutzerRepository;

    public Benutzer benutzerRegistrieren(Benutzer benutzer) {
        if (benutzerRepository.findByUsername(benutzer.getUsername()).isPresent()) {
            throw new RuntimeException("Benutzername bereits vergeben!");
        }
        return benutzerRepository.save(benutzer);
    }

    public Benutzer benutzerEinloggen(String username, String password) {
        Optional<Benutzer> userOptional = benutzerRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            Benutzer benutzer = userOptional.get();
            if (benutzer.getPassword().equals(password)) {
                return benutzer;
            }
        }
        throw new RuntimeException("Ungültiger Benutzername oder Passwort!");
    }
}
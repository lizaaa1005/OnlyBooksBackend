package bs.lf10.controller;

import bs.lf10.entity.Benutzer;
import bs.lf10.repository.BenutzerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class BenutzerController {

    private final BenutzerRepository benutzerRepository;

    public BenutzerController(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    @PostMapping("/register")
    public Benutzer register(@RequestBody Benutzer benutzer) {
        return benutzerRepository.save(benutzer);
    }
}
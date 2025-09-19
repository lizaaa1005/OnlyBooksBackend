package bs.lf10.controller;

import bs.lf10.entity.Benutzer;
import bs.lf10.service.BenutzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BenutzerController {

    private final BenutzerService benutzerService;

    @PostMapping("/registrieren")
    public ResponseEntity<Benutzer> registrieren(@RequestBody Benutzer benutzer) {
        try {
            Benutzer neuerBenutzer = benutzerService.benutzerRegistrieren(benutzer);
            return new ResponseEntity<>(neuerBenutzer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/einloggen")
    public ResponseEntity<Benutzer> einloggen(@RequestBody Benutzer benutzer) {
        try {
            Benutzer eingeloggterBenutzer = benutzerService.benutzerEinloggen(benutzer.getUsername(), benutzer.getPassword());
            return new ResponseEntity<>(eingeloggterBenutzer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
package bs.lf10.controller;

import bs.lf10.entity.Buch;
import bs.lf10.service.BuchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buch")
@RequiredArgsConstructor
public class BuchController {

    private final BuchService buchService;

//    @Autowired
//    public BuchController(BuchService buchService) {
//        this.buchService = buchService;
//    }

    @PostMapping
    public ResponseEntity<Buch> buchErstellen(@RequestBody Buch buch) {
        Buch neuesBuch = buchService.buchErstellen(buch);
        return new ResponseEntity<>(neuesBuch, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Buch>> alleBuecherAbrufen() {
        List<Buch> buecher = buchService.alleBuecherAbrufen();
        return new ResponseEntity<>(buecher, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buch> buchNachIdAbrufen(@PathVariable Long id) {
        Optional<Buch> buchOptional = buchService.buchNachIdAbrufen(id);
        return buchOptional.map(buch -> new ResponseEntity<>(buch, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/suche")
    public ResponseEntity<List<Buch>> buecherSuchen(@RequestParam(required = false) String suchbegriff,
                                                    @RequestParam(required = false) String sprache) {
        List<Buch> gefundeneBuecher = buchService.buecherSuchen(suchbegriff, sprache);
        return new ResponseEntity<>(gefundeneBuecher, HttpStatus.OK);
    }
}

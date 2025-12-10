package bs.lf10;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// Ein Integrationstest, der nur prüft, ob die Anwendung ohne Fehler startet.
@SpringBootTest
@ActiveProfiles("test")
public class ApplicationContextTestIT {

    @Test
    void contextLoads() {
        // Wenn dieser Test läuft, ist Ihre Spring-Konfiguration in Ordnung.
    }
}

package bs.lf10.service;

import bs.lf10.entity.User;
import bs.lf10.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setup() {
        // Initialisiert die Mocks und den Service vor jedem Test
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    // --- Tests für registerUser ---

    @Test
    void registerUser_shouldSaveNewUser_whenUsernameIsUnique() {
        User newUser = new User();
        newUser.setUsername("testuser");
        newUser.setPassword("password123");

        // Mock-Verhalten: findByUsername gibt Optional.empty() zurück (Benutzer existiert nicht)
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        // Mock-Verhalten: save gibt den übergebenen Benutzer zurück
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User createdUser = userService.registerUser(newUser);

        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        // Überprüft, ob findByUsername geprüft und save genau einmal aufgerufen wurde
        verify(userRepository, times(1)).findByUsername("testuser");
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void registerUser_shouldThrowException_whenUsernameExists() {
        User existingUser = new User();
        existingUser.setUsername("existinguser");

        // Mock-Verhalten: findByUsername gibt ein Optional mit dem existierenden Benutzer zurück
        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(existingUser));

        User userToRegister = new User();
        userToRegister.setUsername("existinguser");

        // Prüft, ob die erwartete RuntimeException ausgelöst wird
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(userToRegister);
        });

        assertEquals("Benutzername bereits vergeben!", thrown.getMessage());
        // Überprüft, ob save() nie aufgerufen wurde
        verify(userRepository, times(1)).findByUsername("existinguser");
        verify(userRepository, never()).save(any(User.class));
    }

    // --- Tests für loginUser ---

    @Test
    void loginUser_shouldReturnUser_whenCredentialsAreValid() {
        String username = "validuser";
        String password = "correctpassword";
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword(password);

        // Mock-Verhalten: findByUsername findet den Benutzer
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        User loggedInUser = userService.loginUser(username, password);

        assertNotNull(loggedInUser);
        assertEquals(username, loggedInUser.getUsername());
        assertEquals(password, loggedInUser.getPassword());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void loginUser_shouldThrowException_whenUserNotFound() {
        String username = "unknownuser";
        String password = "anypassword";

        // Mock-Verhalten: findByUsername gibt Optional.empty() zurück
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Prüft, ob die erwartete RuntimeException ausgelöst wird
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.loginUser(username, password);
        });

        assertEquals("Ungültiger Benutzername oder Passwort!", thrown.getMessage());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void loginUser_shouldThrowException_whenPasswordIsIncorrect() {
        String username = "userwithwrongpass";
        String correctPassword = "correct";
        String incorrectPassword = "wrong";
        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword(correctPassword); // Speichert das korrekte Passwort im Mock-Objekt

        // Mock-Verhalten: findByUsername findet den Benutzer
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // Prüft, ob die erwartete RuntimeException ausgelöst wird
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userService.loginUser(username, incorrectPassword); // Versucht sich mit falschem Passwort einzuloggen
        });

        assertEquals("Ungültiger Benutzername oder Passwort!", thrown.getMessage());
        verify(userRepository, times(1)).findByUsername(username);
    }
}

package org.example.infrastructure.UI.application;

import org.example.infrastructure.UI.application.domain.Player;
import org.example.infrastructure.UI.application.domain.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    private PlayerRepository mockRepository;
    private PlayerService service;

    @BeforeEach
    void setUp() {
        mockRepository = mock(PlayerRepository.class);
        service = new PlayerService(mockRepository);
    }

    @Test
    void testRegister() {
        String username = "testUser";
        String password = "testPass";
        double balance = 100.0;

        when(mockRepository.checkName(username)).thenReturn(false);

        Player player = service.register(username, password, balance);

        assertNotNull(player);
        assertEquals(username, player.getUsername());
        assertEquals(password, player.getPassword());
        assertEquals(balance, player.getBalance(), 0.0001);

        verify(mockRepository).save(any(Player.class));
    }

    @Test
    void testAuthenticateSuccessfully() {
        String username = "testUser";
        String password = "testPass";
        Player mockPlayer = new Player(UUID.randomUUID().toString(), username, password, 100.0);

        when(mockRepository.findByUsername(username)).thenReturn(mockPlayer);

        Player result = service.authenticate(username, password);

        assertNotNull(result);
        assertEquals(mockPlayer, result);
    }

    @Test
    void testAuthenticateFailedWrongPassword() {
        String username = "testUser";
        String correctPassword = "correctPass";
        String wrongPassword = "wrongPass";
        Player mockPlayer = new Player(UUID.randomUUID().toString(), username, correctPassword, 100.0);

        when(mockRepository.findByUsername(username)).thenReturn(mockPlayer);

        Player result = service.authenticate(username, wrongPassword);

        assertNull(result);
    }

    @Test
    void testAuthenticateFailedNoUser() {
        String username = "testUser";
        String password = "testPass";

        when(mockRepository.findByUsername(username)).thenReturn(null);

        Player result = service.authenticate(username, password);

        assertNull(result);
    }

    @Test
    void testCheckBalance() {
        String playerId = UUID.randomUUID().toString();
        double balance = 150.0;

        when(mockRepository.checkBalance(playerId)).thenReturn(balance);

        double result = service.checkBalance(playerId);

        assertEquals(balance, result, 0.0001);
    }

    @Test
    void testCheckName() {
        String username = "testUser";

        when(mockRepository.checkName(username)).thenReturn(true);

        assertTrue(service.checkName(username));

        when(mockRepository.checkName(username)).thenReturn(false);

        assertFalse(service.checkName(username));
    }
}

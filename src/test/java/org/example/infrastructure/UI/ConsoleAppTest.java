package org.example.infrastructure.UI;

import org.example.infrastructure.UI.application.PlayerService;
import org.example.infrastructure.UI.application.TransactionService;
import org.example.infrastructure.UI.application.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ConsoleAppTest {

    private PlayerService mockPlayerService;
    private TransactionService mockTransactionService;
    private ConsoleInputHandler mockInputHandler;
    private ConsoleOutputHandler mockOutputHandler;
    private ConsoleApp app;

    @BeforeEach
    void setUp() {
        mockPlayerService = mock(PlayerService.class);
        mockTransactionService = mock(TransactionService.class);
        mockInputHandler = mock(ConsoleInputHandler.class);
        mockOutputHandler = mock(ConsoleOutputHandler.class);

        app = new ConsoleApp(mockPlayerService, mockTransactionService, mockInputHandler, mockOutputHandler);
    }

    @Test
    void testRegisterPlayer() {
        when(mockInputHandler.readUsername()).thenReturn("testUser");
        when(mockPlayerService.checkName("testUser")).thenReturn(false);
        when(mockInputHandler.readPassword()).thenReturn("testPass");
        when(mockInputHandler.readAmount()).thenReturn(100.0);

        app.run();

        verify(mockPlayerService).register("testUser", "testPass", 100.0);
        verify(mockOutputHandler).showMessage("User successfully registered.");
    }

    @Test
    void testRegisterPlayerWithTakenUsername() {
        when(mockInputHandler.readUsername()).thenReturn("testUser");
        when(mockPlayerService.checkName("testUser")).thenReturn(true);

        app.run();

        verify(mockOutputHandler).showMessage("The username is already taken.");
    }

    @Test
    void testAuthenticatePlayerSuccessfully() {
        when(mockInputHandler.readUsername()).thenReturn("testUser");
        when(mockInputHandler.readPassword()).thenReturn("testPass");
        when(mockPlayerService.authenticate("testUser", "testPass")).thenReturn(new Player("1", "testUser", "testPass", 100.0));

        app.run();

        verify(mockOutputHandler).showMessage("User successfully authenticated!");
    }

    @Test
    void testAuthenticatePlayerFailed() {
        when(mockInputHandler.readUsername()).thenReturn("testUser");
        when(mockInputHandler.readPassword()).thenReturn("wrongPass");
        when(mockPlayerService.authenticate("testUser", "wrongPass")).thenReturn(null);

        app.run();

        verify(mockOutputHandler).showMessage("Authentication failed!");
    }


}

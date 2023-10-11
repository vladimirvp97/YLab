package org.example.infrastructure.UI.application;

import org.example.infrastructure.UI.application.domain.Player;
import org.example.infrastructure.UI.application.domain.PlayerRepository;
import org.example.infrastructure.UI.application.domain.Transaction;
import org.example.infrastructure.UI.application.domain.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    private PlayerRepository mockPlayerRepository;
    private TransactionRepository mockTransactionRepository;
    private TransactionService service;

    @BeforeEach
    void setUp() {
        mockPlayerRepository = mock(PlayerRepository.class);
        mockTransactionRepository = mock(TransactionRepository.class);
        service = new TransactionService(mockPlayerRepository, mockTransactionRepository);
    }

    @Test
    void testDebitSuccessfully() {
        String playerId = UUID.randomUUID().toString();
        double amount = 50.0;
        String transactionId = "testTransaction";

        Player mockPlayer = new Player(playerId, "testUser", "testPass", 100.0);
        when(mockPlayerRepository.findById(playerId)).thenReturn(mockPlayer);
        when(mockTransactionRepository.findByTransactionId(transactionId)).thenReturn(null);

        boolean result = service.debit(playerId, amount, transactionId);

        assertTrue(result);
        assertEquals(50.0, mockPlayer.getBalance(), 0.0001);
    }

    @Test
    void testDebitFailedInsufficientFunds() {
        String playerId = UUID.randomUUID().toString();
        double amount = 150.0;
        String transactionId = "testTransaction";

        Player mockPlayer = new Player(playerId, "testUser", "testPass", 100.0);
        when(mockPlayerRepository.findById(playerId)).thenReturn(mockPlayer);
        when(mockTransactionRepository.findByTransactionId(transactionId)).thenReturn(null);

        boolean result = service.debit(playerId, amount, transactionId);

        assertFalse(result);
    }

    @Test
    void testCreditSuccessfully() {
        String playerId = UUID.randomUUID().toString();
        double amount = 50.0;
        String transactionId = "testTransaction";

        Player mockPlayer = new Player(playerId, "testUser", "testPass", 100.0);
        when(mockPlayerRepository.findById(playerId)).thenReturn(mockPlayer);
        when(mockTransactionRepository.findByTransactionId(transactionId)).thenReturn(null);

        boolean result = service.credit(playerId, amount, transactionId);

        assertTrue(result);
        assertEquals(150.0, mockPlayer.getBalance(), 0.0001);
    }

    @Test
    void testGetTransactionHistory() {
        String playerId = UUID.randomUUID().toString();
        Transaction mockTransaction = new Transaction("testTransaction", playerId, 50.0, Transaction.TransactionType.DEBIT);
        when(mockTransactionRepository.findByPlayerId(playerId)).thenReturn(Collections.singletonList(mockTransaction));

        List<Transaction> transactions = service.getTransactionHistory(playerId);

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(mockTransaction, transactions.get(0));
    }
}

package org.example.infrastructure;

import org.example.infrastructure.UI.application.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTransactionRepositoryTest {
    private InMemoryTransactionRepository repository;
    private Transaction testTransaction;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTransactionRepository();

        testTransaction = new Transaction("1", "player1", 100.0, Transaction.TransactionType.CREDIT);
        repository.save(testTransaction);
    }

    @Test
    void findByTransactionId() {
        Transaction foundTransaction = repository.findByTransactionId("1");
        assertNotNull(foundTransaction);
        assertEquals(testTransaction, foundTransaction);
    }

    @Test
    void findByPlayerId() {
        List<Transaction> foundTransactions = repository.findByPlayerId("player1");
        assertFalse(foundTransactions.isEmpty());
        assertTrue(foundTransactions.contains(testTransaction));
    }

    @Test
    void save() {
        Transaction newTransaction = new Transaction("2", "player2", 50.0, Transaction.TransactionType.DEBIT);
        repository.save(newTransaction);

        Transaction foundTransaction = repository.findByTransactionId("2");
        assertNotNull(foundTransaction);
        assertEquals(newTransaction, foundTransaction);
    }
}

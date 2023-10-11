package org.example.infrastructure;

import org.example.infrastructure.UI.application.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPlayerRepositoryTest {

    private InMemoryPlayerRepository repository;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        repository = new InMemoryPlayerRepository();

        testPlayer = new Player("1", "testUser", "testPass", 100.0);
        repository.save(testPlayer);
    }

    @Test
    void findById_WhenPlayerExists_ReturnsPlayer() {
        Player foundPlayer = repository.findById("1");
        assertEquals(testPlayer, foundPlayer);
    }

    @Test
    void findById_WhenPlayerDoesNotExists_ReturnsNull() {
        Player foundPlayer = repository.findById("999");
        assertNull(foundPlayer);
    }

    @Test
    void findByUsername_WhenUsernameExists_ReturnsPlayer() {
        Player foundPlayer = repository.findByUsername("testUser");
        assertEquals(testPlayer, foundPlayer);
    }

    @Test
    void findByUsername_WhenUsernameDoesNotExists_ReturnsNull() {
        Player foundPlayer = repository.findByUsername("unknownUser");
        assertNull(foundPlayer);
    }

    @Test
    void save_AddsPlayerToRepository() {
        Player newPlayer = new Player("2", "newUser", "newPass", 50.0);
        repository.save(newPlayer);

        Player foundPlayer = repository.findById("2");
        assertEquals(newPlayer, foundPlayer);
    }

    @Test
    void checkBalance_ReturnsCorrectBalance() {
        double balance = repository.checkBalance("1");
        assertEquals(100.0, balance);
    }

    @Test
    void checkName_WhenNameExists_ReturnsTrue() {
        assertTrue(repository.checkName("testUser"));
    }

    @Test
    void checkName_WhenNameDoesNotExists_ReturnsFalse() {
        assertFalse(repository.checkName("unknownUser"));
    }

}

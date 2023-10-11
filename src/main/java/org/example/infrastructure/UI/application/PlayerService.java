package org.example.infrastructure.UI.application;

import lombok.Data;
import org.example.infrastructure.UI.application.domain.Player;
import org.example.infrastructure.UI.application.domain.PlayerRepository;

import java.util.Map;
import java.util.UUID;

/**
 * Сервис для управления игроками.
 * Предоставляет функционал для регистрации, аутентификации и других операций с аккаунтом игрока.
 *
 * @author Vladimir Polyakov
 */
public class PlayerService {

    private final PlayerRepository playerRepository;
    /**
     * Конструктор класса.
     *
     * @param playerRepository Репозиторий игроков.
     */
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    /**
     * Регистрирует нового игрока.
     *
     * @param username Имя пользователя.
     * @param password Пароль пользователя.
     * @param balance  Начальный баланс.
     * @return Созданный объект игрока.
     */
    public Player register(String username, String password, double balance) {
        Player newPlayer = new Player(UUID.randomUUID().toString(), username, password, balance);
        playerRepository.save(newPlayer);

        return newPlayer;
    }
    /**
     * Аутентифицирует игрока.
     *
     * @param username Имя пользователя.
     * @param password Пароль пользователя.
     * @return Аутентифицированный игрок или null, если аутентификация не удалась.
     */
    public Player authenticate(String username, String password) {
        Player player = playerRepository.findByUsername(username);
        if (player != null && player.getPassword().equals(password)) {
            return player;
        }
        return null;
    }
    /**
     * Проверяет баланс игрока.
     *
     * @param playerId ID игрока.
     * @return Баланс игрока.
     */
    public double checkBalance(String playerId){
        return playerRepository.checkBalance(playerId);
    }

    /**
     * Проверяет, существует ли игрок с данным именем.
     *
     * @param playerName Имя игрока.
     * @return true, если игрок с данным именем существует, иначе false.
     */
    public boolean checkName(String playerName) {
        return playerRepository.checkName(playerName);
    }
    /**
     * Возвращает репозиторий игроков.
     *
     * @return Репозиторий игроков.
     */
    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }
}


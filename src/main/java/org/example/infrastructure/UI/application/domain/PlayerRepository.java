package org.example.infrastructure.UI.application.domain;

/**
 * Интерфейс репозитория игроков, предоставляющий методы для работы с данными игрока.
 *
 * @author Vladimir Polyakov
 */
public interface PlayerRepository {
    /**
     * Находит и возвращает игрока по его уникальному идентификатору.
     *
     * @param id Уникальный идентификатор игрока.
     * @return Объект игрока или null, если игрок не найден.
     */
    Player findById(String id);

    /**
     * Находит и возвращает игрока по его имени пользователя.
     *
     * @param username Имя пользователя.
     * @return Объект игрока или null, если игрок не найден.
     */
    Player findByUsername(String username);

    /**
     * Сохраняет информацию об игроке или обновляет существующую информацию.
     *
     * @param player Объект игрока для сохранения.
     */
    void save(Player player);

    /**
     * Проверяет баланс игрока.
     *
     * @param playerId Уникальный идентификатор игрока.
     * @return Баланс игрока.
     */
    double checkBalance(String playerId);

    /**
     * Проверяет, существует ли игрок с заданным именем пользователя.
     *
     * @param playerName Имя пользователя для проверки.
     * @return true, если имя пользователя уже существует, иначе false.
     */
    boolean checkName(String playerName);
}

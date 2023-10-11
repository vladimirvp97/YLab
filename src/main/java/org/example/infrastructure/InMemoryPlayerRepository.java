package org.example.infrastructure;

import org.example.infrastructure.UI.application.domain.Player;
import org.example.infrastructure.UI.application.domain.PlayerRepository;
import java.util.*;

/**
 * Реализация интерфейса PlayerRepository, хранящая данные в памяти.
 * Для хранения игроков используется HashMap.
 *
 * @author Vladimir Polyakov
 */
public class InMemoryPlayerRepository implements PlayerRepository {
    private final Map<String, Player> players = new HashMap<>();

    /**
     * Получает игрока по его ID.
     *
     * @param id Уникальный ID игрока.
     * @return Игрока, если найден, в противном случае null.
     */
    @Override
    public Player findById(String id) {
        return players.get(id);
    }

    /**
     * Получает игрока по его имени пользователя.
     *
     * @param username Имя пользователя.
     * @return Игрока, если найден, в противном случае null.
     */
    @Override
    public Player findByUsername(String username) {
        return players.values().stream()
                .filter(player -> player.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * Сохраняет или обновляет данные игрока в репозитории.
     *
     * @param player Игрок, который должен быть сохранен или обновлен.
     */
    @Override
    public void save(Player player) {
        players.put(player.getId(), player);
    }

    /**
     * Проверяет баланс игрока по его ID.
     *
     * @param playerId Уникальный ID игрока.
     * @return Баланс игрока.
     */
    @Override
    public double checkBalance(String playerId){
        return players.get(playerId).getBalance();
    }

    /**
     * Проверяет, существует ли игрок с заданным именем в репозитории.
     *
     * @param playerName Имя игрока.
     * @return True, если игрок существует, false в противном случае.
     */
    @Override
    public boolean checkName(String playerName) {
        boolean check = false;
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            if(entry.getValue().getUsername().equals(playerName)) check = true;
        }
        return check;
    }
}

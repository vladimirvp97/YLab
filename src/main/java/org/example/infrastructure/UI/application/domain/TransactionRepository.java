package org.example.infrastructure.UI.application.domain;

import java.util.List;

/**
 * Интерфейс репозитория для управления транзакциями.
 * Определяет основные методы для работы с транзакциями в системе.
 *
 * @author Vladimir Polyakov
 */
public interface TransactionRepository {

    /**
     * Находит все транзакции конкретного игрока по его ID.
     *
     * @param playerId ID игрока.
     * @return Список транзакций игрока.
     */
    List<Transaction> findByPlayerId(String playerId);

    /**
     * Находит транзакцию по её уникальному идентификатору.
     *
     * @param transactionId Уникальный идентификатор транзакции.
     * @return Транзакция или null, если транзакция не найдена.
     */
    Transaction findByTransactionId(String transactionId);

    /**
     * Сохраняет транзакцию в репозитории.
     *
     * @param transaction Транзакция для сохранения.
     */
    void save(Transaction transaction);
}

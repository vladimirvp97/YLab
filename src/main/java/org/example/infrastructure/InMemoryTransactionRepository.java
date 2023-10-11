package org.example.infrastructure;

import org.example.infrastructure.UI.application.domain.Transaction;
import org.example.infrastructure.UI.application.domain.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса TransactionRepository, хранящая данные транзакций в памяти.
 * Для хранения транзакций используется ArrayList.
 *
 * @author Vladimir Polyakov
 */
public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Получает транзакцию по ее уникальному ID.
     *
     * @param transactionId Уникальный ID транзакции.
     * @return Транзакцию, если она найдена, в противном случае null.
     */
    @Override
    public Transaction findByTransactionId(String transactionId) {
        return transactions.stream()
                .filter(transaction -> transaction.getTransactionId().equals(transactionId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Получает список всех транзакций, выполненных определенным игроком.
     *
     * @param playerId Уникальный ID игрока.
     * @return Список транзакций данного игрока.
     */
    @Override
    public List<Transaction> findByPlayerId(String playerId) {
        return transactions.stream()
                .filter(transaction -> transaction.getPlayerId().equals(playerId))
                .collect(Collectors.toList());
    }

    /**
     * Сохраняет данные о транзакции в репозитории.
     *
     * @param transaction Транзакция, которая должна быть сохранена.
     */
    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }
}

package org.example.infrastructure.UI.application;

import org.example.infrastructure.UI.application.domain.Player;
import org.example.infrastructure.UI.application.domain.PlayerRepository;
import org.example.infrastructure.UI.application.domain.Transaction;
import org.example.infrastructure.UI.application.domain.TransactionRepository;

import java.util.List;
/**
 * Сервис для управления транзакциями.
 * Позволяет осуществлять кредитные и дебетовые операции с аккаунтом игрока, а также просматривать историю транзакций.
 *
 * @author Vladimir Polyakov
 */
public class TransactionService {
    private final PlayerRepository playerRepository;
    private final TransactionRepository transactionRepository;
    /**
     * Конструктор класса.
     *
     * @param playerRepository       Репозиторий игроков.
     * @param transactionRepository  Репозиторий транзакций.
     */
    public TransactionService(PlayerRepository playerRepository, TransactionRepository transactionRepository) {
        this.playerRepository = playerRepository;
        this.transactionRepository = transactionRepository;
    }
    /**
     * Осуществляет дебетовую операцию с аккаунтом игрока.
     *
     * @param playerId        ID игрока.
     * @param amount          Сумма для списания.
     * @param transactionId   ID транзакции.
     * @return true если операция успешно выполнена, иначе false.
     */
    public boolean debit(String playerId, double amount, String transactionId) {
        if (transactionRepository.findByTransactionId(transactionId) != null) {
            return false; // Transaction ID already exists
        }

        Player player = playerRepository.findById(playerId);
        if (player.getBalance() < amount) {
            return false; // Insufficient funds
        }

        player.setBalance(player.getBalance() - amount);
        playerRepository.save(player);

        Transaction transaction = new Transaction(transactionId, playerId, amount, Transaction.TransactionType.DEBIT);
        transactionRepository.save(transaction);
        return true;
    }
    /**
     * Осуществляет кредитную операцию с аккаунтом игрока.
     *
     * @param playerId        ID игрока.
     * @param amount          Сумма для зачисления.
     * @param transactionId   ID транзакции.
     * @return true если операция успешно выполнена, иначе false.
     */
    public boolean credit(String playerId, double amount, String transactionId) {
        if (transactionRepository.findByTransactionId(transactionId) != null) {
            return false; // Transaction ID already exists
        }

        Player player = playerRepository.findById(playerId);
        player.setBalance(player.getBalance() + amount);
        playerRepository.save(player);

        Transaction transaction = new Transaction(transactionId, playerId, amount, Transaction.TransactionType.CREDIT);
        transactionRepository.save(transaction);
        return true;
    }
    /**
     * Возвращает историю транзакций игрока.
     *
     * @param playerId  ID игрока.
     * @return Список транзакций.
     */
    public List<Transaction> getTransactionHistory(String playerId) {
        return transactionRepository.findByPlayerId(playerId);
    }
}


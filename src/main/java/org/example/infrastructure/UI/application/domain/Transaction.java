package org.example.infrastructure.UI.application.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Класс, представляющий транзакцию в системе.
 * Содержит информацию о типе транзакции, сумме, времени выполнения и связанных с ней аккаунтах.
 *
 * @author Vladimir Polyakov
 */
public class Transaction {
    private final String transactionId;
    private final String playerId;
    private final double amount;
    private final TransactionType type;
    private final Instant timestamp;

    /**
     * Перечисление, представляющее типы транзакций.
     */
    public enum TransactionType {
        DEBIT,
        CREDIT
    }

    /**
     * Конструктор для создания новой транзакции.
     *
     * @param transactionId Уникальный идентификатор транзакции.
     * @param playerId      ID игрока.
     * @param amount        Сумма транзакции.
     * @param type          Тип транзакции.
     */
    public Transaction(String transactionId, String playerId, double amount, TransactionType type) {
        this.transactionId = transactionId;
        this.playerId = playerId;
        this.amount = amount;
        this.type = type;
        this.timestamp = Instant.now();
    }

    /**
     * Предоставляет строковое представление транзакции.
     *
     * @return Строковое представление транзакции.
     */
    @Override
    public String toString() {
        return "transaction id: " + transactionId + "type: " +
                type + "amount: " + amount + "time: " + timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}

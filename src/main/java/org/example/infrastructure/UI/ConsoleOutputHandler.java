package org.example.infrastructure.UI;

import org.example.infrastructure.UI.application.domain.Transaction;
import java.util.List;

/**
 * Обработчик вывода на консоль.
 * Предоставляет методы для отображения меню, сообщений и истории транзакций.
 *
 * @author Vladimir Polyakov
 */
public class ConsoleOutputHandler {

    /**
     * Отображает основное меню приложения в консоли.
     */
    public void showMenu() {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. View Transaction History");
        System.out.println("6. Check balance");
        System.out.println("7. Exit");
    }

    /**
     * Отображает заданное сообщение в консоли.
     *
     * @param message Сообщение для отображения.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Отображает историю транзакций в консоли.
     *
     * @param transactions Список транзакций для отображения.
     */
    public void showTransactionHistory(List<Transaction> transactions) {
        transactions.forEach(transaction -> System.out.println(transaction.toString()));
    }
}

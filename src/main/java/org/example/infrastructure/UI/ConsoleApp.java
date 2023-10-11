package org.example.infrastructure.UI;

import org.example.infrastructure.UI.application.PlayerService;
import org.example.infrastructure.UI.application.TransactionService;
import org.example.infrastructure.UI.application.domain.Player;
import org.example.infrastructure.UI.application.domain.Transaction;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Консольное приложение для управления аккаунтом игрока.
 * Позволяет регистрировать игроков, авторизовывать, осуществлять кредитные и дебетовые операции, просматривать историю транзакций и проверять баланс.
 *
 * @author Vladimir Polyakov
 */
public class ConsoleApp {

    private final PlayerService playerService;
    private final TransactionService transactionService;
    private Player authenticatedPlayer;

    private final ConsoleInputHandler inputHandler;
    private final ConsoleOutputHandler outputHandler;

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Конструктор класса.
     *
     * @param playerService       Сервис для управления игроками.
     * @param transactionService  Сервис для управления транзакциями.
     * @param inputHandler        Обработчик ввода с консоли.
     * @param outputHandler       Обработчик вывода на консоль.
     */
    public ConsoleApp(PlayerService playerService, TransactionService transactionService, ConsoleInputHandler inputHandler, ConsoleOutputHandler outputHandler) {
        this.playerService = playerService;
        this.transactionService = transactionService;
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    /**
     * Запускает консольное приложение.
     */
    public void run() {

        while (true) {
            outputHandler.showMenu();
            String choice = inputHandler.readChoice();

            switch (choice) {
                case "1":
                    registerPlayer();
                    break;
                case "2":
                    authenticatePlayer();
                    break;
                case "3":
                    creditAccount();
                    break;
                case "4":
                    debitAccount();
                    break;
                case "5":
                    showTransactionHistory();
                    break;
                case "6":
                    checkBalance();
                    break;
                case "7":
                    exitApplication();
                    return;
                default:
                    outputHandler.showMessage("Invalid choice. Please select again.");
            }
        }
    }

    private void registerPlayer() {
        String name = inputHandler.readUsername();
        if (playerService.checkName(name)) {
            outputHandler.showMessage("The username is already taken.");
            return;
        }
        String pass = inputHandler.readPassword();
        double amount = inputHandler.readAmount();
        inputHandler.clearBuffer();
        playerService.register(name, pass, amount);
        outputHandler.showMessage("User successfully registered.");
    }

    private void authenticatePlayer() {
        String name = inputHandler.readUsername();
        String pass = inputHandler.readPassword();
        this.authenticatedPlayer = playerService.authenticate(name, pass);
        if (this.authenticatedPlayer != null) {
            outputHandler.showMessage("User successfully authenticated!");
        } else {
            outputHandler.showMessage("Authentication failed!");
        }
    }

    private void creditAccount() {
        if (authenticatedPlayer == null) {
            outputHandler.showMessage("You were not logged in");
            return;
        }
        double amount = inputHandler.readAmount();
        inputHandler.clearBuffer(); // To clear the buffer from potential residuals
        boolean check = transactionService.credit(authenticatedPlayer.getId(), amount, UUID.randomUUID().toString());
        if (check) {
            outputHandler.showMessage("Account credited.");
        } else {
            outputHandler.showMessage("Transaction error, please try again.");
        }
    }

    private void debitAccount() {
        if (authenticatedPlayer == null) {
            outputHandler.showMessage("You were not logged in");
            return;
        }
        double amount = inputHandler.readAmount();
        inputHandler.clearBuffer(); // To clear the buffer
        if (playerService.checkBalance(authenticatedPlayer.getId()) < amount) {
            outputHandler.showMessage("Withdrawal amount exceeds account balance.");
            return;
        }
        boolean check = transactionService.debit(authenticatedPlayer.getId(), amount, UUID.randomUUID().toString());
        if (check) {
            outputHandler.showMessage("Funds withdrawn.");
        } else {
            outputHandler.showMessage("Transaction error, please try again.");
        }
    }

    private void showTransactionHistory() {
        if (authenticatedPlayer == null) {
            outputHandler.showMessage("You were not logged in");
            return;
        }
        List<Transaction> transactions = transactionService.getTransactionHistory(authenticatedPlayer.getId());
        outputHandler.showTransactionHistory(transactions);
    }

    private void exitApplication() {
        outputHandler.showMessage("Goodbye!");
    }

    private void checkBalance() {
        if (authenticatedPlayer == null) {
            outputHandler.showMessage("You were not logged in");
            return;
        }
        double balance = playerService.checkBalance(authenticatedPlayer.getId());
        outputHandler.showMessage("Your balance: " + balance);
    }


}


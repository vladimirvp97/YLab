package org.example;

import org.example.infrastructure.InMemoryPlayerRepository;
import org.example.infrastructure.InMemoryTransactionRepository;
import org.example.infrastructure.UI.ConsoleApp;
import org.example.infrastructure.UI.ConsoleInputHandler;
import org.example.infrastructure.UI.ConsoleOutputHandler;
import org.example.infrastructure.UI.application.PlayerService;
import org.example.infrastructure.UI.application.TransactionService;
/**
 * Главный класс, служащий точкой входа для консольного банковского приложения.
 * <p>
 * В этом классе создаются репозитории, службы и обработчики ввода/вывода. Затем запускается главное приложение.
 * </p>
 */
public class Main {
    /**
     * Главный метод для запуска приложения.
     * <p>
     * Инициализируются внутренние репозитории, сервисы для обработки бизнес-логики,
     * а также обработчики консольного ввода и вывода. После всех инициализаций
     * запускается главное приложение.
     * </p>
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();
        InMemoryTransactionRepository transactionRepository = new InMemoryTransactionRepository();

        PlayerService playerService = new PlayerService(playerRepository);
        TransactionService transactionService = new TransactionService(playerRepository, transactionRepository);

        ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();

        ConsoleApp consoleApp = new ConsoleApp(playerService, transactionService, consoleInputHandler, consoleOutputHandler);

        consoleApp.run();
    }
}

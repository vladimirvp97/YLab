package org.example.infrastructure.UI;

import java.util.Scanner;

/**
 * Обработчик ввода с консоли.
 * Предоставляет методы для чтения выбора пользователя, имени пользователя, пароля и суммы.
 *
 * @author Vladimir Polyakov
 */
public class ConsoleInputHandler {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Читает строку, введенную пользователем в консоли.
     *
     * @return Строку, введенную пользователем.
     */
    public String readChoice() {
        return scanner.nextLine();
    }

    /**
     * Просит пользователя ввести имя и читает его.
     *
     * @return Имя пользователя, введенное в консоль.
     */
    public String readUsername() {
        System.out.println("Enter username:");
        return scanner.nextLine();
    }

    /**
     * Просит пользователя ввести пароль и читает его.
     *
     * @return Пароль, введенный пользователем.
     */
    public String readPassword() {
        System.out.println("Enter your password:");
        return scanner.nextLine();
    }

    /**
     * Просит пользователя ввести сумму и читает ее.
     *
     * @return Сумма, введенная пользователем.
     */
    public double readAmount() {
        System.out.println("Enter the amount:");
        return scanner.nextDouble();
    }

    /**
     * Метод для очистки буфера после использования scanner.nextDouble().
     */
    public void clearBuffer() {
        scanner.nextLine();
    }
}

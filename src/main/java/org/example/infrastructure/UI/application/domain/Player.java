package org.example.infrastructure.UI.application.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс представляет сущность игрока с полями: идентификатор, имя пользователя, пароль и баланс.
 *
 * @author Vladimir Polyakov
 */
public class Player {
    /**
     * Уникальный идентификатор игрока.
     */
    private final String id;

    /**
     * Имя пользователя.
     */
    private String username;

    /**
     * Пароль игрока.
     */
    private String password;

    /**
     * Баланс игрока.
     */
    private double balance;

    /**
     * Конструктор для создания нового объекта игрока.
     *
     * @param id       уникальный идентификатор игрока.
     * @param username имя пользователя.
     * @param password пароль игрока.
     * @param balance  начальный баланс игрока.
     */

    public Player(String id, String username, String password, double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
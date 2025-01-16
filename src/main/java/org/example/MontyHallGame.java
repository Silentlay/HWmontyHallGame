package org.example;

public abstract class MontyHallGame {
    protected final int totalGames;
    protected int winsWhenSwitched;
    protected int winsWhenStayed;

    public MontyHallGame(int totalGames) {
        if (totalGames <= 0) {
            throw new IllegalArgumentException("Общее количество игр должно быть положительным");
        }
        this.totalGames = totalGames;
        this.winsWhenSwitched = 0;
        this.winsWhenStayed = 0;
    }

    // Абстрактный метод симуляции, реализация будет в подклассах
    public abstract void simulate();

    // Сброс статистики
    public void reset() {
        this.winsWhenSwitched = 0;
        this.winsWhenStayed = 0;
    }

    // Геттеры
    public int getWinsWhenSwitched() {
        return winsWhenSwitched;
    }

    public int getWinsWhenStayed() {
        return winsWhenStayed;
    }

    public int getTotalGames() {
        return totalGames;
    }
}

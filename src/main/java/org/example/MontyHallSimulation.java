package org.example;

import java.util.Random;

public class MontyHallSimulation extends MontyHallGame {
    private final Random random;

    public MontyHallSimulation(int totalGames) {
        super(totalGames);
        this.random = new Random();
    }

    @Override
    public void simulate() {
        for (int i = 0; i < totalGames; i++) {
            int carDoor = random.nextInt(3); // Дверь с автомобилем
            int playerChoice = random.nextInt(3); // Выбор игрока

            // Ведущий открывает дверь с козой
            int openedDoor = findGoatDoor(carDoor, playerChoice);

            // Если игрок меняет выбор, он выбирает оставшуюся дверь
            int switchChoice = 3 - playerChoice - openedDoor;

            if (switchChoice == carDoor) {
                winsWhenSwitched++;
            } else if (playerChoice == carDoor) {
                winsWhenStayed++;
            }
        }
    }

    private int findGoatDoor(int carDoor, int playerChoice) {
        for (int i = 0; i < 3; i++) {
            if (i != carDoor && i != playerChoice) {
                return i;
            }
        }
        return -1; // Ошибка
    }
}
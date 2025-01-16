package org.example;

public class Game {
    public static void main (String[] args){
        MontyHallSimulation simulation = new MontyHallSimulation(1000);
        simulation.simulate();

        System.out.println("Победы при смене двери: " + simulation.getWinsWhenSwitched());
        System.out.println("Победы при отказе от смены двери: " + simulation.getWinsWhenStayed());

    }
}

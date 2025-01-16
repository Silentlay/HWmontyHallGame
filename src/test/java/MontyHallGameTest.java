import org.example.MontyHallSimulation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MontyHallGameTest {

    @Test
    @DisplayName("Проверка общей логики симуляции")
    public void testSimulationLogic() {

        // given
        MontyHallSimulation simulation = new MontyHallSimulation(1000);

        // when
        simulation.simulate();

        // then
        assertEquals(1000, simulation.getWinsWhenSwitched() + simulation.getWinsWhenStayed(),
                "Общее количество игр должно совпадать с суммой побед");
    }

    @Test
    @DisplayName("Проверка выигрыша при смене двери")
    public void testSwitchWinProbability() {

        // given
        MontyHallSimulation simulation = new MontyHallSimulation(1000);

        // when
        simulation.simulate();

        // then
        assertTrue(simulation.getWinsWhenSwitched() > simulation.getWinsWhenStayed(),
                "Вероятность выигрыша при смене двери должна быть выше, чем при отказе от смены");
    }

    @Test
    @DisplayName("Проверка неправильных параметров")
    public void testInvalidParameters() {

        // given

        // when
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new MontyHallSimulation(-10));

        // then
        assertEquals("Общее количество игр должно быть положительным", exception.getMessage(),
                "Сообщение об ошибке должно соответствовать ожидаемому");
    }

    @ParameterizedTest
    @DisplayName("Параметризованный тест с разным количеством игр")
    @ValueSource(ints = {1, 100, 500, 1000})
    public void testParameterizedSimulation(int games) {

        // given
        MontyHallSimulation simulation = new MontyHallSimulation(games);

        // when
        simulation.simulate();

        // then
        assertEquals(games, simulation.getWinsWhenSwitched() + simulation.getWinsWhenStayed(),
                "Общее количество игр должно совпадать с суммой побед");
    }

    @Test
    @DisplayName("Проверка минимального количества игр")
    public void testMinimumGames() {

        // given
        MontyHallSimulation simulation = new MontyHallSimulation(1);

        // when
        simulation.simulate();

        // then
        assertEquals(1, simulation.getWinsWhenSwitched() + simulation.getWinsWhenStayed(),
                "При одной игре общее количество игр должно быть равно 1");
    }

    @Test
    @DisplayName("Проверка распределения выигрышей")
    public void testWinDistribution() {

        // given
        MontyHallSimulation simulation = new MontyHallSimulation(10000);

        // when
        simulation.simulate();

        // then
        int switchedWins = simulation.getWinsWhenSwitched();
        int stayedWins = simulation.getWinsWhenStayed();
        double switchedProbability = (double) switchedWins / 10000;
        double stayedProbability = (double) stayedWins / 10000;

        // Проверяю, что распределение приближается к теоретическим значениям
        assertTrue(Math.abs(switchedProbability - 0.666) < 0.05,
                "Вероятность выигрыша при смене двери должна быть около 2/3");
        assertTrue(Math.abs(stayedProbability - 0.333) < 0.05,
                "Вероятность выигрыша при отказе от смены двери должна быть около 1/3");
    }
}

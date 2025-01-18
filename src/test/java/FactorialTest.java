import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {

    @Test
    @DisplayName("Тестирование факториала - эквивалентное разбиение и границы для int.")

    public void factorialTest() throws NegativeNumberException, BigNumberException {
        assertEquals(40320, Factorial.getFactorial(8));
        assertEquals(1, Factorial.getFactorial(0));
        assertEquals(1, Factorial.getFactorial(1));
        assertEquals(2004189184, Factorial.getFactorial(16));
    }

    @Test
    @DisplayName("Тестирование факториала - исключения.")

    public void factorialExcTest() {
        assertThrows(NegativeNumberException.class, () -> {
            Factorial.getFactorial(-4);
        });

        try {
            Factorial.getFactorial(21);
            fail("Исключение не брошено");
        } catch (NegativeNumberException | BigNumberException e) {
        }
    }
}
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {

    @Test
    @DisplayName("Тестирование факториала - эквивалентное разбиение и границы для long.")

    public void factorialTest() throws NegativeNumberException, BigNumberException {
        assertAll(
                () -> assertEquals(40320L, Factorial.getFactorial(8)),
                () -> assertEquals(1L, Factorial.getFactorial(0)),
                () -> assertEquals(1L, Factorial.getFactorial(1)),
                () -> assertEquals(2432902008176640000L, Factorial.getFactorial(20))
        );
    }

    @Test
    @DisplayName("Тестирование факториала - исключения отрицательных чисел.")

    public void factorialExcNegTest() {
        assertThrows(NegativeNumberException.class, () -> {
            Factorial.getFactorial(-4);
        });
    }

    @Test
    @DisplayName("Тестирование факториала - исключения больших чисел.")

    public void factorialExcBigTest() throws BigNumberException, NegativeNumberException {
        try {
            Factorial.getFactorial(26);
            fail("Исключение не брошено");
        } catch (BigNumberException e) {
        }
    }
}
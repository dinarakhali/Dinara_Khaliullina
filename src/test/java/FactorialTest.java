import org.testng.annotations.Test;
import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class FactorialTest {
    @Test(description = "Тестирование факториала - эквивалентное разбиение и границы для int.")
    public void factorialTest() throws NegativeNumberException, BigNumberException {
        assertEquals(Factorial.getFactorial(8), 40320);
        assertEquals(Factorial.getFactorial(0), 1);
        assertEquals(Factorial.getFactorial(1), 1);
        assertEquals(Factorial.getFactorial(16), 2004189184);
    }

    @Test(description = "Тестирование факториала - исключения.", expectedExceptions = {NegativeNumberException.class, BigNumberException.class})
    public void factorialExcTest() throws NegativeNumberException, BigNumberException {
        Factorial.getFactorial(-1);
        Factorial.getFactorial(17);
    }
}
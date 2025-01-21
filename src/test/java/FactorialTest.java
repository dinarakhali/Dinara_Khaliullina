import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigInteger;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class FactorialTest {
    @Test(description = "Тестирование факториала - эквивалентное разбиение и границы для BigInteger.")
    public void factorialTest() throws NegativeNumberException {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(Factorial.getFactorial(BigInteger.valueOf(8)), BigInteger.valueOf(40320), "Ошибка в факториале 8.");
        softAssert.assertEquals(Factorial.getFactorial(BigInteger.ZERO), BigInteger.ONE, "Ошибка в факториале 0.");
        softAssert.assertEquals(Factorial.getFactorial(BigInteger.ONE), BigInteger.ONE, "Ошибка в факториале 1.");
        softAssert.assertEquals(Factorial.getFactorial(BigInteger.valueOf(16)), new BigInteger("20922789888000"), "Ошибка в факториале 16.");

        softAssert.assertAll();
    }

    @Test(description = "Тестирование факториала - исключения негативных чисел.", expectedExceptions = {NegativeNumberException.class})
    public void factorialExcTest() throws NegativeNumberException {
        Factorial.getFactorial(BigInteger.valueOf(-1));
    }
}
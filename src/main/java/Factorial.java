import java.math.BigInteger;

public class Factorial {
    public static BigInteger getFactorial(BigInteger f) throws NegativeNumberException {
        if (f.compareTo(BigInteger.ZERO) < 0) {
            throw new NegativeNumberException("Число не может быть отрицательным.");
        } else if (f.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.ONE;
        } else {
            return f.multiply(getFactorial(f.subtract(BigInteger.ONE)));
        }
    }
}
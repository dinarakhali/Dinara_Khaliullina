public class Factorial {
    public static int getFactorial(int f) throws NegativeNumberException, BigNumberException {
        if (f < 0) {
            throw new NegativeNumberException("Число не может быть отрицательным.");
        } else if (f <= 1) {
            return 1;
        } else if (f > 16) {
            throw new BigNumberException("Для чисел больше 16 напишите метод с типом BigInteger.");
        } else {
            return f * getFactorial(f - 1);
        }
    }
}
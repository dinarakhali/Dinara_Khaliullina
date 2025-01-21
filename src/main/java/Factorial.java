public class Factorial {
    public static long getFactorial(long f) throws NegativeNumberException, BigNumberException {
        if (f < 0) {
            throw new NegativeNumberException("Число не может быть отрицательным.");
        } else if (f <= 1) {
            return 1;
        } else if (f > 20) {
            throw new BigNumberException("Для чисел больше 20 напишите метод с типом BigInteger.");
        } else {
            return f * getFactorial(f - 1);
        }
    }

    public static void main(String[] args) throws BigNumberException, NegativeNumberException {
        System.out.println(getFactorial(20));
    }
}
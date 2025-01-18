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

    /* public static void main(String[] args) {
        try {
            int result = getFactorial(-5);
            System.out.println("Факториал = " + result);
        } catch (NegativeNumberException | BigNumberException e) {
            System.out.println(e.getMessage());
        }

        try {
            int result = getFactorial(17);
            System.out.println("Факториал = " + result);
        } catch (NegativeNumberException |
                 BigNumberException e) {
            System.out.println(e.getMessage());
        }

        try {
            int result = getFactorial(8);
            System.out.println("Факториал = " + result);
        } catch (NegativeNumberException |
                 BigNumberException e) {
            System.out.println(e.getMessage());
        }
    }
    */
}
public class Main {
    public static void main(String[] args) {
        String[][] array = {
                {"1", "10", "Елка", ":)"},
                {"Снег", "20", "3", "2"},
                {"2", "2", "2", "2"},
                {"1", "1", "1", "1"}};
        String[][] wrongSizeArray = {
                {"1", "10", "3"},
                {"20", "3", "2"}};
        String[][] rightArray = {
                {"2", "5", "3", "1"},
                {"2", "5", "3", "1"},
                {"2", "5", "3", "1"},
                {"2", "5", "3", "1"}
        };

        try {
            int summ = transformArray(array);
            System.out.println("Сумма элементов массива = " + summ + "\n");
        } catch (MyArraySizeException e) {
            System.err.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println(e.getMessage());
        }

        try {
            int summ = transformArray(wrongSizeArray);
            System.out.println("Сумма элементов массива = " + summ + "\n");
        } catch (MyArraySizeException e) {
            System.err.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println(e.getMessage());
        }

        try {
            int summ = transformArray(rightArray);
            System.out.println("Сумма элементов массива = " + summ + "\n");
        } catch (MyArraySizeException e) {
            System.err.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int transformArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4) {
            throw new MyArraySizeException("Ошибка: Массив должен быть размером 4x4.");
        }
        for (String[] row : array) {
            if (row.length != 4) {
                throw new MyArraySizeException("Ошибка: Массив должен быть размером 4x4.");
            }
        }

        int summ = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    summ += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка: Некорректные данные в ячейке [" + i + "][" + j + "]");
                }
            }
        }
        return summ;
    }
}
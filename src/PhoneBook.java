import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<Integer>> phoneBook = new HashMap<>();

    public void add(String surname, int phoneNumber) {

        if (!phoneBook.containsKey(surname)) {
            phoneBook.put(surname, new ArrayList<>());
        }
        phoneBook.get(surname).add(phoneNumber);
    }

    public void get(String surname) {
        List<Integer> numbers = phoneBook.get(surname);

        if (numbers != null && !numbers.isEmpty()) {
            System.out.println(surname + ": " + numbers);
        } else {
            System.out.println(surname + " : номера не найдены.");
        }
    }
}
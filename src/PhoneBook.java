import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class PhoneBook {
    private Map<String, Set<Integer>> phoneBook = new HashMap<>();
    private Map<Integer, String> uniqueCheck;

    public PhoneBook() {
        this.uniqueCheck = new HashMap<>();
    }

    public void add(String surname, Integer phoneNumber) {

        if (uniqueCheck.containsKey(phoneNumber)) {
            System.out.println("Номер " + phoneNumber + " уже принадлежит " + uniqueCheck.get(phoneNumber));
            return;
        }
        phoneBook.putIfAbsent(surname, new HashSet<>());
        phoneBook.get(surname).add(phoneNumber);
        uniqueCheck.put(phoneNumber, surname);
    }

    public void get(String surname) {
        Set<Integer> numbers = phoneBook.get(surname);

        if (numbers != null && !numbers.isEmpty()) {
            System.out.println(surname + ": " + numbers);
        } else {
            System.out.println(surname + " : номера не найдены.");
        }
    }
}
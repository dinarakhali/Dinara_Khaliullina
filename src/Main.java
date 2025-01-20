import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("cherry");
        words.add("watermelon");
        words.add("blackberry");
        words.add("kiwi");
        words.add("pineapple");
        words.add("peach");
        words.add("cherry");
        words.add("watermelon");
        words.add("peach");
        words.add("cherry");
        words.add("kiwi");
        words.add("banana");
        words.add("cherry");
        words.add("apple");
        words.add("watermelon");
        words.add("peach");

        HashSet<String> uniqueWords = new HashSet<>(words);
        System.out.println("Уникальные слова: " + uniqueWords + "\n");

        Map<String, Integer> wordsRepeat = new HashMap<>();
        for (String word : words) {
            wordsRepeat.put(word, wordsRepeat.getOrDefault(word, 0) + 1);
        }

        System.out.println("Количество повторений слов: " + wordsRepeat + "\n");

        // 2 задание
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", 555224);
        phoneBook.add("Petrov", 555225);
        phoneBook.add("Sidorov", 555226);
        phoneBook.add("Ivanov", 555227);
        phoneBook.add("Ivanov", 555228);
        phoneBook.add("Sidorov", 555229);
        phoneBook.add("Ivanov", 555230);
        phoneBook.add("Sidorov", 555224);

        phoneBook.get("Petrov");
        phoneBook.get("Ivanov");
        phoneBook.get("Sidorov");
        phoneBook.get("Pushkin");
    }
}
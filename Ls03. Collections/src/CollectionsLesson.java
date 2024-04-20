import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class CollectionsLesson {
    public static void main(String[] args) {
        doTask1();
        doTask2();
    }

    static void doTask1() {
        String[] words = {"Word1", "Word", "Word1", "Word2", "Word3", "Word4", "Word5", "Word", "Word9", "Word7",
                "Word8", "Word3", "Word6", "Word1", "Word5", "Word7", "Word", "Word5"};

        printUnicWords(words);
        countUnicWords(words);
    }

    static void doTask2() {
        PhoneBook pb = new PhoneBook();
        pb.add("Ivanov", "+7916000001");
        pb.add("Petrov", "+7916000002");
        pb.add("Sidorov", "+7916000003");
        pb.add("Ivanov", "+7916000004");
        pb.add("Putin", "+7916000005");
        pb.add("Petrov", "+7916000006");

        System.out.println(pb.get("Petrov").toString());
        System.out.println(pb.get("Putin").toString());
        System.out.println(pb.get("Ivanov").toString());
    }
    static void countUnicWords(String[] array) {
        Map<String, Integer> unicWords = new HashMap<>();

        for (String word : array) {
            if (unicWords.containsKey(word)) {
                unicWords.put(word, unicWords.get(word) + 1);
            } else {
                unicWords.put(word, 1);
            }
        }
        System.out.println(unicWords);
    }

    static void printUnicWords(String[] array) {
        Set<String> uniqueWords = new HashSet<>();
        Collections.addAll(uniqueWords, array);
        System.out.println(uniqueWords);
    }
}

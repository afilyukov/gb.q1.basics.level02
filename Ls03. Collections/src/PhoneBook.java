import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {
    private Map<String, Set<String>> records;

    public PhoneBook() {
        this.records = new HashMap<>();
    }

    public void add(String surname, String number) {
        if (records.containsKey(surname)) {
            records.get(surname).add(number);
        } else {
            Set<String> numbers = new HashSet<>();
            numbers.add(number);
            records.put(surname, numbers);
        }
    }

    public Set<String> get(String surname) {
        if (records.containsKey(surname)) {
            return records.get(surname);
        }
        return new HashSet<>();
    }
}
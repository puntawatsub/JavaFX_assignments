package task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortFilter {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("A", 90, "Helsinki"));
        personList.add(new Person("B", 10, "Vantaa"));
        personList.add(new Person("C", 50, "Espoo"));
        personList.add(new Person("D", 20, "Helsinki"));

        personList.sort(Comparator.comparingInt(Person::getAge));
        personList.removeIf(person -> !person.getCity().equals("Helsinki"));
        System.out.println(personList);
    }
}

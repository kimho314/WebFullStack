package methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MethodRef4 {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Kim"),
                new Person("Park"),
                new Person("Lee")
        );

        List<String> result1 = mapPersonToString(persons, (Person p) -> p.introduce());
        System.out.println(result1);

        List<String> result2 = mapPersonToString(persons, Person::introduce);
        System.out.println(result2);

        List<String> upperResult1 = mapStringToString(result1, (String s) -> s.toUpperCase());
        System.out.println(upperResult1);
        List<String> upperResult2 = mapStringToString(result1, String::toUpperCase);
        System.out.println(upperResult2);
    }

    static List<String> mapPersonToString(List<Person> persons, Function<Person, String> func) {
        List<String> result = new ArrayList<>();
        for (Person p : persons) {
            String applied = func.apply(p);
            result.add(applied);
        }
        return result;
    }

    static List<String> mapStringToString(List<String> strings, Function<String, String> func) {
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            String applied = func.apply(s);
            result.add(applied);
        }
        return result;
    }
}

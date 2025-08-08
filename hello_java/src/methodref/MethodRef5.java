package methodref;

import lambda.lambda5.mystream.MyStreamV3;

import java.util.List;

public class MethodRef5 {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Kim"),
                new Person("Park"),
                new Person("Lee")
        );

        List<String> result1 = MyStreamV3.of(persons)
                .map((Person p) -> p.introduce())
                .map(s -> s.toUpperCase())
                .toList();
        System.out.println(result1);

        List<String> result2 = MyStreamV3.of(persons)
                .map(Person::introduce)
                .map(String::toUpperCase)
                .toList();
        System.out.println(result2);
    }
}

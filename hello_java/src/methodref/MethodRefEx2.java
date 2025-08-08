package methodref;

import java.util.function.Function;

public class MethodRefEx2 {
    public static void main(String[] args) {
        Function<String, String> staticMethod1 = name -> Person.greetign(name);
        Function<String, String> staticMethod2 = Person::greetign;
        System.out.println(staticMethod2.apply("Kim"));

        Person person = new Person("Kim");
        Function<Integer, String> instanceMethod = person::introduceWithNumber;
        System.out.println(instanceMethod.apply(10));

        Function<String, Person> newPerson = Person::new;
        System.out.println(newPerson.apply("Kim"));
    }
}

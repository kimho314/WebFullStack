package methodref;

import java.util.function.Supplier;

public class MethodRefEx1 {
    public static void main(String[] args) {
        Supplier<String> staticMethod = () -> Person.greeting();
        Supplier<String> staticMethod2 = Person::greeting;
        System.out.println(staticMethod.get());
        System.out.println(staticMethod2.get());


        Person person = new Person("Kim");
        Supplier<String> instanceMethod1 = () -> person.introduce();
        Supplier<String> instanceMethod2 = person::introduce;
        System.out.println("instanceMethod1: " + instanceMethod1.get());
        System.out.println("instanceMethod2: " + instanceMethod2.get());

        Supplier<Person> newPerson = Person::new;
        System.out.println("newPerson: " + newPerson.get());
        

    }
}

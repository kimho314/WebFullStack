package methodref;

import java.util.function.Function;

public class MethodRefEx3 {
    public static void main(String[] args) {
        Person person1 = new Person("Kim");
        Person person2 = new Person("Park");
        Person person3 = new Person("Lee");

        Function<Person, String> func1 = (Person p) -> p.introduce();
        System.out.println("person1.introduce = " + func1.apply(person1));
        System.out.println("person2.introduce = " + func1.apply(person2));
        System.out.println("person3.introduce = " + func1.apply(person3));

        Function<Person, String> func2 = Person::introduce;
        System.out.println("person1.introduce = " + func2.apply(person1));
        System.out.println("person2.introduce = " + func2.apply(person2));
        System.out.println("person3.introduce = " + func2.apply(person3));
    }
}

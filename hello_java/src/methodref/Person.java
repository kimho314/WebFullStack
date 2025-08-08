package methodref;

public class Person {
    private String name;

    public Person() {
        this("Unknown");
    }

    public Person(String name) {
        this.name = name;
    }

    public static String greeting() {
        return "Hello";
    }

    public static String greetign(String name) {
        return "Hello " + name;
    }

    public String getName() {
        return this.name;
    }

    public String introduce() {
        return "I am " + this.name;
    }

    public String introduceWithNumber(int number) {
        return "I am " + this.name + ", my number is " + number;
    }
}

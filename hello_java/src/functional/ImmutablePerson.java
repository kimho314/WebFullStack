package functional;

public record ImmutablePerson(String name, int age) {
    public ImmutablePerson wihtAge(int newAge) {
        return new ImmutablePerson(name, newAge);
    }
}

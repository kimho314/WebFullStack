package functional;

import java.util.List;

public class ImmutableMain3 {
    public static void main(String[] args) {
        ImmutablePerson m1 = new ImmutablePerson("Kim", 10);
        ImmutablePerson m2 = new ImmutablePerson("Lee", 20);
        List<ImmutablePerson> originList = List.of(m1, m2);
        System.out.println("originList = " + originList);

        List<ImmutablePerson> resultList = originList.stream()
                .map(p -> p.wihtAge(p.age() + 10))
                .toList();
        
        System.out.println("=== After ===");
        System.out.println("resultList = " + resultList);
        System.out.println("originList = " + originList);

    }
}

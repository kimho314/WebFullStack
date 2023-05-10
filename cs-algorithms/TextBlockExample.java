public class TextBlockExample {
    public static void main(String[] args) {
        String type1 = """
                    hello #1 #2
                """.replace("#1", "world").replace("#2", "!!");  //replace메소드
        System.out.println(type1);

        String type2 = """
                    hello %s %s
                """.formatted("world", "!!");  //formatted메소드
        System.out.println(type2);
    }
}

public class ArrayListExample {
    public static void main(String[] args) {
        Integer[][][] dataList = {
                {
                        {1, 2, 3},
                        {4, 5, 6}
                },
                {
                        {7, 8, 9},
                        {10, 11, 12}
                }
        };
        System.out.println(dataList[1][0][0]);
        System.out.println(dataList[1][0][1]);
        System.out.println(dataList[1][0][2]);
    }
}

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17680">...</a>
 */
public class Cache {
    private static final Integer CACHE_HIT = 1;
    private static final Integer CACHE_MISS = 5;

    public static void main(String[] args) {
        System.out.println(new Cache().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(new Cache().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
        System.out.println(new Cache().solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "NewYork"}));
        System.out.println(new Cache().solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(new Cache().solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}));
        System.out.println(new Cache().solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
        System.out.println(new Cache().solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}));
        System.out.println(new Cache().solution(3, new String[]{"1", "2", "1", "1", "1", "1"}));
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if (cacheSize == 0) {
            return cities.length * CACHE_MISS;
        }
        
        Queue<String> cacheStack = new LinkedList<>();
        for (String city : cities) {
            String lowerCase = city.toLowerCase();
            if (!cacheStack.contains(lowerCase)) {
                answer += CACHE_MISS;
                if (cacheStack.size() >= cacheSize) {
                    cacheStack.poll();
                }
                cacheStack.offer(lowerCase);
            }
            else {
                answer += CACHE_HIT;
                cacheStack.remove(lowerCase);
                cacheStack.offer(lowerCase);
            }
        }
        return answer;
    }
}

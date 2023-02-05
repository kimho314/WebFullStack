import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class ComparableExample {
    public static class Edge implements java.lang.Comparable<Edge> {
        private String vertex;
        private Integer distance;

        public Edge(String vertex, Integer distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public String getVertex() {
            return vertex;
        }

        public Integer getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.getDistance(); // ascending
        }
    }


    public static void main(String[] args) {
        var edge1 = new Edge("A", 10);
        var edge2 = new Edge("A", 13);
        var edge3 = new Edge("A", 12);
        var edge4 = new Edge("A", 11);

        var edges = Arrays.asList(edge1, edge2, edge3, edge4);
        System.out.println(
                edges.stream()
                        .map(Edge::getDistance)
                        .map(Object::toString)
                        .collect(Collectors.joining(","))
        );
        Collections.sort(edges);
        System.out.println(
                edges.stream()
                        .map(Edge::getDistance)
                        .map(Objects::toString)
                        .collect(Collectors.joining(","))
        );
    }
}

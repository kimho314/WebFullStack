import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Dijkstra {
    public static class Edge implements java.lang.Comparable<Edge> {
        private Integer distance;
        private String vertex;

        public Edge(Integer distance, String vertex) {
            this.distance = distance;
            this.vertex = vertex;
        }

        public Integer getDistance() {
            return distance;
        }

        public String getVertex() {
            return vertex;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public void setVertex(String vertex) {
            this.vertex = vertex;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.getDistance();
        }
    }

    public static void main(String[] args) {
        HashMap<String, ArrayList<Edge>> graph = new HashMap<>();
        graph.put("A", new ArrayList<>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
        graph.put("B", new ArrayList<>());
        graph.put("C", new ArrayList<>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<>(List.of(new Edge(1, "F"))));
        graph.put("F", new ArrayList<>(List.of(new Edge(5, "A"))));
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public static class Edge implements java.lang.Comparable<Edge> {
        public Integer distance;
        public String vertex;

        public Edge(Integer distance, String vertex) {
            this.distance = distance;
            this.vertex = vertex;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }

    public static HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String start) {
        HashMap<String, Integer> distances = new HashMap<>();
        graph.keySet().forEach(s -> distances.put(s, Integer.MAX_VALUE));

        distances.put(start, 0);

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(distances.get(start), start));

        while (priorityQueue.size() > 0) {
            var edgeNode = priorityQueue.poll();
            var currentDistance = edgeNode.distance;
            var currentVertex = edgeNode.vertex;

            if (distances.get(currentVertex) < currentDistance) {
                continue;
            }

            var nodeList = graph.get(currentVertex);
            for (Edge adjacentNode : nodeList) {
                var adjacent = adjacentNode.vertex;
                var weight = adjacentNode.distance;
                var distance = currentDistance + weight;

                if (distance < distances.get(adjacent)) {
                    distances.put(adjacent, distance);
                    priorityQueue.add(new Edge(distance, adjacent));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        HashMap<String, ArrayList<Edge>> graph = new HashMap<>();
        graph.put("A", new ArrayList<>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"),
                new Edge(2, "D"))));
        graph.put("B", new ArrayList<>());
        graph.put("C", new ArrayList<>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<>(List.of(new Edge(1, "F"))));
        graph.put("F", new ArrayList<>(List.of(new Edge(5, "A"))));

        var distances = dijkstraFunc(graph, "A");
        System.out.println(distances);
        System.out.println("shortest distance to B : " + distances.get("B"));
    }
}

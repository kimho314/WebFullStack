import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BfsDfsSearch {
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> graph = new HashMap<>();
        graph.put("A", new ArrayList<>(Arrays.asList("B", "C")));
        graph.put("B", new ArrayList<>());
        graph.put("C", new ArrayList<>(Arrays.asList("D", "F")));
        graph.put("D", new ArrayList<>(List.of("E")));
        graph.put("F", new ArrayList<>());
        graph.put("E", new ArrayList<>());

        var result = String.join(" ", bfsFunc(graph, "A"));
        System.out.printf(result);
        System.out.println();
        result = String.join(" ", dfsFunc(graph, "A"));
        System.out.println(result);
    }

    public static ArrayList<String> bfsFunc(HashMap<String, ArrayList<String>> graph, String startNode) {
        ArrayList<String> visited = new ArrayList<>(); // queue
        ArrayList<String> needVisit = new ArrayList<>(); // queue

        needVisit.add(startNode);

        while (needVisit.size() > 0) {
            String node = needVisit.remove(0);
            if (!visited.contains(node)) {
                visited.add(node);
                needVisit.addAll(graph.get(node));
            }
        }

        return visited;
    }

    public static ArrayList<String> dfsFunc(HashMap<String, ArrayList<String>> graph, String startNode) {
        ArrayList<String> visited = new ArrayList<>(); // queue
        ArrayList<String> needVisit = new ArrayList<>(); // stack

        needVisit.add(startNode);

        while (needVisit.size() > 0) {
            String node = needVisit.remove(needVisit.size() - 1);
            if (!visited.contains(node)) {
                visited.add(node);
                needVisit.addAll(graph.get(node));
            }
        }

        return visited;
    }
}

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Search {
    public static void BFS(Board root) {
        Node rootNode = new Node(root);

        Queue<Node> nodesToVisit = new PriorityQueue<>();
        Set<Node> nodesVisited = new HashSet<>();


        ArrayList<Node> childNodes;

        System.out.println("Search Started On:");
        System.out.println(root);
        System.out.println("------------------------");

        nodesToVisit.add(rootNode);

        while (!nodesToVisit.isEmpty()) {
            Node current = nodesToVisit.remove();
            nodesVisited.add(current);

            //solution found
            if (current.getRedPos().equals(new Point(6,3))) {
                solutionFoundOutput(current, nodesVisited.size());
                break;

            } else {
                childNodes = current.getChildren();
                for(Node n : nodesVisited) {
                    childNodes.removeIf(m -> m.isEqual(n));
                }
                for(Node n : nodesToVisit) {
                    childNodes.removeIf(m -> m.isEqual(n));
                }
                nodesToVisit.addAll(childNodes);
            }
        }
        if(nodesToVisit.isEmpty()){
            System.out.println("Solution not found");
        }
    }

    /**
     * Used for the output of any search
     * @param solution The solution node from the tree
     */
    private static void solutionFoundOutput(Node solution, int nodesVisited){
        System.out.println("SEARCH COMPLETE");
        System.out.println("Path taken: ");

        ArrayList<Node> path = solution.parentPath();
        Collections.reverse(path);
        for (Node n : path) {
            System.out.println(n.getBoard().getCreationMove());
            System.out.println(n.getBoard());
        }
        System.out.println("No. of steps: " + solution.getDepth());
        System.out.println("Configurations visited: " + nodesVisited);
    }
}

import java.awt.*;
import java.util.ArrayList;

public class Node implements Comparable<Node>{
    private Node parent;
    private Board board;
    private int depth;
    private int cost;
    private String hash; // used for checking to nodes are the same


    public Node(Board board){
        this.board = board;
        this.cost = 0;
        this.parent = null;
        hash = board.toString();
    }

    public Node(Node parent, Board board){
        this.parent = parent;
        this.board = board;
        this.depth = parent.getDepth() + 1;
        this.cost = depth + (6 - board.getRedPos().x) * 2;
        hash = board.toString();
    }

    public ArrayList<Node> getChildren(){
        ArrayList<Node> children = new ArrayList<>();
        for (Board b: board.getChildren()){
            children.add(new Node(this, b));
        }
        return children;
    }

    public ArrayList<Node> parentPath(){
        ArrayList<Node> path = new ArrayList<>();
        Node route = this;
        while (route!=null){
            path.add(route);
            route = route.getParent();
        }
        return path;
    }
    public Point getRedPos(){
        return board.getRedPos();
    }

    public boolean isEqual(Node n){
        return (hash.equals(n.getHash()));
    }

    public Board getBoard() {
        return board;
    }

    public Node getParent() {
        return parent;
    }

    public int getCost() {
        return cost;
    }

    public String getHash() {
        return hash;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public int compareTo(Node o) {
        int difference = cost - o.getCost();
        if(difference == 0){
            return  0;
        }else{
            return difference;
        }
    }
}

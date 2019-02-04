import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

//allows for sorting the arraylist of vehicles to check if two nodes are the same
public class Vehicle implements Comparable<Vehicle>{

    private String name; //red car has id of zero, other cars have id incremented
    private Point head; //position of the front of the car, always right most or bottom most point
    private Integer length; //length of the car
    private Color colour; //colour of car
    private Boolean horizontal; //true if car can move up and down, false if can move left to right
    private ArrayList<Point> points;

    public Vehicle (String name, Point head, Integer length, Boolean horizontal){
        if(head.x < 1 || head.x > 6 || head.y < 1 || head.y > 6){
            throw new IllegalArgumentException("CARS HEAD IS NOT ON BOARD");
        }
        if((horizontal && head.x < length) || (!horizontal && head.y < length)){
            throw new IllegalArgumentException("CARS LENGTH GOES OFF THE BOARD");
        }
        this.name = name;
        this.head = head;
        this.length = length;
        //this.colour = colour;
        this.horizontal = horizontal;

        points = new ArrayList<>();
        points.add(head);
        if(horizontal){
            for(int i = 1; i < length; i++){
                points.add(new Point(head.x - i, head.y));
            }
        }
        else{
            for(int i = 1; i < length; i++){
                points.add(new Point(head.x, head.y - i));
            }
        }
        Collections.reverse(points);
    }

    public ArrayList<Point> getPoints(){
        return points;
    }

    /**
     * Positive moves are ones that either go down or to the right, this function gets 'this' to move in the positive direction
     * @return null if car can't move in that direction, otherwise the cars new position
     */
    public Vehicle movePositive (){
        if (horizontal){
            if (head.x == 6){
                return null;
            }
            else {
                return new Vehicle(name, new Point(head.x + 1, head.y), length, true);
            }
        }
        else{
            if (head.y == 6){
                return null;
            }
            else{
                return new Vehicle(name, new Point(head.x, head.y + 1), length, false);
            }
        }
    }

    /**
     * Positive moves are ones that either go down or to the right, this function gets 'this' to move in the positive direction
     * @return null if car can't move in that direction, otherwise the cars new position
     */
    public Vehicle moveNegative (){
        if (horizontal){
            if (head.x == length){
                return null;
            }
            else {
                return new Vehicle(name, new Point(head.x - 1, head.y), length, true);
            }
        }
        else{
            if (head.y == length){
                return null;
            }
            else{
                return new Vehicle(name, new Point(head.x, head.y - 1), length, false);
            }
        }
    }

    public Point getHead() {
        return head;
    }

    public Point getTail(){
        if(horizontal){
            return new Point(head.x - length + 1, head.y);
        }
        else{
            return new Point(head.x, head.y - length + 1);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Vehicle other) {
        if (this.head.y < other.getHead().y){
            return -1;
        }
        else if (this.head.y > other.getHead().y){
            return 1;
        }
        else{
            if (this.head.x < other.getHead().x) {
                return -1;
            }
            else if(this.head.x > other.getHead().x){
                return 1;
            }
            else{
                return 0;
            }
        }
    }

    public boolean isEqual(Vehicle v){
        return (head.equals(v.head) && length.equals(v.getLength()) && (horizontal == v.isHorizontal()));
    }

    public Boolean isHorizontal() {
        return horizontal;
    }

    public Integer getLength() {
        return length;
    }
}

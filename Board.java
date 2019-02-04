import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {
    private Point redPos;
    private ArrayList<Vehicle> vehicles;
    private String creationMove;


    public Board (Integer redX){
        redPos = new Point(redX , 3);
        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Red", redPos, 2, true));
        creationMove = "Start";
    }

    public Board (Point redPos, ArrayList<Vehicle> vehicles, String move){
        this.redPos = redPos;
        this.vehicles = vehicles;
        this.creationMove = move;
    }

    public void addVehicle(Vehicle v){
        if (canBePlaced(v)){
            vehicles.add(v);
            Collections.sort(vehicles);
        }
        else{
            throw new IllegalArgumentException("Vehicle is placed on top of another vehicle");
        }
    }


    public ArrayList<Board> getChildren(){
        ArrayList<Board> children = new ArrayList<>();
        String move;
        for(Vehicle v: vehicles){
           Vehicle a = v.movePositive();
           Vehicle b = v.moveNegative();
           if(a != null && pointAvailable(a.getHead())){
               ArrayList<Vehicle> aVehicles = new ArrayList<>(vehicles);
               aVehicles.remove(v);
               aVehicles.add(a);
               if (a.isHorizontal()){
                   move = a.getName() + " Right";
               }
               else{
                   move = a.getName() + " Down";
               }
               if (a.getName().equals("Red")){
                    children.add(new Board(a.getHead(), aVehicles, move));
               }
               else {
                   children.add(new Board(redPos, aVehicles, move));
               }
           }
            if(b != null && pointAvailable(b.getTail())){
                ArrayList<Vehicle> bVehicles = new ArrayList<>(vehicles);
                bVehicles.remove(v);
                bVehicles.add(b);
                if (b.isHorizontal()){
                    move = b.getName() + " Left";
                }
                else{
                    move = b.getName() + " Up";
                }

                if (b.getName().equals("Red")){
                    children.add(new Board(b.getHead(), bVehicles, move));
                }
                else {
                    children.add(new Board(redPos, bVehicles, move));
                }
            }
        }
        return children;
    }

    /**
     * Check if there is space to put a new vehicle on the board
     * @param test vehicle to place
     * @return true if there is space, false if it clashes with a vehicle already on the board
     */
    private boolean canBePlaced(Vehicle test){
        for(Point p : test.getPoints()){
            if(!pointAvailable(p)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a point is taken up by a vehicle
     * @param p point to check
     * @return true if point does not have a vehicle on it, false if it does
     */
    public boolean pointAvailable(Point p){
        for(Vehicle v: vehicles){
            if(v.isHorizontal() && v.getHead().getX() >= p.getX()){
                for(Point o: v.getPoints()){
                    if(o.equals(p)){
                        return false;
                    }
                }
            }
            else if(!v.isHorizontal() && v.getHead().getY() >= p.getY()){
                for(Point o: v.getPoints()){
                    if(o.equals(p)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Used to check if this board is equal to another
     * @param b board to check against
     * @return true if they are same, false if not
     */
    public boolean isEqual(Board b){
        if(vehicles.size() != b.getVehicles().size()){
            return false;
        }
        else{
            for(int i = 0; i < vehicles.size(); i++){
                Vehicle u = vehicles.get(i);
                Vehicle v = b.getVehicles().get(i);
                if(!u.isEqual(v)){
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public String toString() {
        String[][] grid = new String[6][6];
        for (String[] row: grid) Arrays.fill(row, "__");

        for(Vehicle v: vehicles){
            for(Point p : v.getPoints()){
                String name = v.getName();
                if(name.length() == 1){
                    name += " ";
                }
                grid [p.y - 1][p.x - 1] = name.substring(0,2);
            }
        }

        String boardVis = "";
        for(int i = 0; i < 6; i++){
            boardVis += "|";
            for( int j = 0; j < 6; j++) {
                boardVis += grid [i][j];
                boardVis += "|";
            }
            boardVis += "\n";
        }
        return boardVis;
    }

    public Point getRedPos() {
        return redPos;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public String getCreationMove() {
        return creationMove;
    }
}

import java.awt.*;

public class Main {
    public static void main (String args[]) {
        /*Board c = new Board(3);
        c.addVehicle(new Vehicle("Lime", new Point(2,1), 2, true));
        c.addVehicle(new Vehicle("Blue", new Point(4,4), 3, false));
        c.addVehicle(new Vehicle("Orange", new Point(1,6), 2, false));
        c.addVehicle(new Vehicle("Purple", new Point(1,4), 3, false));
        c.addVehicle(new Vehicle("Green", new Point(5,6), 3, true));
        c.addVehicle(new Vehicle("ABlue", new Point(6,5), 2, true));
        c.addVehicle(new Vehicle("Yellow", new Point(6,3), 3, false));
        */
        Board hardest = new Board(4);
        hardest.addVehicle(new Vehicle("1", new Point(1,3), 2, false));
        hardest.addVehicle(new Vehicle("2", new Point(2,6), 2, false));
        hardest.addVehicle(new Vehicle("3", new Point(3,5), 2, false));
        hardest.addVehicle(new Vehicle("4", new Point(4,2), 2, false));
        hardest.addVehicle(new Vehicle("5", new Point(5,3), 3, false));
        hardest.addVehicle(new Vehicle("6", new Point(6,3), 3, false));
        hardest.addVehicle(new Vehicle("13", new Point(3,1), 3, true));
        hardest.addVehicle(new Vehicle("14", new Point(3,2), 2, true));
        hardest.addVehicle(new Vehicle("16", new Point(2,4), 2, true));
        hardest.addVehicle(new Vehicle("17", new Point(6,5), 2, true));
        hardest.addVehicle(new Vehicle("24", new Point(6,6), 2, true));
        Search.BFS(hardest);
        /*
        System.out.println(b);
        for(Board c : b.getChildren()){
            System.out.println(c);
        }*/
    }
}

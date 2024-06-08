import java.util.List;

public class Main {
    public static void main(String[] args) {
        Lift lift = new Lift(Direction.DOWN, 10, 10);
        lift.addRequest(new Request(Direction.DOWN, 9));
        lift.addRequest(new Request(Direction.UP, 8));
        lift.run();
        lift.addRequest(new Request(Direction.UP, 10));
    }
}

public class Request {
    Direction direction;
    int floor;
    Request(Direction direction, int floor){
        this.direction = direction;
        this.floor = floor;
    }

    @Override
    public java.lang.String toString() {
        return direction.name() + " " + floor;
    }
}

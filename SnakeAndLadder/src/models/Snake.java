package models;

public class Snake extends IObstacle{
    Snake(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    int getInitialPosition() {
        return this.start;
    }

    @Override
    int getEndPosition() {
        return this.end;
    }
}

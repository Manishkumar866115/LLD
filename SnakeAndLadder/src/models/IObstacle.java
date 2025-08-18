package models;

abstract class  IObstacle{
    int start;
    int end;
    abstract int getInitialPosition();
    abstract int getEndPosition();
}

package Entities;

public enum Coin {

    FIVE(5),
    ONE(1),
    TEN(10);
    private int value;

    public int getValue(){
        return value;
    }
    Coin(int val){
        this.value = val;
    }
}

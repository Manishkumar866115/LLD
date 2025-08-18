package models;

public class NormalDice implements Dice{
    @Override
    public int roll() {
        return 1 + (int)(Math.random() * 6);
    }
}

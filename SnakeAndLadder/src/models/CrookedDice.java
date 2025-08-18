package models;

import java.util.List;

public class CrookedDice implements Dice{

    List<Integer> possibleThrows = List.of(1,3,5);
    @Override
    public int roll() {
        return possibleThrows.get((int)(Math.random() * possibleThrows.size()));
    }
}

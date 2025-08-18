package models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MultiPlayerSnakeAndLadder implements ISnakeAndLadder{
    static int IdCounter = 1;
    int gameId;

    HashMap<Integer, IObstacle> obstacle;

    HashMap<String, Integer> playerPostion;

    Dice dice;
    Queue<User> players;
    User winner;

    User currentPlayer;
    int size;

    public MultiPlayerSnakeAndLadder(int size, User user, Dice dice){
        this.size = size;
        this.winner = null;
        players = new LinkedList<>();
        players.add(user);
        this.dice = dice;
        gameId = IdCounter++;
        obstacle = new HashMap<>();
        playerPostion = new HashMap<>();
        playerPostion.put(user.userId, 0);
        for(int i = 0; i < 5; i++){
            int start = (int)(Math.random() * size);
            int end = (int)(Math.random() * size);
            if( start == end){
                continue;
            }
            obstacle.put(Math.max(start,end), new Snake(Math.max(start,end), Math.min(start, end)));
        }
        for(int i = 0; i < 5; i++){
            int start = (int)(Math.random() * size);
            int end = (int)(Math.random() * size);
            if( start == end){
                continue;
            }
            obstacle.put(Math.min(start,end), new Ladder(Math.min(start,end), Math.max(start, end)));
        }
    }

    @Override
    public int getGameId() {
        return this.gameId;
    }

    @Override
    public void addUser(User user) {
        players.add(user);
        playerPostion.put(user.userId, 0);
    }

    @Override
    public void start() {
        int nextPosition = 0;
        while(true){
            currentPlayer = players.peek();
            System.out.println("Current turn player " + currentPlayer.userId);
            int roll = dice.roll();
            System.out.println("Current position " + playerPostion.get(currentPlayer.userId));
            System.out.println("roll: " + roll);
            nextPosition = playerPostion.get(currentPlayer.userId) + roll;
            System.out.println("next position " + nextPosition);
            if( nextPosition > size){
                System.out.println("Player: " + currentPlayer.userId + "Cannot move on a dice roll of " + roll);
                nextTurn();
                continue;
            }

            if( nextPosition == size){
                winner = currentPlayer;
                System.out.println("Player: " + currentPlayer.userId + " won");
                break;
            }

            if( obstacle.containsKey(nextPosition)){
                System.out.println("Player: " + currentPlayer.userId + " faced obstacle on roll " + roll);
                nextPosition = obstacle.get(nextPosition).getEndPosition();
            }
            playerPostion.put(currentPlayer.userId, nextPosition);
            System.out.println("next position " + nextPosition);
            nextTurn();
        }
    }

    private void nextTurn() {
        players.poll();
        players.add(currentPlayer);
    }
}

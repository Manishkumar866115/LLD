package service;

import models.Dice;
import models.ISnakeAndLadder;
import models.MultiPlayerSnakeAndLadder;
import models.User;

import java.util.HashMap;

public class GameManagementService {

    private static GameManagementService instance;

    HashMap<Integer, ISnakeAndLadder> games;

    public static GameManagementService getInstance() {
        if (instance == null) {
            synchronized (GameManagementService.class) {
                if (instance == null) {
                    instance = new GameManagementService();
                }
            }
        }
        return instance;
    }

    private GameManagementService(){
        games = new HashMap<>();
    }

    public int createGame(User user, Dice dice, int n){
        ISnakeAndLadder game = new MultiPlayerSnakeAndLadder(n, user, dice);
        games.put(game.getGameId(), game);
        return game.getGameId();
    }

    public void joinGame(User user, int gameId){
        if( !games.containsKey(gameId)){
            System.out.println("Cannot join game");
        }
        games.get(gameId).addUser(user);
    }

    public void startGame(int gameId){
        if( !games.containsKey(gameId)){
            System.out.println("Cannot start game");
        }
        games.get(gameId).start();
    }
}

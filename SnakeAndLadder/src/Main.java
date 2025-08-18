import models.Dice;
import models.NormalDice;
import models.User;
import service.GameManagementService;

public class Main {
    public static void main(String[] args) {
        /**
         * FR: Design a multiplayer snake and ladder game where a player can create a game and other player can join the game.
         * There can be 2 type of dice - normal dice and crooked dice( only rolls odd numbers)
         */
        User u1 = new User("1","M");
        User u2 = new User("2", "N");
        Dice dice = new NormalDice();
        GameManagementService gms = GameManagementService.getInstance();
        int gameId = gms.createGame(u1, dice, 100);
        gms.joinGame(u2, gameId);
        gms.startGame(gameId);

    }
}
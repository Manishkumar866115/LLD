import Entities.Coin;
import Entities.Product;
import VendingMachine.VendingMachine;

public class Main {

    public static void main(String[] args) {
        Product p1 = new Product(1, "lays", 15);
        Product p2 = new Product(2, "kit kat", 10);
         VendingMachine vendingMachine = new VendingMachine();
         vendingMachine.initalize();
         vendingMachine.initalDenotions(1, 5,10);
         vendingMachine.addProduct(p1);
         vendingMachine.addProduct(p2);
         vendingMachine.display();

        vendingMachine.insertCoin(Coin.TEN);
        vendingMachine.insertCoin(Coin.FIVE);
        vendingMachine.setCurrentStateAsSelectItemState();
        vendingMachine.selectItem(p1);
        vendingMachine.display();

    }
}
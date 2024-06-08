package state;

import Entities.Coin;
import Entities.Product;
import VendingMachine.VendingMachine;


public class NoCoinInsertedState implements State{

    VendingMachine vendingMachine;


    public NoCoinInsertedState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(Coin coin) {
            vendingMachine.getDenotions().put(coin, vendingMachine.getDenotions().get(coin) + 1);
            vendingMachine.setInsertedValue(vendingMachine.getInsertedValue() + coin.getValue());
    }

    @Override
    public void selectItem(Product number) {
        throw new RuntimeException("No coin inserted cannot select item");

    }

    @Override
    public void dispense(Product number) {
        throw new RuntimeException("No coin inserted cannot dispense item");
    }
}

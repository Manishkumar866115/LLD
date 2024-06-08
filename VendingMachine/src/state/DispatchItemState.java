package state;

import Entities.Coin;
import Entities.Product;
import VendingMachine.VendingMachine;

public class DispatchItemState implements State{

    VendingMachine vendingMachine;


    public DispatchItemState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(Coin coin) {
        throw  new RuntimeException("Cannot insert coin");
    }

    @Override
    public void selectItem(Product number) {

    }

    @Override
    public void dispense(Product product) {
        vendingMachine.getInventory().put(product,vendingMachine.getInventory().get(product) - 1 );
        vendingMachine.setCurrentStateAsCoinInsertedState();
        vendingMachine.setInsertedValue(0);
    }
}

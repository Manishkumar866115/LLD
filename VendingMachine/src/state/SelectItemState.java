package state;

import Entities.Coin;
import Entities.Product;
import VendingMachine.VendingMachine;

public class SelectItemState implements State{
    VendingMachine vendingMachine;

    public SelectItemState(VendingMachine vendingMachine){
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(Coin coin) {
        throw  new RuntimeException("Cannot insert coin");
    }

    @Override
    public void selectItem(Product product) {
           if( product.getPrice() > vendingMachine.getInsertedValue()){
               vendingMachine.setCurrentStateAsCoinInsertedState();
               throw new RuntimeException("Insert More Coins");
           }
           if( vendingMachine.getInventory().get(product) == 0){
               throw new RuntimeException("Out of Stock");
           }

           vendingMachine.setCurrentStateAsDispatchState();
           vendingMachine.dispense(product);
    }

    @Override
    public void dispense(Product number) {
        throw new RuntimeException("Cannot dispense in current state");
    }
}

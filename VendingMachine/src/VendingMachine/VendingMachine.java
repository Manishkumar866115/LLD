package VendingMachine;

import Entities.Coin;
import Entities.Product;
import state.DispatchItemState;
import state.NoCoinInsertedState;
import state.SelectItemState;
import state.State;

import java.util.HashMap;

public class VendingMachine {
    private HashMap<Coin, Integer> denotions = new HashMap<>();

    private HashMap<Product,Integer> inventory = new HashMap<>();

    private int insertedValue = 0;

    private State currentState;

    private State noCoinInsertedState;

    private State selectItemState;

    private State dispatchItemState;


    public void initalize(){
        noCoinInsertedState = new NoCoinInsertedState(this);
        selectItemState = new SelectItemState(this);
        dispatchItemState = new DispatchItemState(this);
        currentState = noCoinInsertedState;
    }

    public int getInsertedValue() {
        return insertedValue;
    }

    public void setInsertedValue(int insertedValue) {
        this.insertedValue = insertedValue;
    }

    public HashMap<Coin, Integer> getDenotions() {
        return denotions;
    }

    public HashMap<Product, Integer> getInventory() {
        return inventory;
    }

    public void setCurrentStateAsCoinInsertedState(){
        currentState = noCoinInsertedState;
    }

    public void setCurrentStateAsSelectItemState(){
        currentState = selectItemState;
    }

    public void setCurrentStateAsDispatchState(){
        currentState = dispatchItemState;
    }

    public void addProduct(Product product){
        if( inventory.containsKey(product)  ) {
            inventory.put(product, inventory.get(product) + 1);
        }else{
            inventory.put(product,1);
        }
    }

    public void initalDenotions(int tenCount, int fiveCount, int oneCount){
        denotions.put(Coin.TEN,tenCount);
        denotions.put(Coin.FIVE,fiveCount);
        denotions.put(Coin.ONE,oneCount);
    }

    public void display(){
        System.out.println(inventory);
    }

    public void insertCoin(Coin coin){
        currentState.insertCoin(coin);
    }

    public void selectItem(Product product){
        currentState.selectItem(product);

    }

    public void dispense(Product product){
        currentState.dispense(product);
    }


}

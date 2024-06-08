package state;

import Entities.Coin;
import Entities.Product;

public interface State {

    void insertCoin(Coin coin);
    void selectItem(Product number);
    void dispense(Product number);

}

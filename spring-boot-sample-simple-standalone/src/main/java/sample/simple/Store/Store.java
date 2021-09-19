package sample.simple.Store;

import sample.simple.Bank.IBank;
import sample.simple.Provider.IProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Store  implements IJustHaveALook,ILane,IFastLane{

    @Autowired
    IBank iBank;
    @Autowired
    IProvider iProvider;


    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void getProductInfo(String dodo) {
        System.out.println(dodo);
        this.iBank.getMoney();
    }

    @Override
    public void oneShotOrder() {

    }

    @Override
    public void addItemToCart() {

    }

    @Override
    public boolean pay() {
        return false;
    }
}

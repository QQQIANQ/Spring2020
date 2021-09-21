package sample.simple.Bank;

import org.springframework.stereotype.Component;

@Component
public class Bank implements IBank {
    @Override
    public void getMoney() {

    }

    @Override
    public boolean transfert(IBank bank1, float ammount) {
        return false;
    }
}

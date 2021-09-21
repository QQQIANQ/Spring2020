package sample.aop.Bank;

import org.springframework.stereotype.Component;

@Component
public class Bank implements IBank {
    @Override
    public double getMoney() {
        return 20.00;
    }

    @Override
    public boolean transfert(IBank bank1, float ammount) {
        return false;
    }
}

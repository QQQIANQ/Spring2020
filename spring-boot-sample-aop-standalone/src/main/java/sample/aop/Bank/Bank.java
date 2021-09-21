package sample.aop.Bank;

import org.springframework.stereotype.Component;

@Component
public class Bank implements IBank {
    @Override
    public void getMoney() {

    }

    @Override
    public boolean transfert() {
        return false;
    }
}

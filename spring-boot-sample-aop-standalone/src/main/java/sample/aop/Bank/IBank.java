package sample.aop.Bank;


public interface IBank {
    double getMoney();

    boolean transfert(IBank bank1, float ammount);
}

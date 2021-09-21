package sample.simple.Bank;


public interface IBank {
    void getMoney();

    //receive money from Bank account bank1
    boolean transfert(IBank bank1, float ammount);
}

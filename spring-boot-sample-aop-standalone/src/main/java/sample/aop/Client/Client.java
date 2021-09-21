package sample.aop.Client;

import sample.aop.Store.IFastLane;
import sample.aop.Store.IJustHaveALook;
import sample.aop.Store.ILane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Client implements IRun {

    @Autowired
    IFastLane ifast;
    @Autowired
    ILane iLane;
    @Autowired
    IJustHaveALook iJustHaveALook;

    @Override
    public void run() {

        System.out.println("I'm client");

        iJustHaveALook.getProductInfo("I want info about product dodo ");

    }
}

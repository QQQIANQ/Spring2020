package sample.aop.Store;

import org.springframework.stereotype.Component;

public interface IJustHaveALook {

    double getPrice();

    boolean isAvailable();

    void getProductInfo(String dodo);
}
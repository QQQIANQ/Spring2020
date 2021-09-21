package sample.aop.Store;

import org.springframework.stereotype.Component;

public interface ILane {
    void addItemToCart();

    boolean pay();
}

package sample.aop.Store;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut(value = "within(Store.*)")
    public void addDemo(){

    }
}

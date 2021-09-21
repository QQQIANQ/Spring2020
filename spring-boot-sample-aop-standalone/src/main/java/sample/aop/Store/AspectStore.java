package sample.aop.Store;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectStore {

    @Before(value = "sample.aop.Store.PointCuts.addDemo()")
    public void before(JoinPoint joinPoint){
        System.out.println("[AspectStore] before advise");
    }

    @Around(value = "sample.aop.Store.PointCuts.addDemo()")
    public void around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("[AspectStore] around advise1");
        pjp.proceed();
        System.out.println("[AspectStore] around advise2");
    }
}

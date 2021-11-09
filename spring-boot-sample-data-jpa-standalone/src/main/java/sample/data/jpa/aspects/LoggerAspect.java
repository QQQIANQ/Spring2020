package sample.data.jpa.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Component
public class LoggerAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * sample.data.jpa.web.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD :" + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }


    @Around("webLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        String methodName = point.getSignature().toShortString();

        Object[] params = point.getArgs();

        if (logger.isDebugEnabled()) {
            logger.info("{} start, request={}", methodName, params);
        }

        Object result;
        try {
            result = point.proceed();
        } catch (Exception e) {
            logger.info("{} error, exception={}", methodName, e);
            throw e;
        }

        if (logger.isDebugEnabled()) {
            logger.info("{} end, result={}", methodName, result);
        }
        return result;

    }
}

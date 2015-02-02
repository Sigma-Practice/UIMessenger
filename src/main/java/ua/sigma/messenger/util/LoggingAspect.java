package ua.sigma.messenger.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by vlad on 30.01.15.
 */
@Aspect
public class LoggingAspect {
    @Before("execution(* ua.sigma.messenger..dao..*(..))")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("***AspectJ*** DoBefore() is running!! intercepted : " + joinPoint.getSignature().getName());
    }

    @After("execution(* ua.sigma.messenger..dao..*(..))")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("***AspectJ*** DoAfter() is running!! intercepted : " + joinPoint.getSignature().getName());

    }

    @AfterReturning(pointcut = "execution(* ua.sigma.messenger..dao..*(..))", returning= "result")

    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("***AspectJ*** DoAfterReturning() is running!! intercepted : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result.toString());
    }

    @AfterThrowing(pointcut = "execution(* ua.sigma.messenger..dao..*(..))",
            throwing= "error")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("***AspectJ*** DoAfterThrowing() is running!! intercepted : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println("******");
    }




}

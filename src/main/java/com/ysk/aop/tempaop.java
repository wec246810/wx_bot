package com.ysk.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
@Aspect
public class tempaop {

    /**
     * 在核心业务执行前执行，不能阻止核心业务的调用。
     * @param joinPoint
     */
    private void doBefore(JoinPoint joinPoint) {
        System.out.println("-----doBefore().invoke-----");
        System.out.println(" 此处意在执行核心业务逻辑前，做一些安全性的判断等等");
        System.out.println(" 可通过joinPoint来获取所需要的内容");
        System.out.println("-----End of doBefore()------");
    }

    /**
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     *
     * 注意：当核心业务抛异常后，立即退出，转向After Advice
     * 执行完毕After Advice，再转到Throwing Advice
     * @param pjp
     * @return
     * @throws Throwable
     */
    private Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("-----doAround().invoke-----");
        System.out.println(" 此处可以做类似于Before Advice的事情");

        //调用核心逻辑
        Object retVal = pjp.proceed();

        System.out.println(" 此处可以做类似于After Advice的事情");
        System.out.println("-----End of doAround()------");
        return retVal;
    }

    /**
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     * @param joinPoint
     */
    private void doAfter(JoinPoint joinPoint) {
        System.out.println("-----doAfter().invoke-----");
        System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");
        System.out.println(" 可通过joinPoint来获取所需要的内容");
        System.out.println("-----End of doAfter()------");
    }

    /**
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     * @param joinPoint
     */
    private void doReturn(JoinPoint joinPoint) {
        System.out.println("-----doReturn().invoke-----");
        System.out.println(" 此处可以对返回值做进一步处理");
        System.out.println(" 可通过joinPoint来获取所需要的内容");
        System.out.println("-----End of doReturn()------");
    }

    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     * @param joinPoint
     * @param ex
     */
    private void doThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("-----doThrowing().invoke-----");
        System.out.println(" 错误信息："+ex.getMessage());
        System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
        System.out.println(" 可通过joinPoint来获取所需要的内容");
        System.out.println("-----End of doThrowing()------");
    }
}

package com.davih.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式锁注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface DistributedLock {

    String key(); //分布式锁的key 支持Spring el表达式

    int expireSec() default 2; //锁超时自动释放阈值

    boolean failureBreak() default false; //加锁异常时是否继续执行
}

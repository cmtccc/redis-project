package xyz.chenmt.www.chenmtrides.annotation;


import java.lang.annotation.*;

@Documented
@Retention(value = RetentionPolicy.RUNTIME)
//@TestAnnotation被限定只能使用在类、接口或方法上面
@Target(value = {ElementType.TYPE,ElementType.METHOD})
public @interface TestAnnotation {

    public String name();

    int age() default 18;

    int[] score();


}

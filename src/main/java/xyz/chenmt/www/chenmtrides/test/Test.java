package xyz.chenmt.www.chenmtrides.test;

import xyz.chenmt.www.chenmtrides.annotation.TestAnnotation;

import java.lang.reflect.Method;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/08/08 09:32
 */
public class Test {

    public static void main(String[] args){
        try {

            Student student=new Student();
            Class stuClass=student.getClass();
            //获取Student的Class对象
//            Class stuClass = Class.forName("xyz.chenmt.www.chenmtrides.test.Student");

            //说明一下，这里形参不能写成Integer.class，应写为int.class
            Method stuMethod = stuClass.getMethod("study",int.class);

            if(stuMethod.isAnnotationPresent(TestAnnotation.class)){
                System.out.println("Student类上配置了CherryAnnotation注解！");
                //获取该元素上指定类型的注解
                TestAnnotation testAnnotation = stuMethod.getAnnotation(TestAnnotation.class);
                System.out.println("name: " + testAnnotation.name() + ", age: " + testAnnotation.age()
                        + ", score: " + testAnnotation.score()[0]);
            }else{
                System.out.println("Student类上没有配置CherryAnnotation注解！");
            }
        }  catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}

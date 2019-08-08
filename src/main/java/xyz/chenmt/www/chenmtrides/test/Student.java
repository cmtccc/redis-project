package xyz.chenmt.www.chenmtrides.test;

import xyz.chenmt.www.chenmtrides.annotation.TestAnnotation;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/08/08 09:29
 */
public class Student {


    @TestAnnotation(name = "chenmt",age = 23,score = {12,14,16})
    public void study(int times){
        for(int i = 0; i < times; i++){
            System.out.println("Good Good Study, Day Day Up!");
        }
    }


}

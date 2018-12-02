package com.ls.demo.annotation;

/**
 * @Author: lishuai
 * @CreateDate: 2018/11/16 17:04
 */
@TestAnnotation(name="I'm class annotation")
public class Test {

    @TestAnnotation(name="I'm method annotation")
    public static void showAnnotation(@TestAnnotation(name="I'm paramater annotation")String type){

    }

}


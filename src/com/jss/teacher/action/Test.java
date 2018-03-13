package com.jss.teacher.action;
public class Test {
     
    static String input = "A、 编译时将产生错误；  B、 编译时正确，运行时将产生错误； C 、输出零；  D、 输出空。 ";
 
    public static void main(String[] args) {
         
        String regex = "[A-Z][|:|：|、|.| 、]";
        String[] str=input.split(regex);
        for(int i=0;i<str.length;i++){
        	System.out.println(str[i]);
        }
         
       /* System.out.println("A：sd1 B：ds 1C：de1 D：se1DA ".split(regex)[0]);
        System.out.println("A：sd1 B：ds 1C：de1 D：se1DA ".split(regex)[1]);
        System.out.println("A：sd1 B：ds 1C：de1 D：se1DA ".split(regex)[2]);
        System.out.println("A：sd1 B：ds 1C：de1 D：se1D A ".split(regex)[3]);
        System.out.println("A：sd1 B：ds 1C：de1 D：se1D 、A ".split(regex)[5]);*/
        //System.out.println("A：sd1 B：ds 1C：de1 D：se1D A ".split(regex)[5]);
       // System.out.println(input.split(regex)[5]);
    }
}
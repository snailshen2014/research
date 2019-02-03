package com.syj.javassist;

import javassist.*;
import javassist.bytecode.AccessFlag;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author shenyanjun1
 * @description ${description}
 * @create 2019-01-30 15:07
 */
public class TestJavassist {
    private static final  ClassPool POOL = ClassPool.getDefault();

    /**
     * test dynamically generate class
     */
    private static void generateClass() {
        try {
            CtClass ct = POOL.makeClass("com.syj.javassist.SsistGenerateClass");//创建类
            ct.setInterfaces(new CtClass[]{POOL.makeInterface("java.lang.Cloneable")});//让类实现Cloneable接口
            CtField field = new CtField(CtClass.intType,"id",ct);
            field.setModifiers(AccessFlag.PUBLIC);
            ct.addField(field);
            //添加构造函数
            CtConstructor constructor = CtNewConstructor.make("public SsistGenerateClass (int id) {this.id = id;}",ct);
            ct.addConstructor(constructor);

            //添加方法
            CtMethod helloM = CtNewMethod.make("public void hello(String des) { System.out.println(des);}",ct);
            ct.addMethod(helloM);

            ct.writeFile();//将生成的.class文件保存到磁盘
            //下面的代码为验证代码
            Field[] fields = ct.toClass().getFields();
            System.out.println("属性名称：" + fields[0].getName() + "  属性类型：" + fields[0].getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * test modify class
     */
    private static void modifiyClass() {
        try {
            CtClass ct=POOL.getCtClass("com.syj.javassist.Point");
            CtMethod m=ct.getDeclaredMethod("move");
            m.insertBefore("{ System.out.print(\"dx:\"+$1); System.out.println(\"dy:\"+$2);}");
            m.insertAfter("{System.out.println(this.x); System.out.println(this.y);}");

            ct.writeFile();
            //通过反射调用方法，查看结果
            Class pc=ct.toClass();
            Method move= pc.getMethod("move",new Class[]{int.class,int.class});
            Constructor<?> con=pc.getConstructor(new Class[]{int.class,int.class});
            move.invoke(con.newInstance(1,2),1,2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static  void main(String[] args) {
        //test generate class
        generateClass();
        //test modify clas
        modifiyClass();

    }
}

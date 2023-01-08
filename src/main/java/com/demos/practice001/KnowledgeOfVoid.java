package com.demos.practice001;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;

@RunnerMember
public class KnowledgeOfVoid {

    @RunnerTask
    public void task() {
        System.out.println(
                """
                void is a primitive type in java, just like byte, short, int, long, float, double, bool, char;
                Void is a reference type in java, just like Object and any other class.
                Each primitive type has a corresponding packaged reference type in java.
                So, there is nine primitive types in java and theirs packaged reference type:
                byte        Byte
                short       Short
                int         Integer
                long        Long
                float       Float
                double      Double
                boolean     Boolean
                char        Character
                void        Void
                """
        );

        usageOfVoid();
    }

    void voidReturnedMethod() {
        //return null; // Error here: Cannot return a value from a method with void result type
        return; // This is right, cause `void` is a `Primitive Type`
    }

    Void VoidReturnedMethod() {
        //return; // Error here: Missing return value, method called an Objective return, so you can't return nothing
        return null; // This is right, cause `Void` is a `Reference Type`, and null is the only value of it.
    }

    void usageOfVoid() {
        Class<Integer> intType = Integer.TYPE;
        Class<Void> voidType = Void.TYPE;
        System.out.println(intType);
        System.out.println(voidType);
        System.out.println(int.class);
    }
}

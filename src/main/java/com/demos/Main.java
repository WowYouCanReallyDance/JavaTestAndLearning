package com.demos;

import com.demos.annotations.RunnerMember;
import com.demos.annotations.RunnerTask;
import com.demos.utils.fmt.Printable;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        final String packageName = Main.class.getPackageName();
        final Reflections reflections = new Reflections(packageName);
        final Set<Class<?>> annotatedTypes = reflections.getTypesAnnotatedWith(RunnerMember.class);

        annotatedTypes.forEach(type -> {
            Object instance = null;
            Constructor<?> constructor = null;
            try {
                constructor = type.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                if (constructor != null) {
                    instance = constructor.newInstance();
                } else {
                    throw new NoSuchMethodException();
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            }

            System.out.println(Printable.joinBorderLine("Running Type " + type.getSimpleName() + " Start!", "="));

            for (Method method : type.getDeclaredMethods()) {
                if (method.isAnnotationPresent(RunnerTask.class)) {
                    boolean runnable = method.getAnnotation(RunnerTask.class).value();
                    if (runnable) {
                        System.out.println(Printable.joinBorderLine("Running Task " + method.getName() + " Start!", "-"));
                        try {
                            if (instance != null) {
                                method.invoke(instance);
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Printable.joinBorderLine("Running Task " + method.getName() + " End!", "-"));
                    }
                }
            }

            System.out.println(Printable.joinBorderLine("Running Type " + type.getSimpleName() + " End!", "="));
        });
    }
}

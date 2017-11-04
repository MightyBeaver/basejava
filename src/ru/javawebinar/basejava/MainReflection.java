package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        Resume r = new Resume();
        Class resumeReflection = r.getClass();
        Field field = resumeReflection.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        Method m = resumeReflection.getDeclaredMethod("toString" );
        System.out.println(m.getName());
        System.out.println(m.invoke(r));
    }
}
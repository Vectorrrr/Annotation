package logic;

import model.FirstAnnotationClass;
import model.PrimitiveType;
import model.annotation.ConsoleSetup;
import model.annotation.InitDefualtValue;
import utils.PrimitiveTypeService;

import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * Created by igladush on 14.03.16.
 */
public class AnnotationProcessor {
    private static final String ERROR_CLASS_FOUND = "I don't found class";
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(FirstAnnotationClass.class.getName());
        System.out.println(createNewInstance("model.FirstAnnotationClass"));
        sc.close();
    }

    private static Object createNewInstance(String className) {
        Class c = null;
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.err.println(ERROR_CLASS_FOUND);
            e.printStackTrace();
        }
        Object instance = null;
        try {
            instance = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        initField(instance);
        return instance;
    }

    private static void initField(Object instance) {
        if (instance != null) {
            for (Field f : instance.getClass().getDeclaredFields()) {
                System.out.println(f.getAnnotations().length);
                if (PrimitiveTypeService.isPrimitive(f)) {
                    checkDefualtInit(instance, f);
                    checkConsoleInit(instance,f);
                }
            }

        }
    }

    private static void checkConsoleInit(Object instance, Field f) {
        if(f.isAnnotationPresent(ConsoleSetup.class)){
            PrimitiveType pt = PrimitiveTypeService.getPrimateType(f);
            System.out.println("Input value type of "+pt.getWrapName()+" for field "+ f.getName());
            String value=sc.nextLine();
            f.setAccessible(true);
            try {
                f.set(instance, pt.convertToDefaultType(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    private static void checkDefualtInit(Object instance, Field f) {
        if (f.isAnnotationPresent(InitDefualtValue.class)) {
            String value = f.getAnnotation(InitDefualtValue.class).defualtValue();
            PrimitiveType pt = PrimitiveTypeService.getPrimateType(f);

            f.setAccessible(true);
            try {
                f.set(instance, pt.convertToDefaultType(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}


package hometask_2.Task_1_singleton;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MultipleClassLoaders {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> singletonClass1 = classLoader.loadClass("hometask_2.Task_1_singleton.Singleton");

        Method method1 = singletonClass1.getMethod("getInstance");
        Object singleton1 = method1.invoke(singletonClass1);

        System.out.println(singleton1);


        File file = new File("C:\\Users\\Yehor\\IdeaProjects\\Infopulse_homework\\out\\production\\Infopulse_homework");

        URL url = file.toURI().toURL();
        URL[] urls = new URL[] {url};

        ClassLoader customClassLoader = new URLClassLoader(urls, classLoader.getParent());
        Class<?> singletonClass2 = customClassLoader.loadClass("hometask_2.Task_1_singleton.Singleton");

        Method method2 = singletonClass2.getMethod("getInstance");
        Object singleton2 = method2.invoke(singletonClass2);

        System.out.println(singleton2);

        System.out.println("Singleton1 == Singleton2 ??? : " + singleton1 == singleton2);
    }
}

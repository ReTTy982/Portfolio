package edu.pwr.controler;

import edu.pwr.MyStatusListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class ListenerHandler {
    public final Object object;
    public final Class<?> myInterface;

    public  ListenerHandler(Class<?> myClass, String myInterface) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.object = myClass.getConstructor().newInstance();

        this.myInterface = setInterface(myClass,myInterface);
    }



    private Class<?> setInterface(Class<?> myClass, String interfaceName){

        Class<?>[] interfaces = myClass.getInterfaces();
        for (Class<?> i : interfaces){
            if (i.getName().contains(interfaceName)){
                return i;
            }
        }
        return null;
    }

    public Collection<String> getStatus() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getMethod("getStatuses");
        return (Collection<String>) method.invoke(object);
    }


    public Class<?> getMyInterface() {
        return myInterface.getClass();
    }
}

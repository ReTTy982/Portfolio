package edu.pwr.controler;

import edu.pwr.MyStatusListener;
import edu.pwr.processing.StatusListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassHandler {
    public final Object object;
    //protected final Class<?> myInterface;
    public ClassHandler(Class<?> myClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.object =myClass.getConstructor().newInstance();
    }
    public boolean submitTask(String task, ListenerHandler sl ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method[] methods = object.getClass().getMethods();
        Method method = object.getClass().getMethod("submitTask",String.class, StatusListener.class);
        return (boolean) method.invoke(object,task,sl.object);

    }

    public boolean submitTask(String task, MyStatusListener sl) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getMethod("submitTask",String.class, StatusListener.class);
        return (boolean) method.invoke(object,task,sl);
    }

    public String getResult() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = object.getClass().getMethod("getResult");
        return (String) method.invoke(object);

    }

    public String getInfo() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = object.getClass().getMethod("getInfo");
        return (String) method.invoke(object);
    }


}




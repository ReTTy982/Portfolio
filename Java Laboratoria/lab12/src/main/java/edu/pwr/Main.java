package edu.pwr;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = scriptEngineManager.getEngineFactories();
        for (ScriptEngineFactory factory : factories){
            System.out.println(factory.getEngineName() + " " + factory.getEngineVersion() + " " + factory.getNames());

        }
        if(factories.isEmpty()){
            System.out.println("NO script found");
        }
        MainWindow.main(args);

    }
}
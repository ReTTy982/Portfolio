package edu.pwr;

import javax.script.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScriptController {
    private final ScriptEngineManager manager = new ScriptEngineManager();
    private final ScriptEngine engine = manager.getEngineByName("graal.js");
    private String scriptContent;

    public void loadScript(Path scriptPath){

        try {
            scriptContent = Files.readString(scriptPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkState(String string) throws ScriptException, NoSuchMethodException {
        engine.getContext().getBindings(ScriptContext.ENGINE_SCOPE).put("string",string);
        engine.eval(scriptContent);
        Invocable invocable = (Invocable) engine;
        return (boolean) invocable.invokeFunction("gameOfLife",string);
    }


}

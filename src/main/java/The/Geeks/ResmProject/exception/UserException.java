package The.Geeks.ResmProject.exception;

import java.util.HashMap;
import java.util.Map;

public class UserException extends Exception{

    private static final Map<String,String> execptionMap= new HashMap<String,String>();
    
    public Map<String,String> exception(String message)
    {
        execptionMap.put("successful : ", "false");
        execptionMap.put("error : ", message);
        return execptionMap;
    }
    public Map<String,String> noException(String message)
    {
        execptionMap.put("successful : ", "true");
        execptionMap.put("error : ", message);
        return execptionMap;
    }
}

package client;
import com.beust.jcommander.Parameter;
import java.util.*;


public class Args {

    @Parameter(names = "-t")
    private String typeRequest;

    @Parameter(names = "-k")
    private String index;

    @Parameter(names = "-v")
    private String valueToDatabase;


    public Map<String, String> getResult() {

        Map<String, String> jsonArgs = new LinkedHashMap<>();

        switch (typeRequest) {
            case ("set"):

                jsonArgs.put("type", typeRequest);
                jsonArgs.put("key", index);
                jsonArgs.put("value", valueToDatabase);
                break;

            case ("get") :

            case ("delete"):

            case ("exit"):
                jsonArgs.put("type", typeRequest);
                jsonArgs.put("key", index);
                break;

            default:
                jsonArgs.put("type", "ERROR");
                break;
        }
        return jsonArgs;
    }
}

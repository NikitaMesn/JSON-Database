package server;

import java.util.*;

public class Database {
    private final String[] data;
    private final Map<String, String> base;


    public Database () {
        data = new String[100];
        base = new HashMap<>();
    }

    public Map<String, String> clientCommand(Map<String, String> command) {
        Map<String, String> answ = new LinkedHashMap<>();

        switch (command.get("type")) {
            case ("set"):
                return setValue(command.get("key"), command.get("value"));
            case ("get") :
                return getValue(command.get("key"));
            case ("delete"):
                return deleteValue(command.get("key"));
            default:
                answ.put("response","ERROR");
                answ.put("reason","No such key");
                return answ;
        }
    }

    private Map<String, String> getValue(String index){
        Map<String, String> answ = new LinkedHashMap<>();

        try {


            if (base.get(index) == null) {
                answ.put("response","ERROR");
                answ.put("reason","No such key");
            } else{
                answ.put("response","OK");
                answ.put("value", base.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            answ.put("response","ERROR");
            answ.put("reason","No such key");
        }catch (NumberFormatException num) {
            answ.put("response","ERROR");
            answ.put("reason","No such key");
        }
        return answ;
    }

    private Map<String, String> setValue(String index, String value) {
        Map<String, String> answ = new LinkedHashMap<>();

        try {
            base.put(index, value);
            answ.put("response","OK");
           answ.put("response","OK");
        } catch (IndexOutOfBoundsException e) {
            answ.put("response","ERROR");
            answ.put("reason","No such key");

        } catch (NumberFormatException num) {
            answ.put("response","ERROR");
            answ.put("reason","No such key");
        }
        return answ;
    }

    private Map<String, String> deleteValue(String index) {
        Map<String, String> answ = new LinkedHashMap<>();

        try {

            if (base.get(index) == null) {
                answ.put("response","ERROR");
                answ.put("reason","No such key");
            } else{
                base.remove(index);
                answ.put("response","OK");
            }


        } catch (IndexOutOfBoundsException e) {
            answ.put("response","ERROR");
            answ.put("reason","No such key");

        } catch (NumberFormatException num) {
            answ.put("response","ERROR");
            answ.put("reason","No such key");
        }
        return  answ;
    }

}

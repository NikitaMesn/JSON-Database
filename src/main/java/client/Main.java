package client;

import com.google.gson.Gson;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class Main {
    public static void main(String[] args)  {

        Args  arguments = new Args();
        String[] ar = {};

        try {
            JCommander.newBuilder()
                    .addObject(arguments)
                    .build()
                    .parse(args);

            Gson gson = new Gson();
            String output = gson.toJson(arguments.getResult());

            EchoClient cl = new EchoClient(output);
            cl.start();

        } catch (ParameterException e) {
            System.out.println("Wrong arguments\nPlease, try again");
        }
    }
}

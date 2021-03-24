package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import com.google.gson.Gson;


public class Server extends Thread {
    private static final int PORT = 8000;

    @Override
    public void start() {
        System.out.println("Server started!");
        Database base = new Database();
        Gson gson = new Gson();


        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (
                        Socket clientSocket = server.accept(); // accepting a new client
                        DataInputStream inputMessage = new DataInputStream(clientSocket.getInputStream());
                        DataOutputStream outputMessage  = new DataOutputStream(clientSocket.getOutputStream())

                ) {
                        String answers = inputMessage.readUTF();
                        Map<String, String> request = new LinkedHashMap<>();
                        request = gson.fromJson(answers, request.getClass());


                        if ("exit".equalsIgnoreCase(request.get("type"))) {
                            Map<String, String> answ = new LinkedHashMap<>();
                            answ.put("response","OK");
                            String output = gson.toJson(answ);
                            outputMessage.writeUTF(output);
                            clientSocket.close();
                            server.close();
                        }else {
                            String ans = gson.toJson(base.clientCommand(request));
                            outputMessage.writeUTF(ans);
                        }
                }
            }
        } catch (SocketException sc) {
            //System.out.println("by");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





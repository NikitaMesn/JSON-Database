package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient extends Thread{

    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8000;
    private static Scanner clientInput;
    private static String commands;

    public EchoClient(String commands) {
        System.out.println("Client started!");
        this.commands = commands;
    }

    @Override
    public void start()  {

        try (
                Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                DataInputStream inputMessage = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputMessage  = new DataOutputStream(clientSocket.getOutputStream())
        ) {

            outputMessage.writeUTF(commands);
            System.out.println("Sent: " + commands);
            System.out.println("Received: " + inputMessage.readUTF());

        } catch (IOException e) {
               e.printStackTrace();
        }
    }
}



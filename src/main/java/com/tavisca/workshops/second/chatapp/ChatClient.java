package com.tavisca.workshops.second.chatapp;

import java.io.*;
import java.net.Socket;

public class ChatClient {

    public static final int PORT = 8888;
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        try {
            Socket client = new Socket(HOST, PORT);
            if(client.isConnected())
                System.out.println("Connected to the server.");

            DataInputStream inStream = new DataInputStream(client.getInputStream());
            DataOutputStream outStream = new DataOutputStream(client.getOutputStream());

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage = "";
            String clientMessage = "";
            while(!clientMessage.equals("bye")){
                System.out.println("Enter your message: ");
                clientMessage = consoleReader.readLine();
                outStream.writeUTF(clientMessage);
                outStream.flush();

                System.out.println("Waiting for server...");
                serverMessage = inStream.readUTF();
                System.out.println("From server: " + serverMessage);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

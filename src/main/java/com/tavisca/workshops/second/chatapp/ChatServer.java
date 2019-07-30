package com.tavisca.workshops.second.chatapp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            System.out.println("Waiting for the client to connect...");
            Socket clientServer = serverSocket.accept();
            System.out.println("Connected with: " + clientServer.getInetAddress());

            DataInputStream inStream = new DataInputStream(clientServer.getInputStream());
            DataOutputStream outStream = new DataOutputStream(clientServer.getOutputStream());

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String serverMessage = "";
            String clientMessage = "";
            while(!serverMessage.equals("bye")){
                System.out.println("Waiting for client...");
                clientMessage = inStream.readUTF();
                System.out.println("From client: " + clientMessage);

                System.out.println("Enter your message: ");

                serverMessage = consoleReader.readLine();
                outStream.writeUTF(serverMessage);
                outStream.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

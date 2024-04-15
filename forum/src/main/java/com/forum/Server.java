package com.forum;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for clients...");
            
            IDandPassword idAndPassword = new IDandPassword();
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                
                launchLoginPage(idAndPassword.getLoginInfo(), clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private static void launchLoginPage(HashMap<String, String> loginInfo, Socket clientSocket) {
        new LoginPage(loginInfo, clientSocket);
    }
}

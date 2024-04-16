package com.forum;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "192.168.39.111"; // Замените на IP-адрес сервера
        int port = 12345;

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to the server");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // Отправляем сообщение на сервер
            out.println("Hello from client");

            // Читаем ответ от сервера
            String response = in.readLine();
            System.out.println("Server response: " + response);

            
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
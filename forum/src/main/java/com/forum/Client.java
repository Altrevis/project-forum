package com.forum;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "192.168.39.111"; // Remplacez par l'adresse IP du serveur
        int port = 8000;

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connecté au serveur");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // Envoyer un message au serveur
            out.println("Bonjour depuis le client");

            // Lire la réponse du serveur
            String response = in.readLine();
            System.out.println("Réponse du serveur : " + response);

            // Fermer la connexion
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
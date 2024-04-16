package com.forum;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // Adresse IP du serveur
        int port = 8000; // Port du serveur

        // Connexion au serveur
        Socket socket = new Socket(serverAddress, port);
        System.out.println("Connecté au serveur");

        // Initialisation des flux de lecture et d'écriture
        final PrintWriter[] writer = new PrintWriter[1];
        writer[0] = new PrintWriter(socket.getOutputStream(), true);
        final BufferedReader[] reader = new BufferedReader[1];
        reader[0] = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final Scanner[] scanner = new Scanner[1];
        scanner[0] = new Scanner(System.in);

        // Fermer la connexion
        socket.close();
    }
}



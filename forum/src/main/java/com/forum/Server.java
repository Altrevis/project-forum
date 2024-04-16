package com.forum;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int port = 8000;
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Serveur démarré sur le port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept(); // Attendre qu'un client se connecte
            System.out.println("Client connecté : " + clientSocket.getInetAddress());
            ClientHandler clientHandler = new ClientHandler(clientSocket); // Créer un gestionnaire de client pour ce client
            clients.add(clientHandler); // Ajouter le gestionnaire de client à la liste des clients
            clientHandler.start(); // Démarrer le thread pour gérer ce client
        }
    }

    // Méthode pour diffuser un message à tous les clients
    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) { // Vérifier que le client n'est pas l'expéditeur
                client.sendMessage(message); // Envoyer le message au client
            }
        }
    }

    // Méthode pour gérer la déconnexion d'un client
    public static void clientDisconnected(ClientHandler client) {
        System.out.println("Client déconnecté : " + client.getClientSocket().getInetAddress());
        broadcastMessage("Client déconnecté : " + client.getClientSocket().getInetAddress(), null); // Diffuser un message sur la déconnexion du client
        clients.remove(client); // Supprimer le client de la liste des clients
    }
}

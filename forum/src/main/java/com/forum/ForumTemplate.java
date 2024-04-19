package com.forum;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ForumTemplate {
    public static void main(String[] args) {
        // Exemple d'utilisation
        String titre = "Titre de mon post";
        String description = "Ceci est la description de mon post. Elle contient des informations supplémentaires.";
        String nomFichier = "post_forum.html";
        createForumPost(titre, description, nomFichier);
    }

    public static void createForumPost(String title, String description, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Écrire le titre en gros/gras
            printWriter.println("<h1><strong>" + title + "</strong></h1>");

            // Écrire la description en texte normal
            printWriter.println("<p>" + description + "</p>");

            printWriter.close();
            System.out.println("Fichier " + fileName + " créé avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du fichier : " + e.getMessage());
        }
    }
}

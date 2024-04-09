package com.oop;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class App 
{
    public static void main(String[] args) {

        JFrame window = new JFrame("Projet forum java");
        window.add(new JLabel("Hello world!"));
        window.setSize(900,900);
        window.setLocation(0, 0);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        UserManager userManager = new UserManager();

        // Inscription
        userManager.register("utilisateur1", "motdepasse1");
        userManager.register("utilisateur2", "motdepasse2");

        // Connexion
        userManager.login("utilisateur1", "motdepasse1"); // devrait afficher "Login successful."
        userManager.login("utilisateur1", "motdepasse2"); // devrait afficher "Invalid username or password."
    }
}

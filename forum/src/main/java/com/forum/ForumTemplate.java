package com.forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ForumTemplate extends JFrame {
    private JTextField titreField;
    private JTextField pseudoField;
    private JTextArea questionArea;

    public ForumTemplate(String userID) {
        setTitle("Créer un fil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel titreLabel = new JLabel("Titre: ");
        JLabel pseudoLabel = new JLabel("Pseudo: ");
        JLabel questionLabel = new JLabel("Question: ");
        titreField = new JTextField();
        pseudoField = new JTextField(userID); // Le pseudo est automatiquement rempli avec l'userID
        questionArea = new JTextArea();
        JButton sendButton = new JButton("Envoyer");

        sendButton.addActionListener(new SendButtonListener());

        panel.add(titreLabel);
        panel.add(titreField);
        panel.add(pseudoLabel);
        panel.add(pseudoField);
        panel.add(questionLabel);
        panel.add(new JScrollPane(questionArea));
        panel.add(new JLabel());
        panel.add(sendButton);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String titre = titreField.getText();
            String pseudo = pseudoField.getText();
            String question = questionArea.getText();

            if (!titre.isEmpty() && !pseudo.isEmpty() && !question.isEmpty()) {
                saveThread(titre, pseudo, question);
                titreField.setText("");
                questionArea.setText("");
                
                // Fermer la fenêtre courante et revenir à ForumScreen2
                dispose();
                new ForumScreen3(pseudo); // Utilisez le pseudo pour initialiser le champ pseudo dans ForumScreen2
            } else {
                JOptionPane.showMessageDialog(ForumTemplate.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void saveThread(String titre, String pseudo, String question) {
            String url = "jdbc:mysql://localhost:3306/db_forum";
            String user = "root";
            String password = "password";
            String sqlInsert = "INSERT INTO threads (userID, titre, description) VALUES (?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(url, user, password);
                 PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {
                insertStatement.setString(1, pseudo);
                insertStatement.setString(2, titre);
                insertStatement.setString(3, question);
                insertStatement.executeUpdate();
                System.out.println("Thread saved successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ForumTemplate("User123"); // Par défaut, nous passons un userID (pseudo) à la fenêtre
        });
    }
}

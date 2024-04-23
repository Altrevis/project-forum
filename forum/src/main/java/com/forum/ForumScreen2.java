package com.forum;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ForumScreen2 extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private String userID;

    public ForumScreen2(String userID) {
        this.userID = userID;
        setTitle("Créer un fil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JButton createThreadButton = new JButton("Accueil");
        JButton joinThreadButton = new JButton("Rejoindre un fil");

        createThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre pour créer un fil (ForumScreen2)
                dispose();
                new ForumScreen(userID);
            }
        });

        joinThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre pour rejoindre un fil (ForumScreen3)
                dispose();
                new ForumScreen3(userID);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createThreadButton);
        buttonPanel.add(joinThreadButton);

        add(buttonPanel, BorderLayout.NORTH);
        menuBar.add(createThreadButton);
        menuBar.add(joinThreadButton);

        JPanel messagePanel = new JPanel();
        JLabel titleLabel = new JLabel("Titre: ");
        JLabel messageLabel = new JLabel("Description: ");
        JTextField titleField = new JTextField(10);
        messageField = new JTextField(10);
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");

        sendButton.addActionListener(new SendButtonListener(titleField));
        resetButton.addActionListener(new ResetButtonListener());

        messagePanel.add(titleLabel);
        messagePanel.add(titleField);
        messagePanel.add(messageLabel);
        messagePanel.add(messageField);
        messagePanel.add(sendButton);
        messagePanel.add(resetButton);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        add(menuBar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private class SendButtonListener implements ActionListener {
        private JTextField titleField;

        public SendButtonListener(JTextField titleField) {
            this.titleField = titleField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String titre = titleField.getText();
            String description = messageField.getText();

            if (!titre.isEmpty() && !description.isEmpty()) {
                IDandPassword idAndPassword = new IDandPassword();
                @SuppressWarnings("unchecked")
                HashMap<String, String> loginInfo = idAndPassword.getLoginInfo();
                userID = loginInfo.keySet().iterator().next();
                // Enregistrer le fil de discussion dans la base de données
                saveThread(userID, titre, description);
                
                // Enregistrer le template HTML dans la base de données
                CreateDB.saveTemplate(userID, titre, description);
                
                // Afficher un message dans la console
                System.out.println("Template HTML enregistré dans la base de données avec succès !");
                
                chatArea.append(userID + ": " + titre + " - " + description + "\n");
                titleField.setText("");
                messageField.setText("");
            }
        }

        private void saveThread(String userID, String titre, String description) {
            
            IDandPassword idAndPassword = new IDandPassword();
            @SuppressWarnings("unchecked")
            HashMap<String, String> loginInfo = idAndPassword.getLoginInfo();
            userID = loginInfo.keySet().iterator().next();

                String url = "jdbc:mysql://localhost:3306/db_forum";
                String user = "root";
                String password = "password";
                String sqlCreateTable = "CREATE TABLE IF NOT EXISTS threads (" +
                                        "userID VARCHAR(50), " +
                                        "titre VARCHAR(100), " +
                                        "description TEXT" +
                                        ")";
        
                                        String sqlInsert = "INSERT INTO threads (userID, titre, description) VALUES (?, ?, ?)";
        
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement createStatement = connection.prepareStatement(sqlCreateTable);
                     PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {
        
                    
                    createStatement.executeUpdate();
        
                    
                    insertStatement.setString(1, userID);
                    insertStatement.setString(2, titre);
                    insertStatement.setString(3, description);
                    insertStatement.executeUpdate();
        
                    System.out.println("Data saved successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageField.setText("");
        }
    }
}
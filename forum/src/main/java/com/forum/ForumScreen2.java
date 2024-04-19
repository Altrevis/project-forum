package com.forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
        JLabel messageLabel = new JLabel("Enter Message: ");
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
                saveThread(titre, description);

                chatArea.append(userID + ": " + titre + " - " + description + "\n");
                titleField.setText("");
                messageField.setText("");
            }
        }

        private void saveThread(String titre, String description) {
            String url = "jdbc:mysql://localhost:3306/db_forum";
            String user = "root";
            String password = "password";

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                Statement statement = connection.createStatement();

                String sqlInsert = "INSERT INTO threads (titre, description) VALUES ('" + titre + "', '" + description + "')";
                statement.executeUpdate(sqlInsert);

                System.out.println("Fil de discussion enregistré dans la base de données avec succès !");
            } catch (SQLException ex) {
                ex.printStackTrace();
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

package com.forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ForumScreen4 extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private String userID;
    public ForumScreen4(String userID, String selectedThreadInfo) {
        this.userID = userID;
        setTitle("Thread");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

       chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);
        
        new JMenuBar();
        JButton createThreadButton = new JButton("Créer un fil");
        JButton joinThreadButton = new JButton("Rejoindre un fil");
        JButton refreshButton = new JButton("Re-lancer"); // Bouton de rafraîchissement
   
        
        JLabel label = new JLabel(selectedThreadInfo);
        add(label, BorderLayout.CENTER); // Utilisation du BorderLayout.CENTER pour afficher le texte au centre

        createThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre pour créer un fil (ForumScreen2)
                dispose();
                new ForumTemplate(userID);
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

        refreshButton.addActionListener(new ActionListener() { // Action pour le bouton de rafraîchissement
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ForumScreen4(userID, selectedThreadInfo); // Ouvrir une nouvelle instance de ForumScreen4
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createThreadButton);
        buttonPanel.add(joinThreadButton);
        buttonPanel.add(refreshButton); // Ajouter le bouton de rafraîchissement
        add(buttonPanel, BorderLayout.NORTH);

        JPanel messagePanel = new JPanel();
        messagePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Ajouter une marge supérieure de 20 pixels

        add(buttonPanel, BorderLayout.NORTH);

        JLabel messageLabel = new JLabel("Enter Message: ");
        messageField = new JTextField(20);
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");

        sendButton.addActionListener(new SendButtonListener());
        resetButton.addActionListener(new ResetButtonListener());

        messagePanel.add(messageLabel);
        messagePanel.add(label);
        messagePanel.add(messageField);
        messagePanel.add(sendButton);
        messagePanel.add(resetButton);

      
       

        add(scrollPane, BorderLayout.CENTER); // Déplacer le chatArea vers le centre
        add(messagePanel, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                IDandPassword idAndPassword = new IDandPassword();
                @SuppressWarnings("unchecked")
                HashMap<String, String> loginInfo = idAndPassword.getLoginInfo();
                userID = loginInfo.keySet().iterator().next();
                chatArea.append(userID + ": " + message + "\n");
                messageField.setText("");
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
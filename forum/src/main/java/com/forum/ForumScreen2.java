package com.forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        sendButton.addActionListener(new SendButtonListener());
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
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            if (!message.isEmpty()) {
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
package com.forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForumScreen2 extends JFrame {
    private JTextArea descriptionArea;
    private JTextField titleField;
    private String userID;

    public ForumScreen2(String userID) {
        this.userID = userID;
        setTitle("Créer un fil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton createThreadButton = new JButton("Accueil");
        JButton joinThreadButton = new JButton("Rejoindre un fil");

        createThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ForumScreen(userID);
            }
        });

        joinThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ForumScreen3(userID);
            }
        });

        buttonPanel.add(createThreadButton);
        buttonPanel.add(joinThreadButton);

        add(buttonPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel titleLabel = new JLabel("Titre: ");
        inputPanel.add(titleLabel, gbc);

        gbc.gridy++;
        titleField = new JTextField(30);
        inputPanel.add(titleField, gbc);

        gbc.gridy++;
        JLabel descriptionLabel = new JLabel("Description: ");
        inputPanel.add(descriptionLabel, gbc);

        gbc.gridy++;
        descriptionArea = new JTextArea(10, 30);
        descriptionArea.setLineWrap(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        inputPanel.add(descriptionScrollPane, gbc);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel2 = new JPanel();
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");

        sendButton.addActionListener(new SendButtonListener());
        resetButton.addActionListener(new ResetButtonListener());

        buttonPanel2.add(sendButton);
        buttonPanel2.add(resetButton);
        add(buttonPanel2, BorderLayout.SOUTH);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            if (!title.isEmpty() && !description.isEmpty()) {
                
                titleField.setText("");
                descriptionArea.setText("");
            }
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            titleField.setText("");
            descriptionArea.setText("");
        }
    }
}
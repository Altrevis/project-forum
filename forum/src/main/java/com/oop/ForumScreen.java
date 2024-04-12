package com.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForumScreen extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;

    public ForumScreen(String userID) {
        setTitle("Simple Forum");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveAsItem = new JMenuItem("Save As");
        fileMenu.add(openItem);
        fileMenu.add(saveAsItem);
        menuBar.add(fileMenu);

        JPanel messagePanel = new JPanel();
        JLabel messageLabel = new JLabel("Enter Message: ");
        messageField = new JTextField(20);
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");

        sendButton.addActionListener(new SendButtonListener());
        resetButton.addActionListener(new ResetButtonListener());

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
    }

    public ForumScreen() {
        //TODO Auto-generated constructor stub
    }

    private class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                chatArea.append("You:" + message + "\n");
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

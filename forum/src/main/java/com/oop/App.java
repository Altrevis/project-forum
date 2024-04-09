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
    }
}

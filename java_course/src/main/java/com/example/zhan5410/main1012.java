package com.example.zhan5410;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class main1012 {
    public static void main(String[] args) {
        JFrame jfr = new JFrame();
        jfr.setSize(new Dimension(500, 700));
        jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton jbt1 = new JButton("jbutton1");
        JButton jbt2 = new JButton("jbutton2");
        JButton jbt3 = new JButton("jbutton3");
        JLabel jbl = new JLabel("jlabel1");
        JTextArea jta = new JTextArea("jtextarea");

        jbt2.setBackground(Color.GRAY);

        jfr.getContentPane().add(BorderLayout.EAST, jbt1);
        jfr.getContentPane().add(BorderLayout.WEST, jbt2);
        jfr.getContentPane().add(BorderLayout.SOUTH, jbt3);
        jfr.getContentPane().add(BorderLayout.NORTH, jbl);
        jfr.getContentPane().add(BorderLayout.CENTER, jta);

        jfr.setVisible(true);
    }
    
}

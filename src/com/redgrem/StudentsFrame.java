package com.redgrem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsFrame extends JFrame {

    JButton button;


    public StudentsFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,100, 800, 600);
        setName("Hello");
        Container contentPanel = getContentPane();
        contentPanel.setLayout(new BorderLayout());
        button = new JButton("всякая чухня");
        contentPanel.add(button, BorderLayout.PAGE_END);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Click");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentsFrame();
    }
}

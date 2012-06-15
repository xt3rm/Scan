package syo_gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class View_Test extends JFrame {

        private int currentCard = 1;
        private JPanel cardPanel;
        private CardLayout cl;

        public View_Test() {

                setTitle("Card Layout Example");
                setSize(300, 150);
                cardPanel = new JPanel();

                cl = new CardLayout();
                cardPanel.setLayout(cl);
                JPanel p1 = new JPanel();
                JPanel p2 = new JPanel();
                JPanel p3 = new JPanel();
                JPanel p4 = new JPanel();
                JLabel lab1 = new JLabel("Card1");
                JLabel lab2 = new JLabel("Card2");
                JLabel lab3 = new JLabel("Card3");
                JLabel lab4 = new JLabel("Card4");
                p1.add(lab1);
                p2.add(lab2);
                p3.add(lab3);
                p4.add(lab4);

                cardPanel.add(p1, "1");
                cardPanel.add(p2, "2");
                cardPanel.add(p3, "3");
                cardPanel.add(p4, "4");
                JPanel buttonPanel = new JPanel();
                JButton b1 = new JButton("Previous");
                JButton b2 = new JButton("Next");
                buttonPanel.add(b1);
                buttonPanel.add(b2);
                b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                if (currentCard > 1) {
                                        currentCard -= 1;
                                        cl.show(cardPanel, "" + (currentCard));
                                }
                        }
                });

                b2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                if (currentCard < 4) {
                                        currentCard += 1;
                                        cl.show(cardPanel, "" + (currentCard));
                                }
                        }
                });
                getContentPane().add(cardPanel, BorderLayout.NORTH);
                getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        }

        public static void main(String[] args) {
                View_Test cl = new View_Test();
                cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                cl.setVisible(true);
        }
}
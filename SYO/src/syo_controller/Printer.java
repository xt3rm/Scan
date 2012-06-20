package syo_controller;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
 
public class Printer extends JFrame {
    
    private JTextField textfeld;
    private JButton submit;
    private Image imgBarcode;
    
    public Printer() throws IOException {
        super("Printer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        textfeld = new JTextField(35);
        textfeld.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        add(textfeld, "Center");
        submit = new JButton("Print");
        imgBarcode = ImageIO.read(new File("images/syo_logo.jpg"));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                print(textfeld.getText(),imgBarcode);
            }        
        });
        add(submit, "South");
    }
    
    private void print(String text, Image pic) {
        PrintJob auftrag = getToolkit().getPrintJob(this, "Mein 1. Ausdruck", null);
        if(auftrag != null) {
            Graphics graphik = auftrag.getGraphics();
            if (graphik != null) {
                graphik.setFont(new Font("TimesRoman", Font.PLAIN, 24)); 
                graphik.drawString(text, 40, 70); 
                graphik.drawImage(pic, 200, 200, 200, 200, null);
                graphik.dispose();
            }
            auftrag.end();
        }
    }
    
    public static void main(String[] args) throws IOException {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex){
        }
        Printer p = new Printer();
        p.pack();
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
        
}


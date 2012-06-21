package syo_controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
 


/**
 * Die Klasse Printer stellt einen Drucker zur Verfügung.
 * Dieser kann aufgerufen werden und nimmt ein Bild entgegen. Das Bild wird automatisch auf die passende Grösse 
 * zugeschnitten. In einem Druckermenu können die gewünschten Einstellungen getätigt werden.
 * @author ebeckm
 *
 */
public class Printer extends JFrame {
    
    private JLabel lblText;
    private JButton submit;
    private Image imgBarcode;
	private ImagePanel imgpanel;

/**
 * Der Konstruktor nimmt ein Bild entgegen, bereitet die Druckvorschau vor und stellt den
 * Drucken-Button zur Verfügung
 * @param img
 * 			Bild, welches zuerst angezeigt und anschliessend gedruckt wird.
 * @throws IOException
 */
    public Printer(Image img) throws IOException {
    	super("Printer");
    	this.imgBarcode = img;
    	this.setSize(500,500);
    	this.setLayout(null);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	//Druckvorschaupanel
    	imgpanel = new ImagePanel(new ImageIcon(imgBarcode).getImage());
    	add(imgpanel);
    	imgpanel.setBounds(0,50,484,420);
        
        //Textlabel
        lblText = new JLabel("Automatischer Testdruck - SYO, scan your objects");
        lblText.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        lblText.setBounds(40,0,440,40);
        this.add(lblText);
        lblText.setBackground(Color.white);
        
        //Druckenbutton
        submit = new JButton("Print");
        submit.setBounds(0,432,484,30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	
                print(lblText.getText(),imgBarcode);
            }        
        });
        add(submit);
        submit.setVisible(true);
    }
    
    /**
     * Die Methode print druckt den mitgegebenen Text und das gewüschte Bild
     * @param text
     * 			Ein Text, welcher zusätzlich zum Bild gedruckt wird
     * @param pic
     * 			Das Bild, welches korrekt formatiert ausgedruckt wird
     */
    private void print(String text, Image pic) {
        PrintJob auftrag = getToolkit().getPrintJob(this, "Mein 1. Ausdruck", null);
        if(auftrag != null) {
            Graphics graphik = auftrag.getGraphics();
            if (graphik != null) {
                graphik.setFont(new Font("TimesRoman", Font.PLAIN, 11)); 
                graphik.drawString(text, 80, 70); 
                graphik.drawImage(pic, 100, 100, 97, 50, null);
                graphik.dispose();
            }
            auftrag.end();
        }
    }
    
/*
    public static void main(String[] args) throws IOException {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex){
        }
        Image imgToPrint = ImageIO.read(new File("images/code128.jpg"));
        Printer p = new Printer(imgToPrint);
        p.setLocationRelativeTo(null);
        p.setVisible(true);
    }
  */
    
    
    /**
     * Innere Klasse. Dient zur Darstellung des Bildvorschaupanels
     * im Druckenpfenster
     * @author ebeckm
     *
     */
    class ImagePanel extends JPanel {

    	  private Image img;

    	  public ImagePanel(String img) {
    	    this(new ImageIcon(img).getImage());
    	  }

    	  public ImagePanel(Image img) {
    	    this.img = img;
    	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    	    setPreferredSize(size);
    	    setMinimumSize(size);
    	    setMaximumSize(size);
    	    setSize(size);
    	    setLayout(null);
    	  }

    	  public void paintComponent(Graphics g) {
    		  int breite = img.getWidth(null);
    		  breite = (480-breite)/2;
    		  g.drawImage(img, breite , 50, null);
    	  }

    	}
}


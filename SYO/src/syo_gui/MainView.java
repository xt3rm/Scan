package syo_gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainView extends JFrame {

	/**
	 * 
	 */
	
	Image picLogo = new ImageIcon("syo.png").getImage(); // Bild holen


    //Skalierung des Bildes
    int b1 = picLogo.getWidth(this);
    int h1 = picLogo.getHeight(this);
    int   percent1 = 29;
    Image scaled1 =   picLogo.getScaledInstance((b1*percent1/100),(h1*percent1/100),Image.SCALE_SMOOTH );
	
	private static final long serialVersionUID = 8892701173173371771L;

	
	
	public MainView(String title) {
		this.setTitle(title);
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	 public void paint(Graphics g){
	      g.drawImage(picLogo, 0, 24, this);//Position (Hintergrund)  
	 }
	
}

package syo_gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainView extends JFrame {
	
    //Tobi-Stuff---
	private static final long serialVersionUID = 8892701173173371771L;

	
	//Panel---
	JPanel pnlContent = new JPanel();
	HeadlinePanel pnlHeadline;
    JPanel pnlNavigation = new JPanel();
    JPanel pnlView = new JPanel();
	
    
	public MainView(String title) {
		this.setTitle(title);
		this.setSize(900, 600);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        
		
		//set size of the panels
		pnlContent.setLayout(null);
        pnlContent.setSize(900,600);    
        pnlNavigation.setLayout(null);
        pnlView.setLayout(null);

        pnlNavigation.setBounds(0,100,200,500);
        pnlView.setBounds(200,100,700,500);
        
        
        this.getContentPane().add(pnlNavigation);
        pnlNavigation.setBackground(Color.RED);
        
        this.getContentPane().add(pnlView);
        pnlView.setBackground(Color.BLUE);
        
        this.getContentPane().add(pnlContent);
        pnlContent.add(new MainView.HeadlinePanel());
    
	}
	
	class HeadlinePanel extends JPanel {
        private Image theImage;
        
        public HeadlinePanel(){
        	this.setSize(900,100);
        	
        	try{
        		 theImage = ImageIO.read(new File("syo.png"));   
        	}catch(IOException ex){
        		ex.printStackTrace();
        	}
        }
        @Override
        public void paint(Graphics g) {
        	System.out.println("Good");
            g.drawImage(theImage, 0, 0, null); // see javadoc for more info on the parameters
            
        }
    }
	
	public void paint(Graphics g) {

        
    }
}

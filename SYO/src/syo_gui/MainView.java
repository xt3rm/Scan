package syo_gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class MainView extends JFrame {
	
	
	JTextArea area = new JTextArea();
	
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
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        		
		pnlContent.setLayout(null);
        pnlContent.setSize(900,600);    
        pnlNavigation.setLayout(null);
        pnlView.setLayout(null);

        pnlNavigation.setBounds(0,100,225,500);
        pnlView.setBounds(230,100,700,500);

        this.getContentPane().add(pnlNavigation);
        pnlNavigation.setBackground(Color.WHITE);
        
        this.getContentPane().add(pnlView);
        pnlView.setBackground(Color.WHITE);
        
        this.getContentPane().add(pnlContent);
        pnlContent.add(new MainView.HeadlinePanel());
        pnlContent.setBackground(Color.lightGray);
        
//-------------------------------------------------------
//  PANEL - NAVIGATION - pnlNavigation
//-------------------------------------------------------
        
    	
    	
//-------------------------------------------------------
//  PANEL - CONTENT - pnlContent
//-------------------------------------------------------
   	//Ja, ich weiss, hier steht noch  nichts!!	
    	
    	
        
        
        
	}
	
//-------------------------------------------------------
//  PANEL - HEADLINE - HeadlinePanel	
//-------------------------------------------------------
	
	class HeadlinePanel extends JPanel {
		private Image theImage;
		
		
		
        
        public HeadlinePanel(){
        	this.setSize(900,95);
        	this.setBackground(Color.WHITE);
        	this.setVisible(true);
        	
        	try{
        		 theImage = ImageIO.read(new File("syo_logo.JPG"));  
        		 theImage =   theImage.getScaledInstance((900),(95),theImage.SCALE_SMOOTH );
        		 
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

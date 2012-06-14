package syo_gui;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainView extends JFrame implements MouseListener{
	
    //Tobi-Stuff---
	private static final long serialVersionUID = 8892701173173371771L;

	
	HeadlinePanel pnlHeadline;
	JPanel pnlContent = new JPanel();
    JPanel pnlNavigation = new JPanel();
    JPanel pnlView;
    JPanel card1;
    JPanel card2;
    
    
    Label lblNavigation;
    JButton cmdSammlung;
    
    final static String SAMMLUNGEN = "Card mit Sammlungen";
    final static String TYPERSTELLUNG = "Card mit Typerstellung";
    
    
	public MainView(String title) {
		this.setTitle(title);
		this.setSize(900, 600);
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        		        
/*        
*-------------------------------------------------------
*  PANEL - NAVIGATION - pnlNavigation
*-------------------------------------------------------
*/
        pnlNavigation.setLayout(null);
        pnlNavigation.setBounds(0,100,225,500);
        
        pnlNavigation.setBackground(Color.WHITE);
        lblNavigation = new Label();
        pnlNavigation.add(lblNavigation);
       
        
        lblNavigation.setBackground(new Color(222,222,222));
        lblNavigation.setForeground(new Color(0,0,0));
        lblNavigation.setAlignment(1);
        lblNavigation.setBounds(37, 10, 150, 30);
        lblNavigation.setFont(new Font((lblNavigation.getFont()).getFontName(), (lblNavigation.getFont()).getStyle(), 16));
    	lblNavigation.setText("Navigationspunkte");
    	
    	
    	cmdSammlung = new JButton("Sammlungen anzeigen");
    	pnlNavigation.add(cmdSammlung);
    	cmdSammlung.setVisible(true);
    	
    	
        
/*    	
*-------------------------------------------------------
*  PANEL - View - pnlView
*-------------------------------------------------------	
*/
    	
    	pnlView = new JPanel(null);
        pnlView.setBounds(230,100,700,500);
        pnlView.setBackground(Color.WHITE);

        card1 = new JPanel();
        card2 = new JPanel();
        
        pnlView.add(card1, SAMMLUNGEN);
        pnlView.add(card2, TYPERSTELLUNG);
        
        repaint();


        
        
        
/*    	
*-------------------------------------------------------
*  PANEL - View-Card1-Sammlungen
*-------------------------------------------------------	
*/
     
        
        
        
		
/*
*-------------------------------------------------------
*  PANEL - CONTENT - pnlContent
*-------------------------------------------------------       
*/
                
        pnlContent.setLayout(null);
        pnlContent.setSize(900,600);  
        pnlContent.add(new MainView.HeadlinePanel());
        pnlContent.setBackground(new Color(222,222,222));
        pnlContent.add(pnlNavigation);
        pnlContent.add(pnlView);
        this.getContentPane().add(pnlContent);
	}
	
	
/*
*-------------------------------------------------------
*  PANEL - HEADLINE - HeadlinePanel	
*-------------------------------------------------------
*/	
	
	
	class HeadlinePanel extends JPanel {
		
		private static final long serialVersionUID = -1802094992738102637L;
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
            g.drawImage(theImage, 0, 0, null);
        }
    }
	
	
//----Ende - HEADLINE ---------------------------------------	

	
	
	
	
	
//----PAINT-METHODE------------------------------------------	
	
	public void paint(Graphics g) {
		lblNavigation.setVisible(true);
		
    }


	
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

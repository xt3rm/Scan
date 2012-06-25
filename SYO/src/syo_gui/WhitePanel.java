package syo_gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * @author ebeckm
 *
 */
@SuppressWarnings("serial")
class WhitePanel extends JPanel 
{
    private Image theImage;
    public WhitePanel() 
    {
        this.setSize(900,95);
    	this.setBackground(Color.WHITE);
    	this.setVisible(true);
        try {                
            theImage = ImageIO.read(new File("images/syo_logo.jpg"));
            theImage = theImage.getScaledInstance((900),(95),Image.SCALE_SMOOTH );
        } catch (IOException ex) {
                ex.printStackTrace();
        }  
    }
    
    @Override
    public void paint(Graphics g) {
         g.drawImage(theImage, 0, 0, null); // see javadoc for more info on the parameters
    }
}
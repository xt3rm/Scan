package syo_gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.ListSelectionModel;


import syo_model.DBTool;



@SuppressWarnings("serial")
public class MainView extends JFrame implements Observer {
 
    
    private int currentCard = 1;

	private CardLayout cl;

    JPanel pnlContent = new JPanel();
    JPanel pnlNavigation = new JPanel();
    private JPanel pnlView;
    
    Label lblNavigation;
    Label lblNaviStuff;

    JButton cmdSammlung;
    JButton cmdVerwaltung;
    JButton cmdInfo;
    JButton cmdStuff;
    
    JButton cmdPrevious;
    JButton cmdNext;
    
    final static String SAMMLUNGEN = "Alle Sammlungen werden angezeigt";
    final static String OBJEKT = "Objekt kann angezeigt werden";
    final static String TYPERSTELLUNG = "Neuer Typ kann erstellt werden";
    final static String SAMMLUNGERSTELLUNG = "Neue Sammlung kann erstellt werden";
    final static String OBJEKTERSTELLUNG = "Neues Objekt kann erstellt werden";
    final static String FELDERSTELLUNG = "Neues Feld erstellen";
    final static String VERWALTUNG = "Die Verwaltung wird angezeigt";
    final static String DIESAMMLUNG = "Objekte der Sammlung";
    final static String TEST = "Die Verwaltung wird angezeigt";

    
    public MainView() {
    	DBTool.getInstance().addObserver(this);
        this.setTitle("SYO - Alles ist besser | Unser Motto: Das Beste ist Schlecht genug!!");	
		this.setSize(900, 600);
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel pnlCard1;
	    JPanel pnlCard2;
	    JPanel pnlCard3;
	    JPanel pnlCard4;
	    JPanel pnlCard5;
	    JPanel pnlCard6;
	    JPanel pnlCard7;
	    JPanel pnlCard8;
	    JPanel pnlCard9;
		
    	
/*  	
*-------------------------------------------------------
*  PANEL - View - pnlView
*-------------------------------------------------------	
*/  	
	    cl = new CardLayout();

		pnlView = new JPanel();
		pnlView.setLayout(cl);
    	
        pnlView.setBounds(230,100,700,500);
        pnlView.setBackground(Color.RED);


    	//card-Definition
        pnlCard1 = new JPanel(null);
        pnlCard2 = new JPanel(null);
        pnlCard3 = new JPanel(null);
        pnlCard4 = new JPanel(null);
        pnlCard5 = new JPanel(null);
        pnlCard6 = new JPanel(null);
        pnlCard7 = new JPanel(null);
        pnlCard8 = new JPanel(null);
        pnlCard9 = new JPanel(null);

        
        
        pnlView.add(pnlCard1, "1");
        pnlView.add(pnlCard2, "2");
        pnlView.add(pnlCard3, "3");
        pnlView.add(pnlCard4, "4");
        pnlView.add(pnlCard5, "5");
        pnlView.add(pnlCard6, "6");
        pnlView.add(pnlCard7, "7");
        pnlView.add(pnlCard8, "8");
        pnlView.add(pnlCard9, "9");
          
/*    	
*-------------------------------------------------------
*  PANEL - View-Card1-SAMMLUNGEN - pnlCard1
*-------------------------------------------------------	
*/	
        
        JLabel lblCard1 = new JLabel(SAMMLUNGEN);
        pnlCard1.add(lblCard1);
        
        

        pnlCard1.setBackground(new Color(255,255,255));
        
        lblCard1.setBounds(100, 20, 300, 30);
        lblCard1.setVisible(true);
        
        JButton cmdCard1neueSammlung;
        cmdCard1neueSammlung = new JButton("neue Sammlung");
        pnlCard1.add(cmdCard1neueSammlung);
        cmdCard1neueSammlung.setVisible(true);
        cmdCard1neueSammlung.setBounds(420, 70, 185, 30);
        
        String List[] = {"Blabla","Supii","lala","weiss ni","42","jaja","weiss ni","42","jaja"};
        

        
        cmdCard1neueSammlung = new JButton("Sammlungen anzeigen");
        pnlCard1.add(cmdCard1neueSammlung);
        cmdCard1neueSammlung.setVisible(true);
        cmdCard1neueSammlung.setBounds(100, 100, 185, 30);

        JList liCard1Sammlungen = new JList(List);
        pnlCard1.add(liCard1Sammlungen);
        liCard1Sammlungen.setVisible(true);
        liCard1Sammlungen.setBounds(30,150,500,200);

        
        
        /*JComboBox cmbCard1Sammlungen = new JComboBox(List);
        pnlCard1.add (cmbCard1Sammlungen);
        cmbCard1Sammlungen.setVisible(true);
        cmbCard1Sammlungen.setBounds(10,80,200,30);
   

/*    	
*-------------------------------------------------------
*  PANEL - View-Card2-OBJEKT - pnlCard2
*-------------------------------------------------------	
*/
        JLabel lblCard2 = new JLabel(OBJEKT);
        pnlCard2.add(lblCard2);

        pnlCard2.setBackground(new Color(255,255,255));
        
        lblCard2.setBounds(100, 20, 300, 30);
        lblCard2.setVisible(true); 
        repaint();
        
        JLabel lblCard2Sammlung = new JLabel("Titel");
        pnlCard2.add(lblCard2Sammlung);
        lblCard2Sammlung.setBounds(50, 90, 300, 30);      
        lblCard2Sammlung.setVisible(true); 
        repaint();

        
        JList liCard2Sammlung = new JList(List);
        pnlCard2.add(liCard2Sammlung);
        liCard2Sammlung.setVisible(true);
        liCard2Sammlung.setBounds(30,150,350,200); 
        
        
        JButton cmdCard2bearbeiten;
        cmdCard2bearbeiten = new JButton("bearbeiten");
        pnlCard2.add(cmdCard2bearbeiten);
        cmdCard2bearbeiten.setVisible(true);
        cmdCard2bearbeiten.setBounds(420, 70, 185, 30);
        
        JButton cmdCard2zurueck;
        cmdCard2zurueck = new JButton("zurück");
        pnlCard2.add(cmdCard2zurueck);
        cmdCard2zurueck.setVisible(true);
        cmdCard2zurueck.setBounds(420, 120, 185, 30);
        
/*    	
*-------------------------------------------------------
*  PANEL - View-Card3-TYPERSTELLUNG
*-------------------------------------------------------	
*/
        JLabel lblCard3 = new JLabel(TYPERSTELLUNG);
        pnlCard3.add(lblCard3);

        pnlCard3.setBackground(new Color(255,255,255));
        
        lblCard3.setBounds(100, 20, 300, 30);
        lblCard3.setVisible(true); 
        
        JLabel lblCard3Typname = new JLabel("Typname:");
        pnlCard3.add(lblCard3Typname);
        lblCard3Typname.setBounds(50, 90, 300, 30);      
        lblCard3Typname.setVisible(true); 
        repaint();
      
        
        JTextField txtCard3Typname = new JTextField();  
        pnlCard3.add (txtCard3Typname);
        txtCard3Typname.setVisible(true);
        txtCard3Typname.setBounds(130,90,185,30);
        
        JList liCard3Felder = new JList(List);
        pnlCard3.add(liCard3Felder);
        liCard3Felder.setVisible(true);
        liCard3Felder.setBounds(30,150,350,200); 
        
        
        JButton cmdCard3neuesFeld;
        cmdCard3neuesFeld = new JButton("Neues Feld");
        pnlCard3.add(cmdCard3neuesFeld);
        cmdCard3neuesFeld.setVisible(true);
        cmdCard3neuesFeld.setBounds(420, 70, 185, 30);
        
        
        JButton cmdCard3entfernen;
        cmdCard3entfernen = new JButton("entfernen");
        pnlCard3.add(cmdCard3entfernen);
        cmdCard3entfernen.setVisible(true);
        cmdCard3entfernen.setBounds(420, 120, 185, 30);
        
        
        JButton cmdCard3weiter;
        cmdCard3weiter = new JButton("weiter");
        pnlCard3.add(cmdCard3weiter);
        cmdCard3weiter.setVisible(true);
        cmdCard3weiter.setBounds(420, 170, 185, 30);
        
/*    	
*-------------------------------------------------------
*  PANEL - View-Card4-SAMMLUNGERSTELLUNG
*-------------------------------------------------------	
*/
        JLabel lblCard4 = new JLabel(SAMMLUNGERSTELLUNG);
        pnlCard4.add(lblCard4);

        pnlCard4.setBackground(new Color(255,255,255));
        
        lblCard4.setBounds(50, 50, 120, 30);
        lblCard4.setVisible(true); 
        

       
        
/*    	
*-------------------------------------------------------
*  PANEL - View-Card5-OBJEKTERSTELLUNG 
*-------------------------------------------------------	
*/

        JLabel lblCard5 = new JLabel(OBJEKTERSTELLUNG);
        pnlCard5.add(lblCard5);

        pnlCard5.setBackground(new Color(255,255,255));
        
        lblCard5.setBounds(50, 50, 120, 30);
        lblCard5.setVisible(true); 
    
        
/*    	
*-------------------------------------------------------
*  PANEL - View-Card6-FELDERSTELLUNG
*-------------------------------------------------------	
*/
        
        JLabel lblCard6 = new JLabel(FELDERSTELLUNG+" - Card6");
        pnlCard6.add(lblCard6);
        lblCard6.setBounds(100, 20, 250, 30);
        lblCard6.setVisible(true); 
        
        JLabel lblCard6Feldname = new JLabel("Feldname:");
        pnlCard6.add(lblCard6Feldname);
        lblCard6Feldname.setBounds(80, 200, 100, 30);
        lblCard6Feldname.setVisible(true);
        
        pnlCard6.setBackground(new Color(255,255,255));
        
        JTextField txtCard6Feldname;
        txtCard6Feldname = new JTextField(10);
        pnlCard6.add(txtCard6Feldname);
        txtCard6Feldname.setBounds(170, 200, 180, 30);
        

        
        
        JButton cmdCard6Abbrechen;
        cmdCard6Abbrechen = new JButton("Abbrechen");
        pnlCard6.add(cmdCard6Abbrechen);
        cmdCard6Abbrechen.setVisible(true);
        cmdCard6Abbrechen.setBounds(400, 180, 120, 30);
        
        
        JButton cmdCard6OK;
        cmdCard6OK = new JButton("OK");
        pnlCard6.add(cmdCard6OK);
        cmdCard6OK.setVisible(true);
        cmdCard6OK.setBounds(400, 230, 120, 30);

        pnlCard6.setBackground(new Color(255,255,255));

        
        

/*    	
*-------------------------------------------------------
*  PANEL - View-Card7-VERWALTUNG
*-------------------------------------------------------	
*/
        JLabel lblCard7 = new JLabel(VERWALTUNG+" - Card7");
        
        pnlCard7.add(lblCard7);

        pnlCard7.setBackground(new Color(255,255,255));
        
        lblCard7.setBounds(100, 20, 250, 30);
        lblCard7.setVisible(true); 
        
        
           
/*    	
*-------------------------------------------------------
*  PANEL - View-Card8-DIEVERWALTUNG-pnlCard8
*-------------------------------------------------------	
*/
        JLabel lblCard8 = new JLabel(DIESAMMLUNG+" - Card8");
        JButton cmdCard8Objektdelete;
        JButton cmdCard8neuesObjekt;
        JButton cmdCard8drucken;

        
        pnlCard8.add(lblCard8);
        pnlCard8.setBackground(new Color(255,255,255));
           
        lblCard8.setBounds(100, 20, 200, 30);
        lblCard8.setVisible(true); 
                
        String Listeli[] = {"Element1" , "Element2", "Element3", "Element4", "Element5", "Element6", "Element7"};
        JList liCard8Objekte = new JList(Listeli);
        pnlCard8.add(liCard8Objekte);
        liCard8Objekte.setVisible(true);
        liCard8Objekte.setBounds(50,150,200,300);
        
        cmdCard8Objektdelete = new JButton("Objekt löschen");
        pnlCard8.add(cmdCard8Objektdelete);
        cmdCard8Objektdelete.setVisible(true);
        cmdCard8Objektdelete.setBounds(400, 150, 170, 30);
        
        cmdCard8neuesObjekt = new JButton("neues Objekt");
        pnlCard8.add(cmdCard8neuesObjekt);
        cmdCard8neuesObjekt.setVisible(true);
        cmdCard8neuesObjekt.setBounds(400, 190, 170, 30);
            
        cmdCard8drucken = new JButton("drucken");
        pnlCard8.add(cmdCard8drucken);
        cmdCard8drucken.setVisible(true);
        cmdCard8drucken.setBounds(400, 230, 170, 30);


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
        lblNavigation.setBounds(31, 10, 156, 30);
        lblNavigation.setFont(new Font((lblNavigation.getFont()).getFontName(), (lblNavigation.getFont()).getStyle(), 16));
      	lblNavigation.setText("Navigationspunkte");
       	Font f = lblNavigation.getFont();
       	lblNavigation.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
            	
            	
        lblNaviStuff = new Label();
        pnlNavigation.add(lblNaviStuff);
        lblNaviStuff.setBackground(new Color(255,255,255));
        lblNaviStuff.setForeground(new Color(0,0,0));
        lblNaviStuff.setAlignment(1);
        lblNaviStuff.setBounds(10, 420, 205, 30);
        lblNaviStuff.setFont(new Font((lblNavigation.getFont()).getFontName(), (lblNavigation.getFont()).getStyle(), 10));
        lblNaviStuff.setText("If you think, Java-GUI is easy...");
        f = lblNavigation.getFont();
        lblNavigation.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
                
            	
            	
        cmdSammlung = new JButton("Sammlungen anzeigen");
        pnlNavigation.add(cmdSammlung);
        cmdSammlung.setVisible(true);
        cmdSammlung.setBounds(20, 100, 185, 30);
            	
        cmdVerwaltung = new JButton("Sammlungen verwalten");
        pnlNavigation.add(cmdVerwaltung);
        cmdVerwaltung.setVisible(true);
        cmdVerwaltung.setBounds(20, 150, 185, 30);
            	
        cmdInfo = new JButton("Über SYO");
        pnlNavigation.add(cmdInfo);
        cmdInfo.setVisible(true);
        cmdInfo.setBounds(20, 200, 185, 30);
            	
        cmdStuff = new JButton("Noch so ein Button");
        pnlNavigation.add(cmdStuff);
        cmdStuff.setVisible(true);
        cmdStuff.setBounds(20, 250, 185, 30);
            	
            	
            	
        //Previous & Next - Buttons / Only for Testing
        cmdPrevious = new JButton("<-- Previous");
        pnlNavigation.add(cmdPrevious);
        cmdPrevious.setVisible(true);
        cmdPrevious.setBounds(50, 310, 120, 36);
        cmdPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if (currentCard > 1) {
                	currentCard -= 1;
                    cl.show(pnlView, "" + (currentCard));
                }
            }
        });

            	
        cmdNext = new JButton("Next -->");
        pnlNavigation.add(cmdNext);
        cmdNext.setVisible(true);
        cmdNext.setBounds(50, 350, 120, 36);
        cmdNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if (currentCard < 10) {
                	currentCard += 1;
                    cl.show(pnlView, "" + (currentCard));
        		}
        	}
       	});
        
        
        
        
        
        
        
        
        pnlContent.setLayout(null);
        pnlContent.setSize(900,600);  
        pnlContent.add(new WhitePanel());
        pnlContent.setBackground(new Color(222,222,222));
        pnlContent.add(pnlNavigation);
        pnlContent.add(pnlView);

        getContentPane().add(pnlContent);
    }


	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Update");
		
	}
}


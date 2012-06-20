package syo_gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
    final static String FELDERSTELLUNG = "Erstellung eines neuen Feldes";
    final static String VERWALTUNG = "Die Verwaltung wird angezeigt";
    final static String UBER_SYO = "SYO - scan your object";
    final static String STUFF = "Das ist ein Text";

    
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
        
        JList liCard1Sammlungen = new JList(List);
        pnlCard1.add(liCard1Sammlungen);
        liCard1Sammlungen.setVisible(true);
        liCard1Sammlungen.setBounds(30,150,500,200);
        
        
       
   

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
*  PANEL - View-Card3-TYPERSTELLUNG - pnlCard3
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
*  PANEL - View-Card4-SAMMLUNGERSTELLUNG - pnlCard4
*-------------------------------------------------------	
*/
        JLabel lblCard4 = new JLabel(SAMMLUNGERSTELLUNG);
        pnlCard4.add(lblCard4);

        pnlCard4.setBackground(new Color(255,255,255));
        
        lblCard4.setBounds(50, 50, 120, 30);
        lblCard4.setVisible(true); 
        

       
        
/*    	
*-------------------------------------------------------
*  PANEL - View-Card5-OBJEKTERSTELLUNG - pnlCard5
*-------------------------------------------------------	
*/

        JLabel lblCard5 = new JLabel(OBJEKTERSTELLUNG);
        pnlCard5.add(lblCard5);

        pnlCard5.setBackground(new Color(255,255,255));
        
        lblCard5.setBounds(100, 20, 200, 30);
        lblCard5.setVisible(true); 
        
        JLabel lblCard5Name = new JLabel("Name:");
        pnlCard5.add(lblCard5Name);
        lblCard5Name.setBounds(30, 90, 300, 30);      
        lblCard5Name.setVisible(true); 
        repaint();

        JTextField txtCard5neueSammlung = new JTextField();  
        pnlCard5.add(txtCard5neueSammlung);
        txtCard5neueSammlung.setVisible(true);
        txtCard5neueSammlung.setBounds(80,90,185,30);
    
        JComboBox cmbCard5Typauswählen = new JComboBox(List);
        pnlCard5.add (cmbCard5Typauswählen);
        cmbCard5Typauswählen.setVisible(true);
        cmbCard5Typauswählen.setBounds(30,140,235,30);
        
        JButton cmdCard5NeuerTyp;
        cmdCard5NeuerTyp = new JButton("Neuer Typ");
        pnlCard5.add(cmdCard5NeuerTyp);
        cmdCard5NeuerTyp.setVisible(true);
        cmdCard5NeuerTyp.setBounds(420, 90, 185, 30);
        
        JButton cmdCard5weiter;
        cmdCard5weiter = new JButton("weiter");
        pnlCard5.add(cmdCard5weiter);
        cmdCard5weiter.setVisible(true);
        cmdCard5weiter.setBounds(420, 140, 185, 30);
/*    	
*-------------------------------------------------------
*  PANEL - View-Card6-FELDERSTELLUNG pnl Card6
*-------------------------------------------------------	
*/
        
        JLabel lblCard6 = new JLabel(FELDERSTELLUNG);
        pnlCard6.add(lblCard6);
        
        pnlCard6.setBackground(new Color(255,255,255));
        
        lblCard6.setBounds(100, 20, 300, 30);
        lblCard6.setVisible(true); 
        
        
        JLabel lblCard6Feldname = new JLabel("Feldname:");
        pnlCard6.add(lblCard6Feldname);
        lblCard6Feldname.setBounds(30, 90, 300, 30);      
        lblCard6Feldname.setVisible(true); 
        repaint();

        JTextField txtCard56Feldname = new JTextField();  
        pnlCard6.add(txtCard56Feldname);
        txtCard56Feldname.setVisible(true);
        txtCard56Feldname.setBounds(100,90,185,30);
        
        
        
        JRadioButton rbtnCard6Text = new JRadioButton("Text", true);
        JRadioButton rbtnCard6Zahl = new JRadioButton("Zahl" , false);
        
        ButtonGroup rbtnGroupCard6 = new ButtonGroup();
        rbtnGroupCard6.add(rbtnCard6Text);
        rbtnGroupCard6.add(rbtnCard6Zahl);
 
        pnlCard6.add(rbtnCard6Text);
        rbtnCard6Text.setVisible(true);
        rbtnCard6Text.setBounds(400, 90, 185,30);
        
        pnlCard6.add(rbtnCard6Zahl);
        rbtnCard6Zahl.setVisible(true);
        rbtnCard6Zahl.setBounds(400, 140, 185,30);
        
        JButton cmdCard6abbrechen;
        cmdCard6abbrechen = new JButton("abbrechen");
        pnlCard6.add(cmdCard6abbrechen);
        cmdCard6abbrechen.setVisible(true);
        cmdCard6abbrechen.setBounds(70, 370, 185, 30);
        
        
        JButton cmdCard6weiter;
        cmdCard6weiter = new JButton("weiter");
        pnlCard6.add(cmdCard6weiter);
        cmdCard6weiter.setVisible(true);
        cmdCard6weiter.setBounds(70, 420, 185, 30);
        
        
/*    	
*-------------------------------------------------------
*  PANEL - View-Card7-VERWALTUNG - pnlCard7
*-------------------------------------------------------	
*/
        JLabel lblCard7 = new JLabel(VERWALTUNG);
        pnlCard7.add(lblCard7);

        pnlCard7.setBackground(new Color(255,255,255));
        
        lblCard7.setBounds(50, 50, 120, 30);
        lblCard7.setVisible(true); 
        

        
        
/*    	
*-------------------------------------------------------
*  PANEL - View-Card8-Über SYO - pnlCard8
*-------------------------------------------------------	
*/
        JLabel lblCard8 = new JLabel(UBER_SYO);
        pnlCard8.add(lblCard8);

        pnlCard8.setBackground(new Color(255,255,255));
              
        lblCard8.setBounds(100, 20, 200, 30);
        lblCard8.setVisible(true);
        
        JLabel lblCard8Info = new JLabel("<html>Die Bedienung von SYO ist nicht einfach.<br>Für Fragen, Probleme, " +
        		"Komplikationen wenden Sie sich bitte an eine kompetente Fachperson.<br>" +
        		"Falls diese Ihnen nicht weiterhelfen kann, so melden Sie sich bei der <br>" +
        		"Konkurenz und Sie werden feststellen, dass Ihnen dort noch weniger geholfen <br>" +
        		"werden kann.<br><br>" +
        		"SYO dankt für Ihre Treue<br><br><br>" +
        		"Die Software SYO - scan your objects ist frei erhältlich und wird unter GPL gehandelt.<br>" +
        		"Jegliche komperzielle Änderungen oder kostenpflichtige Veröffentlichungen können strafrechtlich Verfolgt werden.<br><br>" +
        		"SYO hält sich vor, Hot Dogs zu verkaufen!</html>");
        pnlCard8.add(lblCard8Info);
        lblCard8Info.setBounds(70, 100, 500, 250);
        lblCard8Info.setVisible(true);

                
                
/*    	
*-------------------------------------------------------
*  PANEL - View-Card9-Stuff - pnlCard9
*-------------------------------------------------------	
*/
        JLabel lblCard9 = new JLabel(STUFF);
        pnlCard9.add(lblCard9);

        pnlCard9.setBackground(new Color(255,255,255));
                        
        lblCard9.setBounds(100, 20, 120, 30);
        lblCard9.setVisible(true); 
                        
        
                        
                        
        
        
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
        cmdSammlung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	cl.show(pnlView, "" + (1));
            }
        });
        
        
            	
        cmdVerwaltung = new JButton("Sammlungen verwalten");
        pnlNavigation.add(cmdVerwaltung);
        cmdVerwaltung.setVisible(true);
        cmdVerwaltung.setBounds(20, 150, 185, 30);
        cmdVerwaltung.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	cl.show(pnlView, "" + (7));
            }
        });
        
        
            	
        cmdInfo = new JButton("Über SYO");
        pnlNavigation.add(cmdInfo);
        cmdInfo.setVisible(true);
        cmdInfo.setBounds(20, 200, 185, 30);
        cmdInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	cl.show(pnlView, "" + (8));
            }
        });
        
            	
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


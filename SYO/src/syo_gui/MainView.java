package syo_gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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

import syo_controller.DBController;
import syo_model.DBBasisObjekt;
import syo_model.DBTool;

@SuppressWarnings("serial")
public class MainView extends JFrame implements Observer {

	// Listen
	private ArrayList<DBBasisObjekt> liSammlung;
	private ArrayList<DBBasisObjekt> liObjekt;
	private ArrayList<DBBasisObjekt> liTyp;
	private ArrayList<DBBasisObjekt> liFeld;
	// controller
	private DBController ctrl;
	//Aktueller Knoten
	private DBBasisObjekt aktuellerKnoten = null;
	
	private int currentCard = 1;

	private CardLayout cl;

 

	JPanel pnlContent = new JPanel();
	JPanel pnlNavigation = new JPanel();
	private JPanel pnlView;

	// --Namen der verschiedenen Cards --
	final static String SAMMLUNGEN = "Meine Sammlungen";
	final static String OBJEKT = "Objekte der ausgewählten Sammlung";
	final static String TYPERSTELLUNG = "Erstellung eines neuen Typs";
	final static String SAMMLUNGERSTELLUNG = "Erstellung einer neuen Sammlugn";
	final static String OBJEKTERSTELLUNG = "Erstellung eines neuen Objektes";
	final static String FELDERSTELLUNG = "Erstellung eines neuen Feldes";
	final static String VERWALTUNG = "SYO - Verwaltung";
	final static String UBER_SYO = "SYO - scan your object | Info";
	final static String STUFF = "Das ist ein Text";

	// -- Cards des pnlView --
	JPanel pnlCard1;
	JPanel pnlCard2;
	JPanel pnlCard3;
	JPanel pnlCard4;
	JPanel pnlCard5;
	JPanel pnlCard6;
	JPanel pnlCard7;
	JPanel pnlCard8;
	JPanel pnlCard9;

	// -- Navigationspanel --
	Label lblNavigation;
	Label lblNaviStuff;

	JButton cmdSammlung;
	JButton cmdVerwaltung;
	JButton cmdInfo;
	JButton cmdStuff;

	JButton cmdPrevious;
	JButton cmdNext;

	// --------- Komponenten Card1 - Alle Samlungen anzeigen ------
	private JLabel lblCard1;
	private JButton cmdCard1neueSammlung;
	private JScrollPane scrollPaneCard1;
	JList liCard1Sammlungen;

	// --------- Komponenten Card2 - Alle Objekte der ausgewählten Sammlung
	// anzeigen ------

	JLabel lblCard2;
	private JLabel lblCard2Sammlung;
	private JList liCard2Sammlung;
	private JScrollPane scrollPaneCard2;
	private JButton cmdCard2bearbeiten;
	private JButton cmdCard2zurueck;

	// --------- Komponenten Card3 - Ein neuer Typ kann erstellt werden ------
	JLabel lblCard3;
	private JLabel lblCard3Typname;
	private JTextField txtCard3Typname;
	private JList liCard3Felder;
	private String listCard3s[];
	private JList liCard3Sammlung;
	private JScrollPane scrollPaneCard3;
	private JButton cmdCard3Feldhinzufuegen;
	private String listCard3_2s[];
	private JComboBox cmbCard3Feldauswaehlen;
	private JButton cmdCard3NeuesFeld;
	private JButton cmdCard3entfernen;
	private JButton cmdCard3weiter;

	// --------- Komponenten Card4 - Eine neue Sammlung wird erstellt ------
	JLabel lblCard4;
	private JLabel lblCard4Sammlungsname;
	private JButton cmdCard4ok;
	private JButton cmdCard4abbrechen;
	JTextField txtCard4neueSammlung;

	// --------- Komponenten Card5 - Erstellung eines neuen Objekts ------
	JLabel lblCard5;
	private JLabel lblCard5Name;
	private JTextField txtCard5neuesObjekt;
	private JLabel lblCard5VorhandeneTypen;
	private JComboBox cmbCard5Typauswählen;
	private JButton cmdCard5NeuerTyp;
	private JButton cmdCard5weiter;

	// --------- Komponenten Card6 - Erstellung eines neuen Feldes ------
	JLabel lblCard6;
	private JLabel lblCard6Feldname;
	private JTextField txtCard56Feldname;
	private JButton cmdCard6weiter;
	private JButton cmdCard6abbrechen;

	// --------- Komponenten Card7 - Verwaltung der Sache ------
	JLabel lblCard7;
	private JScrollPane scrollPaneCard7;
	private JList liCard7Sammlungen;
	private JButton cmdCard7Sammlunglöschen;

	// --------- Komponenten Card8 - Info über SYO ------
	JLabel lblCard8;
	private JLabel lblCard8Info;

	// --------- Komponenten Card9 - Irgendwelcher Stuff ------
	JLabel lblCard9;

	/**
	 * Konstuktor der Klasse Main View() Die verschiedenen Panels werden
	 * erstellt
	 */
	public MainView() {
		DBTool.getInstance().addObserver(this);
		ctrl = new DBController(this);
		this.setTitle("SYO - Alles ist besser | Unser Motto: Das Beste ist Schlecht genug!!");
		this.setSize(900, 600);
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		/*
		 * ------------------------------------------------------- PANEL - View
		 * - pnlView-------------------------------------------------------
		 */
		cl = new CardLayout();

		pnlView = new JPanel();
		pnlView.setLayout(cl);


		pnlView.setBounds(230, 100, 700, 500);
		pnlView.setBackground(Color.RED);

		// card-Definition
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
		 * ------------------------------------------------------- PANEL -
		 * View-Card1-SAMMLUNGEN - pnlCard1
		 * -------------------------------------------------------
		 */

	
		lblCard1 = new JLabel("<html><bold>" + SAMMLUNGEN + "</bold></html>");
		pnlCard1.add(lblCard1);

		pnlCard1.setBackground(new Color(255, 255, 255));

		lblCard1.setBounds(100, 20, 300, 30);
		lblCard1.setVisible(true);

		cmdCard1neueSammlung = new JButton("neue Sammlung");
		pnlCard1.add(cmdCard1neueSammlung);
		cmdCard1neueSammlung.setVisible(true);
		cmdCard1neueSammlung.setBounds(440, 60, 185, 30);
		cmdCard1neueSammlung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (4));
			}
		});

		DBTool.getInstance().connectDB();
		liSammlung = ctrl.updateSammlung();
		DBTool.getInstance().closeDB();

		liCard1Sammlungen = new JList(liSammlung.toArray());

		liCard1Sammlungen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					liObjekt = ctrl.getObjektOfSammlung((DBBasisObjekt)liCard1Sammlungen
							.getSelectedValue());
					aktuellerKnoten = (DBBasisObjekt)liCard1Sammlungen
					.getSelectedValue();
					liCard2Sammlung.setListData(liObjekt.toArray());
					cl.show(pnlView, "" + (2));
				}
			}
		});

		pnlCard1.add(liCard1Sammlungen);

		scrollPaneCard1 = new JScrollPane(liCard1Sammlungen);
		pnlCard1.add(scrollPaneCard1);
		scrollPaneCard1.setBounds(30, 150, 500, 200);
		scrollPaneCard1.setVisible(true);

		/*
		 * ------------------------------------------------------- PANEL -
		 * View-Card2-OBJEKT - pnlCard2
		 * -------------------------------------------------------
		 */
		lblCard2 = new JLabel(OBJEKT);
		pnlCard2.add(lblCard2);

		pnlCard2.setBackground(new Color(255, 255, 255));

		lblCard2.setBounds(100, 20, 300, 30);
		lblCard2.setVisible(true);
		repaint();

		lblCard2Sammlung = new JLabel("Titel");
		pnlCard2.add(lblCard2Sammlung);
		lblCard2Sammlung.setBounds(30, 90, 300, 30);
		lblCard2Sammlung.setVisible(true);
		repaint();

		liObjekt = new ArrayList<DBBasisObjekt>();

		liCard2Sammlung = new JList(liObjekt.toArray());
		pnlCard2.add(liCard2Sammlung);

		scrollPaneCard2 = new JScrollPane(liCard2Sammlung);
		pnlCard2.add(scrollPaneCard2);
		scrollPaneCard2.setBounds(30, 150, 500, 200);
		scrollPaneCard2.setVisible(true);

		cmdCard2bearbeiten = new JButton("Neu");
		cmdCard2bearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (5));
			}
		});
		
		pnlCard2.add(cmdCard2bearbeiten);
		cmdCard2bearbeiten.setVisible(true);
		cmdCard2bearbeiten.setBounds(440, 60, 185, 30);

		cmdCard2zurueck = new JButton("zurück");
		pnlCard2.add(cmdCard2zurueck);
		cmdCard2zurueck.setVisible(true);
		cmdCard2zurueck.setBounds(440, 110, 185, 30);

		/*
		 * ------------------------------------------------------- PANEL -
		 * View-Card3-TYPERSTELLUNG - pnlCard3
		 * -------------------------------------------------------
		 */

		lblCard3 = new JLabel(TYPERSTELLUNG);
		pnlCard3.add(lblCard3);

		pnlCard3.setBackground(new Color(255, 255, 255));

		lblCard3.setBounds(100, 20, 300, 30);
		lblCard3.setVisible(true);

		lblCard3Typname = new JLabel("Typname:");
		pnlCard3.add(lblCard3Typname);
		lblCard3Typname.setBounds(30, 90, 300, 30);
		lblCard3Typname.setVisible(true);

		txtCard3Typname = new JTextField();
		pnlCard3.add(txtCard3Typname);
		txtCard3Typname.setVisible(true);
		txtCard3Typname.setBounds(100, 90, 185, 30);

		DBTool.getInstance().connectDB();
		liTyp = DBTool.getInstance().selectAllFromTable("Typ");
		DBTool.getInstance().closeDB();

		listCard3s = new String[] { "ListCard3", "Supii", "lala", "weiss ni",
				"42", "jaja", "weiss ni", "42", "jaja", "42", "42", "42", "42",
				"42", "42", "42", "42" };

		liCard3Felder = new JList(listCard3s);
		pnlCard3.add(liCard3Felder);

		liCard3Sammlung = new JList(liTyp.toArray());
		pnlCard2.add(liCard3Sammlung);

		scrollPaneCard3 = new JScrollPane(liCard3Sammlung);
		pnlCard3.add(scrollPaneCard3);
		scrollPaneCard3.setBounds(30, 150, 250, 200);
		scrollPaneCard3.setVisible(true);

		cmdCard3Feldhinzufuegen = new JButton("Feld zum Typ hinzufügen");
		pnlCard3.add(cmdCard3Feldhinzufuegen);
		cmdCard3Feldhinzufuegen.setVisible(true);
		cmdCard3Feldhinzufuegen.setBounds(440, 90, 185, 30);

		listCard3_2s = new String[] { "Feld1", "Feld2", "Feld3", "Feld1", "42",
				"jaja", "weiss ni", "42", "jaja", "42", "42", "42", "42", "42",
				"42", "42", "42" };

		cmbCard3Feldauswaehlen = new JComboBox(listCard3_2s);
		pnlCard3.add(cmbCard3Feldauswaehlen);
		cmbCard3Feldauswaehlen.setVisible(true);
		cmbCard3Feldauswaehlen.setBounds(440, 150, 185, 30);

		cmdCard3NeuesFeld = new JButton("Neues Feld generieren");
		pnlCard3.add(cmdCard3NeuesFeld);
		cmdCard3NeuesFeld.setVisible(true);
		cmdCard3NeuesFeld.setBounds(440, 230, 185, 30);
		cmdCard3NeuesFeld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (6));
			}
		});

		cmdCard3entfernen = new JButton("entfernen");
		pnlCard3.add(cmdCard3entfernen);
		cmdCard3entfernen.setVisible(true);
		cmdCard3entfernen.setBounds(440, 270, 185, 30);

		cmdCard3weiter = new JButton("weiter");
		pnlCard3.add(cmdCard3weiter);
		cmdCard3weiter.setVisible(true);
		cmdCard3weiter.setBounds(440, 310, 185, 30);
		cmdCard3weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (5));
			}
		});
		/*
		 * ------------------------------------------------------- PANEL -
		 * View-Card4-SAMMLUNGERSTELLUNG - pnlCard4
		 * -------------------------------------------------------
		 */
		lblCard4 = new JLabel(SAMMLUNGERSTELLUNG);
		pnlCard4.add(lblCard4);

		pnlCard4.setBackground(new Color(255, 255, 255));

		lblCard4.setBounds(100, 20, 300, 30);
		lblCard4.setVisible(true);

		lblCard4Sammlungsname = new JLabel("Sammlungsname:");
		pnlCard4.add(lblCard4Sammlungsname);
		lblCard4Sammlungsname.setBounds(30, 90, 300, 30);
		lblCard4Sammlungsname.setVisible(true);
		repaint();

		txtCard4neueSammlung = new JTextField();
		pnlCard4.add(txtCard4neueSammlung);
		txtCard4neueSammlung.setVisible(true);
		txtCard4neueSammlung.setBounds(140, 90, 185, 30);

		cmdCard4ok = new JButton("ok");
		pnlCard4.add(cmdCard4ok);
		cmdCard4ok.setVisible(true);
		cmdCard4ok.setBounds(440, 60, 185, 30);
		cmdCard4ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.createSammlung(txtCard4neueSammlung.getText());
				cl.show(pnlView, "" + (1));
			}
		});

		cmdCard4abbrechen = new JButton("abbrechen");
		pnlCard4.add(cmdCard4abbrechen);
		cmdCard4abbrechen.setVisible(true);
		cmdCard4abbrechen.setBounds(440, 110, 185, 30);
		cmdCard4abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (1));
			}
		});

		/*
		 * ------------------------------------------------------- PANEL -
		 * View-Card5-OBJEKTERSTELLUNG - pnlCard5
		 * -------------------------------------------------------
		 */

		lblCard5 = new JLabel(OBJEKTERSTELLUNG);
		pnlCard5.add(lblCard5);

		pnlCard5.setBackground(new Color(255, 255, 255));

		lblCard5.setBounds(100, 20, 200, 30);
		lblCard5.setVisible(true);

		lblCard5Name = new JLabel("Name:");
		pnlCard5.add(lblCard5Name);
		lblCard5Name.setBounds(30, 90, 300, 30);
		lblCard5Name.setVisible(true);
		repaint();

		txtCard5neuesObjekt = new JTextField();
		pnlCard5.add(txtCard5neuesObjekt);
		txtCard5neuesObjekt.setVisible(true);
		txtCard5neuesObjekt.setBounds(100, 90, 185, 30);

		lblCard5VorhandeneTypen = new JLabel("Typ: ");
		pnlCard5.add(lblCard5VorhandeneTypen);
		lblCard5VorhandeneTypen.setBounds(30, 150, 60, 30);
		lblCard5VorhandeneTypen.setVisible(true);

		liTyp = ctrl.getEveryRowOfTable("typ");

		cmbCard5Typauswählen = new JComboBox(liTyp.toArray());
		pnlCard5.add(cmbCard5Typauswählen);
		cmbCard5Typauswählen.setVisible(true);
		cmbCard5Typauswählen.setBounds(30, 190, 235, 30);

		cmdCard5NeuerTyp = new JButton("Neuer Typ");
		pnlCard5.add(cmdCard5NeuerTyp);
		cmdCard5NeuerTyp.setVisible(true);
		cmdCard5NeuerTyp.setBounds(440, 60, 185, 30);
		cmdCard5NeuerTyp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (3));
			}
		});

		cmdCard5weiter = new JButton("Ok");
		pnlCard5.add(cmdCard5weiter);
		cmdCard5weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (2));
				if (cmbCard5Typauswählen.getSelectedItem() != null) {
					String name = txtCard5neuesObjekt.getText();
					int typID = ((DBBasisObjekt)cmbCard5Typauswählen.getSelectedItem()).getId();
					txtCard5neuesObjekt.setText("");
					ctrl.createObjekt(name, typID, aktuellerKnoten.getId());
				}
			}
		});
		cmdCard5weiter.setVisible(true);
		cmdCard5weiter.setBounds(440, 110, 185, 30);

		/*
		 * ------------------------------------------------------- PANEL -
		 * View-Card6-FELDERSTELLUNG pnl Card6
		 * -------------------------------------------------------
		 */

		lblCard6 = new JLabel(FELDERSTELLUNG);
		pnlCard6.add(lblCard6);

		pnlCard6.setBackground(new Color(255, 255, 255));

		lblCard6.setBounds(100, 20, 300, 30);
		lblCard6.setVisible(true);

		lblCard6Feldname = new JLabel("Feldname:");
		pnlCard6.add(lblCard6Feldname);
		lblCard6Feldname.setBounds(30, 90, 300, 30);
		lblCard6Feldname.setVisible(true);
		repaint();

		txtCard56Feldname = new JTextField();
		pnlCard6.add(txtCard56Feldname);
		txtCard56Feldname.setVisible(true);
		txtCard56Feldname.setBounds(100, 90, 185, 30);

		cmdCard6weiter = new JButton("weiter");
		pnlCard6.add(cmdCard6weiter);
		cmdCard6weiter.setVisible(true);
		cmdCard6weiter.setBounds(440, 60, 185, 30);
		cmdCard6weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (3));
			}
		});

		cmdCard6abbrechen = new JButton("abbrechen");
		pnlCard6.add(cmdCard6abbrechen);
		cmdCard6abbrechen.setVisible(true);
		cmdCard6abbrechen.setBounds(440, 110, 185, 30);
		cmdCard6abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (3));
			}
		});

		/*
		 * ------------------------------------------------------- PANEL -
		 * View-Card7-VERWALTUNG - pnlCard7
		 * -------------------------------------------------------
		 */
		lblCard7 = new JLabel(VERWALTUNG);
		pnlCard7.add(lblCard7);

		pnlCard7.setBackground(new Color(255, 255, 255));

		lblCard7.setBounds(100, 20, 220, 30);
		lblCard7.setVisible(true);

		liCard7Sammlungen = new JList(liSammlung.toArray());
		pnlCard7.add(liCard7Sammlungen);

		scrollPaneCard7 = new JScrollPane(liCard7Sammlungen);
		pnlCard7.add(scrollPaneCard7);
		scrollPaneCard7.setBounds(30, 150, 500, 200);
		scrollPaneCard7.setVisible(true);

		cmdCard7Sammlunglöschen = new JButton("Sammlung löschen");
		cmdCard7Sammlunglöschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (liCard7Sammlungen.getSelectedIndex() > -1)
					ctrl.deleteSammlung(((DBBasisObjekt) liCard7Sammlungen
							.getSelectedValue()).getId());
			}
		});

		pnlCard7.add(cmdCard7Sammlunglöschen);
		cmdCard7Sammlunglöschen.setVisible(true);
		cmdCard7Sammlunglöschen.setBounds(440, 60, 185, 30);

		/*
		 * ------------------------------------------------------- PANEL -
		 * View-Card8-Über SYO - pnlCard8
		 * -------------------------------------------------------
		 */
		lblCard8 = new JLabel(UBER_SYO);
		pnlCard8.add(lblCard8);

		pnlCard8.setBackground(new Color(255, 255, 255));

		lblCard8.setBounds(100, 20, 200, 30);
		lblCard8.setVisible(true);

		lblCard8Info = new JLabel(
				"<html>Die Bedienung von SYO ist nicht einfach.<br>Für Fragen, Probleme, "
						+ "Komplikationen wenden Sie sich bitte an eine kompetente Fachperson.<br>"
						+ "Falls diese Ihnen nicht weiterhelfen kann, so melden Sie sich bei der <br>"
						+ "Konkurenz und Sie werden feststellen, dass Ihnen dort noch weniger geholfen <br>"
						+ "werden kann.<br><br>"
						+ "SYO dankt für Ihre Treue<br><br><br>"
						+ "Die Software SYO - scan your objects ist frei erhältlich und wird unter GPL gehandelt.<br>"
						+ "Jegliche komperzielle Änderungen oder kostenpflichtige Veröffentlichungen können strafrechtlich Verfolgt werden.<br><br>"
						+ "SYO hält sich vor, Hot Dogs zu verkaufen!</html>");
		pnlCard8.add(lblCard8Info);
		lblCard8Info.setBounds(70, 100, 500, 250);
		lblCard8Info.setVisible(true);

		/*
		 * ------------------------------------------------------- PANEL -
		 * View-Card9-Stuff - pnlCard9
		 * -------------------------------------------------------
		 */
		lblCard9 = new JLabel(STUFF);
		pnlCard9.add(lblCard9);

		pnlCard9.setBackground(new Color(255, 255, 255));

		lblCard9.setBounds(100, 20, 120, 30);
		lblCard9.setVisible(true);

		/*
		 * ------------------------------------------------------- PANEL -
		 * NAVIGATION - pnlNavigation
		 * -------------------------------------------------------
		 */
		pnlNavigation.setLayout(null);
		pnlNavigation.setBounds(0, 100, 225, 500);
		pnlNavigation.setBackground(Color.WHITE);

		lblNavigation = new Label();
		pnlNavigation.add(lblNavigation);
		lblNavigation.setBackground(new Color(222, 222, 222));
		lblNavigation.setForeground(new Color(0, 0, 0));
		lblNavigation.setAlignment(1);
		lblNavigation.setBounds(31, 10, 156, 30);
		lblNavigation.setFont(new Font((lblNavigation.getFont()).getFontName(),
				(lblNavigation.getFont()).getStyle(), 16));
		lblNavigation.setText("Übersicht");
		Font f = lblNavigation.getFont();
		lblNavigation.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));

		lblNaviStuff = new Label();
		pnlNavigation.add(lblNaviStuff);
		lblNaviStuff.setBackground(new Color(255, 255, 255));
		lblNaviStuff.setForeground(new Color(0, 0, 0));
		lblNaviStuff.setAlignment(1);
		lblNaviStuff.setBounds(10, 420, 205, 30);
		lblNaviStuff.setFont(new Font((lblNavigation.getFont()).getFontName(),
				(lblNavigation.getFont()).getStyle(), 10));
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

		// Previous & Next - Buttons / Only for Testing
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
		pnlContent.setSize(900, 600);
		pnlContent.add(new WhitePanel());
		pnlContent.setBackground(new Color(222, 222, 222));
		pnlContent.add(pnlNavigation);
		pnlContent.add(pnlView);

		getContentPane().add(pnlContent);
		DBTool.getInstance().closeDB();
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Update");
		// Update Sammlung
		liSammlung = ctrl.updateSammlung();
		liCard1Sammlungen.setListData(liSammlung.toArray());
		liCard7Sammlungen.setListData(liSammlung.toArray());
		this.txtCard4neueSammlung.setText(""); // Clear the text
		// Update object
		
		// Update typ

		// Update feld

	}
}

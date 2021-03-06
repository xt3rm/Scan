package syo_gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.avalon.framework.configuration.ConfigurationException;
import org.krysalis.barcode4j.BarcodeException;

import syo_controller.BarcodeGen;
import syo_controller.DBBasisObjekt;
import syo_controller.DBController;
import syo_model.DBTool;

/**
 * Main GUI class
 * 
 * @author ebrogt, ebeckm, estedt
 * 
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements Observer {

	// Listen
	private ArrayList<DBBasisObjekt> liSammlung;
	private ArrayList<DBBasisObjekt> liObjekt;
	private ArrayList<DBBasisObjekt> liTyp;
	private ArrayList<DBBasisObjekt> liFeld;
	private ArrayList<DBBasisObjekt> liChosenFeld;
	// controller
	private DBController ctrl;
	// Aktueller Knoten
	private DBBasisObjekt aktuellerKnoten = null;
	// Wird gerade etwas bearbeitet?
	private boolean isEditing = false;
	// Ein eventueller Barcode wird hier gespeichert
	private String barcode = null;
	// Filechooser f�r backup
	private CardLayout cl;

	JPanel pnlContent = new JPanel();
	JPanel pnlNavigation = new JPanel();
	private JPanel pnlView;

	// --Namen der verschiedenen Cards --
	final static String SAMMLUNGEN = "Meine Sammlungen";
	final static String OBJEKT = "Objekte der ausgew�hlten Sammlung";
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
	JLabel lblMeldung;

	JTextField txtBarcode;

	JButton cmdSammlung;
	JButton cmdVerwaltung;
	JButton cmdInfo;
	JButton cmdBackup;

	JButton cmdPrevious;
	JButton cmdNext;
	private int killerbutton;

	// --------- Komponenten Card1 - Alle Samlungen anzeigen ------
	private JLabel lblCard1;
	private JButton cmdCard1neueSammlung;
	private JButton cmdCard1Sammlungloeschen;
	private JScrollPane scrollPaneCard1;
	JList liCard1Sammlungen;

	// --------- Komponenten Card2 - Alle Objekte der ausgew�hlten Sammlung
	// anzeigen ------

	JLabel lblCard2;
	private JLabel lblCard2Sammlung;
	private JList liCard2Sammlung;
	private JScrollPane scrollPaneCard2;
	private JButton cmdCard2bearbeiten;
	private JButton cmdCard2zurueck;
	private JButton cmdCard2Objektloeschen;

	// --------- Komponenten Card3 - Ein neuer Typ kann erstellt werden ------
	JLabel lblCard3;
	private JLabel lblCard3Typname;
	private JTextField txtCard3Typname;
	private JList liCard3Felder;
	private String listCard3s[];
	private JList liCard3Sammlung;
	private JScrollPane scrollPaneCard3;
	private JButton cmdCard3Feldhinzufuegen;
	private JComboBox cmbCard3Feldauswaehlen;
	private JButton cmdCard3NeuesFeld;
	private JButton cmdCard3entfernen;
	private JButton cmdCard3weiter;
	private JButton cmdCard3zurueck;

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
	private JComboBox cmbCard5Typausw�hlen;
	private JButton cmdCard5NeuerTyp;
	private JButton cmdCard5weiter;
	private JButton cmdCard5zurueck;

	// --------- Komponenten Card6 - Erstellung eines neuen Feldes ------
	JLabel lblCard6;
	private JLabel lblCard6Feldname;
	private JTextField txtCard56Feldname;
	private JButton cmdCard6weiter;
	private JButton cmdCard6abbrechen;

	// --------- Komponenten Card8 - Info �ber SYO ------
	JLabel lblCard8;
	private JLabel lblCard8Info;

	// --------- Komponenten Card9 - Irgendwelcher Stuff ------

	private JLabel lblCard9;
	private JButton cmdCard9speichern;
	private JButton cmdCard9abbrechen;
	private JButton cmdCard9drucken;
	// private JList liCard9Sammlung;
	private JScrollPane scrollPaneCard9;
	private JTable liCard9Sammlungen;

	/**
	 * Konstruktor der Klasse Main View() Die verschiedenen Panels werden
	 * erstellt
	 */
	public MainView() {
		DBTool.getInstance().addObserver(this);
		this.setTitle("SYO - Scan Your Objects");
		this.setSize(900, 600);
		this.setResizable(false);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ctrl = new DBController();

		/*
		 * ------------------------------------------------------- PANEL - View
		 * - pnlView -------------------------------------------------------
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

		createPnlCard1();
		createPnlCard2();
		createPnlCard3();
		createPnlCard4();
		createPnlCard5();
		createPnlCard6();
		createPnlCard8();
		createPnlCard9();
		createNavigation();

		pnlContent.setLayout(null);
		pnlContent.setSize(900, 600);
		pnlContent.add(new WhitePanel());
		pnlContent.setBackground(new Color(222, 222, 222));
		pnlContent.add(pnlNavigation);
		pnlContent.add(pnlView);

		getContentPane().add(pnlContent);
		DBTool.getInstance().closeDB();
		txtBarcode.requestFocusInWindow();
	}

	/**
	 * Creates PanelCard 1
	 */
	public void createPnlCard1() {
		lblCard1 = new JLabel("<html><bold>" + SAMMLUNGEN + "</bold></html>");
		pnlCard1.add(lblCard1);

		pnlCard1.setBackground(new Color(255, 255, 255));

		lblCard1.setBounds(100, 20, 300, 30);
		lblCard1.setVisible(true);

		cmdCard1neueSammlung = new JButton("neue Sammlung");
		pnlCard1.add(cmdCard1neueSammlung);
		cmdCard1neueSammlung.setVisible(true);
		cmdCard1neueSammlung.setBounds(40, 340, 185, 30);
		cmdCard1neueSammlung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isEditing = true;
				cl.show(pnlView, "" + (4));
			}

		});

		liSammlung = ctrl.getSammlung();

		liCard1Sammlungen = new JList(liSammlung.toArray());

		liCard1Sammlungen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					liObjekt = ctrl
							.getBaseObjektOfSammlung((DBBasisObjekt) liCard1Sammlungen
									.getSelectedValue());

					aktuellerKnoten = (DBBasisObjekt) liCard1Sammlungen
							.getSelectedValue();
					liCard2Sammlung.setListData(liObjekt.toArray());
					lblCard2Sammlung.setText(aktuellerKnoten.getName());
					cl.show(pnlView, "" + (2));
				}
			}
		});

		cmdCard1Sammlungloeschen = new JButton("Sammlung l�schen");
		pnlCard1.add(cmdCard1Sammlungloeschen);
		cmdCard1Sammlungloeschen.setVisible(true);
		cmdCard1Sammlungloeschen.setBounds(40, 390, 185, 30);
		cmdCard1Sammlungloeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (liCard1Sammlungen.getSelectedIndex() > -1)
					ctrl.deleteSammlung(((DBBasisObjekt) liCard1Sammlungen
							.getSelectedValue()).getId());
			}
		});

		pnlCard1.add(liCard1Sammlungen);
		scrollPaneCard1 = new JScrollPane(liCard1Sammlungen);
		pnlCard1.add(scrollPaneCard1);
		scrollPaneCard1.setBounds(30, 60, 600, 260);
		scrollPaneCard1.setVisible(true);
	}

	/**
	 * Creates PanelCard 9
	 */
	public void createPnlCard9() {
		lblCard9 = new JLabel("");
		pnlCard9.add(lblCard9);

		cmdCard9speichern = new JButton("Speichern");
		pnlCard9.add(cmdCard9speichern);
		cmdCard9speichern.setVisible(true);
		cmdCard9speichern.setBounds(440, 110, 185, 30);
		cmdCard9speichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.updateObjekt(aktuellerKnoten);
				lblMeldung.setText("<html><b>Daten eingetragen</b></html>");
			}
		});

		cmdCard9abbrechen = new JButton("Zur�ck");
		pnlCard9.add(cmdCard9abbrechen);
		cmdCard9abbrechen.setVisible(true);
		cmdCard9abbrechen.setBounds(40, 420, 185, 30);
		cmdCard9abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Update object
				if (aktuellerKnoten.getParent() != null) {
					liObjekt = ctrl.getBaseObjektOfSammlung(aktuellerKnoten
							.getParent());
					liCard2Sammlung.setListData(liObjekt.toArray());
					txtCard5neuesObjekt.setText("");
					lblMeldung.setText("<html><b> </b></html>");
					cl.show(pnlView, "" + (2));
				}
			}
		});

		liCard9Sammlungen = new JTable();
		scrollPaneCard9 = new JScrollPane(liCard9Sammlungen);
		pnlCard9.add(scrollPaneCard9);
		scrollPaneCard9.setBounds(30, 70, 350, 250);

		pnlCard9.setBackground(new Color(255, 255, 255));

		lblCard9.setBounds(100, 20, 120, 30);
		lblCard9.setVisible(true);

		cmdCard9drucken = new JButton("Drucken");
		pnlCard9.add(cmdCard9drucken);
		cmdCard9drucken.setVisible(true);

		cmdCard9drucken.setBounds(440, 190, 185, 30);
		cmdCard9drucken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new BarcodeGen(ctrl.getBarcodeOfObject(aktuellerKnoten), aktuellerKnoten);
				} catch (ConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BarcodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Creates PanelCard 2
	 */
	public void createPnlCard2() {
		lblCard2 = new JLabel(OBJEKT);
		pnlCard2.add(lblCard2);

		pnlCard2.setBackground(new Color(255, 255, 255));

		lblCard2.setBounds(100, 20, 300, 30);
		lblCard2.setVisible(true);
		repaint();

		lblCard2Sammlung = new JLabel("");
		pnlCard2.add(lblCard2Sammlung);
		lblCard2Sammlung.setBounds(30, 60, 300, 30);
		lblCard2Sammlung.setVisible(true);
		repaint();

		liObjekt = new ArrayList<DBBasisObjekt>();

		liCard2Sammlung = new JList(liObjekt.toArray());
		pnlCard2.add(liCard2Sammlung);

		scrollPaneCard2 = new JScrollPane(liCard2Sammlung);
		pnlCard2.add(scrollPaneCard2);
		scrollPaneCard2.setBounds(30, 100, 600, 250);
		scrollPaneCard2.setVisible(true);

		cmdCard2bearbeiten = new JButton("Neu");
		cmdCard2bearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isEditing = true;
				cl.show(pnlView, "" + (5));
			}
		});

		pnlCard2.add(cmdCard2bearbeiten);
		cmdCard2bearbeiten.setVisible(true);
		cmdCard2bearbeiten.setBounds(40, 370, 185, 30);

		cmdCard2zurueck = new JButton("zur�ck");
		pnlCard2.add(cmdCard2zurueck);
		cmdCard2zurueck.setVisible(true);
		cmdCard2zurueck.setBounds(440, 110, 185, 30);

		liCard2Sammlung.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						DBBasisObjekt tmp = aktuellerKnoten;
						aktuellerKnoten = ctrl
								.getWholeObjekt((DBBasisObjekt) liCard2Sammlung
										.getSelectedValue());
						aktuellerKnoten.setParent(tmp);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					liCard9Sammlungen.setModel(new MyTableModel(aktuellerKnoten
							.getChildren()));
					lblCard9.setText(aktuellerKnoten.getName());
					cl.show(pnlView, "" + (9));
				}
			}
		});
		cmdCard2zurueck.setBounds(40, 420, 185, 30);
		cmdCard2zurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isEditing = false;
				cl.show(pnlView, "" + (1));
				txtBarcode.requestFocus();

			}
		});

		cmdCard2Objektloeschen = new JButton("Objekt l�schen");
		pnlCard2.add(cmdCard2Objektloeschen);
		cmdCard2Objektloeschen.setVisible(true);
		cmdCard2Objektloeschen.setBounds(240, 370, 185, 30);
		cmdCard2Objektloeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (liCard2Sammlung.getSelectedIndex() > -1) {
					ctrl.deleteObjekt(((DBBasisObjekt) liCard2Sammlung
							.getSelectedValue()).getId());
				}
			}
		});

	}

	/**
	 * Creates PanelCard 3 TYPERSTELLUNG
	 */
	public void createPnlCard3() {
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

		// Get the Felder
		liFeld = ctrl.getEveryRowOfTable("feld");

		liCard3Felder = new JList(listCard3s);
		pnlCard3.add(liCard3Felder);

		liChosenFeld = new ArrayList<DBBasisObjekt>();
		liCard3Sammlung = new JList(liChosenFeld.toArray());
		pnlCard2.add(liCard3Sammlung);

		scrollPaneCard3 = new JScrollPane(liCard3Sammlung);
		pnlCard3.add(scrollPaneCard3);
		scrollPaneCard3.setBounds(30, 150, 250, 200);
		scrollPaneCard3.setVisible(true);

		cmbCard3Feldauswaehlen = new JComboBox(liFeld.toArray());
		pnlCard3.add(cmbCard3Feldauswaehlen);
		cmbCard3Feldauswaehlen.setVisible(true);
		cmbCard3Feldauswaehlen.setBounds(440, 140, 185, 30);

		cmdCard3NeuesFeld = new JButton("Neues Feld generieren");
		pnlCard3.add(cmdCard3NeuesFeld);
		cmdCard3NeuesFeld.setVisible(true);
		cmdCard3NeuesFeld.setBounds(440, 240, 185, 30);
		cmdCard3NeuesFeld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (6));
			}
		});

		cmdCard3Feldhinzufuegen = new JButton("Feld zum Typ hinzuf�gen");
		pnlCard3.add(cmdCard3Feldhinzufuegen);
		cmdCard3Feldhinzufuegen.setVisible(true);
		cmdCard3Feldhinzufuegen.setBounds(440, 90, 185, 30);
		cmdCard3Feldhinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!liChosenFeld.contains(cmbCard3Feldauswaehlen
						.getSelectedItem())) {
					liChosenFeld.add((DBBasisObjekt) cmbCard3Feldauswaehlen
							.getSelectedItem());
					liCard3Sammlung.setListData(liChosenFeld.toArray());
				}
			}
		});

		cmdCard3entfernen = new JButton("entfernen");
		pnlCard3.add(cmdCard3entfernen);
		cmdCard3entfernen.setVisible(true);
		cmdCard3entfernen.setBounds(440, 190, 185, 30);
		cmdCard3entfernen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				liChosenFeld.remove((DBBasisObjekt) liCard3Sammlung
						.getSelectedValue());
				liCard3Sammlung.setListData(liChosenFeld.toArray());
			}
		});

		cmdCard3weiter = new JButton("Ok");
		pnlCard3.add(cmdCard3weiter);
		cmdCard3weiter.setVisible(true);
		cmdCard3weiter.setBounds(440, 420, 185, 30);
		cmdCard3weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isFilledOut(txtCard3Typname) && !liChosenFeld.isEmpty()) {
					ctrl.createTyp(txtCard3Typname.getText(), liChosenFeld);
					txtCard3Typname.setText("");
					cl.show(pnlView, "" + (5));
				}
			}
		});

		cmdCard3zurueck = new JButton("zur�ck");
		pnlCard3.add(cmdCard3zurueck);
		cmdCard3zurueck.setVisible(true);
		cmdCard3zurueck.setBounds(40, 420, 185, 30);
		cmdCard3zurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (5));
			}
		});
	}

	/**
	 * Creates PanelCard 4
	 */
	public void createPnlCard4() {
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

		cmdCard4ok = new JButton("Erstellen");
		pnlCard4.add(cmdCard4ok);
		cmdCard4ok.setVisible(true);
		cmdCard4ok.setBounds(440, 420, 185, 30);
		cmdCard4ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isEditing = false;
				ctrl.createSammlung(txtCard4neueSammlung.getText());
				cl.show(pnlView, "" + (1));
			}
		});

		cmdCard4abbrechen = new JButton("Zur�ck");
		pnlCard4.add(cmdCard4abbrechen);
		cmdCard4abbrechen.setVisible(true);
		cmdCard4abbrechen.setBounds(40, 420, 185, 30);
		cmdCard4abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isEditing = false;
				cl.show(pnlView, "" + (1));
			}
		});
	}

	/**
	 * Creates PanelCard 5
	 */
	public void createPnlCard5() {
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

		cmbCard5Typausw�hlen = new JComboBox(liTyp.toArray());
		pnlCard5.add(cmbCard5Typausw�hlen);
		cmbCard5Typausw�hlen.setVisible(true);
		cmbCard5Typausw�hlen.setBounds(30, 190, 235, 30);

		cmdCard5NeuerTyp = new JButton("Neuer Typ");
		pnlCard5.add(cmdCard5NeuerTyp);
		cmdCard5NeuerTyp.setVisible(true);
		cmdCard5NeuerTyp.setBounds(440, 90, 185, 30);
		cmdCard5NeuerTyp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (3));
			}
		});

		cmdCard5weiter = new JButton("Erstellen");
		pnlCard5.add(cmdCard5weiter);
		cmdCard5weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (cmbCard5Typausw�hlen.getSelectedItem() != null) {
					String name = txtCard5neuesObjekt.getText();
					int typID = ((DBBasisObjekt) cmbCard5Typausw�hlen
							.getSelectedItem()).getId();
					if (isFilledOut(txtCard5neuesObjekt)) {
						isEditing = false;

						txtCard5neuesObjekt.setText("");
						if (barcode == null) {
							ctrl.createObjekt(name, typID,
									aktuellerKnoten.getId());
						} else {
							ctrl.createObjekt(name, typID,
									aktuellerKnoten.getId(), barcode);
						}
						cl.show(pnlView, "" + (2));
						barcode = null;
					}
				}
			}
		});
		cmdCard5weiter.setVisible(true);
		cmdCard5weiter.setBounds(440, 420, 185, 30);

		cmdCard5zurueck = new JButton("zur�ck");
		pnlCard5.add(cmdCard5zurueck);
		cmdCard5zurueck.setVisible(true);
		cmdCard5zurueck.setBounds(40, 420, 185, 30);
		cmdCard5zurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isEditing = false;
				cl.show(pnlView, "" + (2));
			}
		});

	}

	/**
	 * Creates PanelCard 6
	 * 
	 */
	public void createPnlCard6() {
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

		cmdCard6weiter = new JButton("ok");
		pnlCard6.add(cmdCard6weiter);
		cmdCard6weiter.setVisible(true);
		cmdCard6weiter.setBounds(440, 420, 185, 30);
		cmdCard6weiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.createFeld(txtCard56Feldname.getText());
				cl.show(pnlView, "" + (3));
			}
		});

		cmdCard6abbrechen = new JButton("zur�ck");
		pnlCard6.add(cmdCard6abbrechen);
		cmdCard6abbrechen.setVisible(true);
		cmdCard6abbrechen.setBounds(40, 420, 185, 30);
		cmdCard6abbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (3));
			}
		});
	}

	/**
	 * Creates PanelCard 8
	 */
	public void createPnlCard8() {
		lblCard8 = new JLabel(UBER_SYO);
		pnlCard8.add(lblCard8);

		pnlCard8.setBackground(new Color(255, 255, 255));

		lblCard8.setBounds(100, 20, 200, 30);
		lblCard8.setVisible(true);

		lblCard8Info = new JLabel(
				"<html>Die Bedienung von SYO ist nicht einfach.<br>F�r Fragen, Probleme, "
						+ "Komplikationen wenden Sie sich bitte an eine kompetente Fachperson.<br>"
						+ "Falls diese Ihnen nicht weiterhelfen kann, so melden Sie sich bei der <br>"
						+ "Konkurenz und Sie werden feststellen, dass Ihnen dort noch weniger geholfen <br>"
						+ "werden kann.<br><br>"
						+ "SYO dankt f�r Ihre Treue<br><br><br>"
						+ "Die Software SYO - scan your objects ist frei erh�ltlich und wird unter GPL gehandelt.<br>"
						+ "Jegliche komperzielle �nderungen oder kostenpflichtige Ver�ffentlichungen k�nnen strafrechtlich Verfolgt werden.<br><br>"
						+ "SYO h�lt sich vor, Hot Dogs zu verkaufen!</html>");
		pnlCard8.add(lblCard8Info);
		lblCard8Info.setBounds(70, 100, 500, 250);
		lblCard8Info.setVisible(true);
	}

	/**
	 * Creates the Navigation
	 */
	public void createNavigation() {

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
		lblNavigation.setText("�bersicht");
		Font f = lblNavigation.getFont();
		lblNavigation.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));

		// Barcodefeld
		txtBarcode = new JTextField();
		pnlNavigation.add(txtBarcode);
		txtBarcode.setBounds(20, 420, 185, 30);
		txtBarcode.setVisible(true);
		txtBarcode.setFont(new Font("Arial", Font.PLAIN, 15));

		/**
		 * 
		 * This is the very important Scannerfield!
		 * 
		 */
		txtBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isEditing) {
					try {
						aktuellerKnoten = ctrl.getObjectOfBarcode(txtBarcode
								.getText());
						lblCard9.setText(aktuellerKnoten.getName());
						liCard9Sammlungen.setModel(new MyTableModel(
								aktuellerKnoten.getChildren()));
						cl.show(pnlView, "" + (9));
						lblMeldung
								.setText("<html><b>Barcode erkannt</b></html>");
						txtBarcode.setText("");
					} catch (Exception e) {
						lblMeldung.setText("<html><b> " + e.getMessage()
								+ " </b></html>");
						if (JOptionPane.showConfirmDialog(null,
								"Neuen eintrag erfassen?", "Nicht gefunden!",
								JOptionPane.YES_NO_OPTION) == 0) {
							barcode = txtBarcode.getText();
							cl.show(pnlView, "" + (1));
						}
					}
				} else {
					lblMeldung
							.setText("<html><b>Bitte zuerst den aktuellen Bearbeitungsschritt abschliessen!</b></html>");
				}
			}
		});

		cmdSammlung = new JButton("Sammlungen anzeigen");
		pnlNavigation.add(cmdSammlung);
		cmdSammlung.setVisible(true);
		cmdSammlung.setBounds(20, 70, 185, 30);
		cmdSammlung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				barcode = null;
				isEditing = false;
				txtBarcode.requestFocus();
				cl.show(pnlView, "" + (1));
				lblMeldung.setText("<html><b> </b></html>");
			}
		});

		cmdVerwaltung = new JButton("Change Style");
		pnlNavigation.add(cmdVerwaltung);
		cmdVerwaltung.setVisible(true);
		cmdVerwaltung.setBounds(20, 120, 185, 30);
		cmdVerwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (killerbutton == 0) {
					pnlContent.setBackground(Color.BLACK);
					pnlNavigation.setBackground(Color.BLUE);
					cl.show(pnlView, "" + (1));
					pnlCard1.setBackground(Color.RED);
					killerbutton++;
				} else if (killerbutton == 1) {
					pnlContent.setBackground(Color.BLACK);
					pnlNavigation.setBackground(Color.BLACK);
					cl.show(pnlView, "" + (1));
					pnlCard1.setBackground(Color.YELLOW);
					killerbutton++;
				} else if (killerbutton == 2) {
					pnlContent.setBackground(Color.BLACK);
					pnlNavigation.setBackground(new Color(255, 192, 203));
					cl.show(pnlView, "" + (1));
					pnlCard1.setBackground(new Color(255, 192, 203));
					killerbutton++;
				} else if (killerbutton == 3) {
					pnlContent.setBackground(Color.BLACK);
					pnlNavigation.setBackground(new Color(238, 212, 130));
					cl.show(pnlView, "" + (1));
					pnlCard1.setBackground(new Color(238, 212, 130));
					killerbutton++;
				} else {
					pnlContent.setBackground(new Color(222, 222, 222));
					pnlNavigation.setBackground(Color.WHITE);
					cl.show(pnlView, "" + (1));
					pnlCard1.setBackground(Color.WHITE);
					killerbutton = 0;
				}
			}
		});

		cmdInfo = new JButton("�ber SYO");
		pnlNavigation.add(cmdInfo);
		cmdInfo.setVisible(true);
		cmdInfo.setBounds(20, 170, 185, 30);
		cmdInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(pnlView, "" + (8));
			}
		});

		lblMeldung = new JLabel("<html><b> </b></html>");
		pnlNavigation.add(lblMeldung);
		lblMeldung.setBackground(Color.white);
		lblMeldung.setForeground(Color.red);
		lblMeldung.setBounds(20, 250, 185, 100);
		lblMeldung.setFont(new Font("Arial", Font.PLAIN, 14));
	}

	/**
	 * Updates the GUI
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// Update sammlung
		liSammlung = ctrl.getSammlung();
		liCard1Sammlungen.setListData(liSammlung.toArray());
		this.txtCard4neueSammlung.setText(""); // Clear the text
		// Update object
		if (aktuellerKnoten != null) {
			this.liObjekt = ctrl.getBaseObjektOfSammlung(aktuellerKnoten);
			this.liCard2Sammlung.setListData(liObjekt.toArray());
			this.txtCard5neuesObjekt.setText("");
		}
		// Update typ
		this.liTyp = ctrl.getEveryRowOfTable("typ");
		this.cmbCard5Typausw�hlen.removeAllItems();
		for (DBBasisObjekt dbt : liTyp) {
			cmbCard5Typausw�hlen.addItem(dbt);
		}
		// Update feld
		this.liFeld = ctrl.getEveryRowOfTable("feld");
		cmbCard3Feldauswaehlen.removeAllItems();
		for (DBBasisObjekt dbo : liFeld) {
			cmbCard3Feldauswaehlen.addItem(dbo);

			txtBarcode.requestFocusInWindow();
		}
	}

	private Boolean isFilledOut(JTextField field) {
		return !field.getText().equals("");
	}

}

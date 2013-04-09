package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import main.App;

public class Fenetre extends JFrame implements ActionListener{
	public JComboBox list;
	public JTextArea tableauRes= new JTextArea();//tableau qui contiendra les resultats des requettes des reponses
	public JScrollPane scroll;
	public JTextArea textreq; 
	public JTextArea tableauRes2=new JTextArea();//tableau qui contiendra les resultats des requettes personnalisees
	public JScrollPane scroll2;
	public JButton executer;
	public App model = new App();
	public JPanel tab1,tab2;
	public Fenetre(){
		super("Application Projet Jena , Partie 1");
		this.setBounds(200 ,80 ,800, 800);
		JTabbedPane onglets = new JTabbedPane();
		/*
		 * Le premier onglet : va contenir les requettes demander 
		 */
		tab1 = new JPanel();
		tab1.setLayout(null);
		Vector<String> questions = new Vector<String>();
		questions.add("Q 1: Tous codes venant typer les features, leurs definition, leur label en anglais et leur classe d’appartenance ");
		questions.add("Q 2: Lister pour Vegetation les codes associes et leur definition");
		questions.add("Q 3: Pour chaque classes geonames , le nombre de type de features");
		questions.add("Q 4: Les codes et definition ayant ice dans leur definition");
		questions.add("Q 5: A partir de quelques features , une requette avec un graphe sous forme de molecule");
		questions.add("Q 6: Requette dont le graphe associe comprend une jointure S-P");
		list = new JComboBox(questions);
		tab1.add(list);
		scroll = new JScrollPane (tableauRes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBackground(Color.WHITE);
		scroll.setBounds(40,150, 650, 600);
		tab1.add(scroll);
		list.setBounds(40,80, 650, 50);
		list.addActionListener(this);
		onglets.addTab("Requettes questions", tab1);
		/*
		 * Onglet 2: va contenir une petite interface des requettes ;
		 */
		tab2 = new JPanel();
		JLabel name2 = new JLabel("Veuillez entrez votre Requette :");
		name2.setBounds(10, 10, 260, 20);
		JLabel name3 = new JLabel("Resultats :");
		name3.setBounds(10, 145, 260, 20);
		tab2.setLayout(null);
		textreq = new JTextArea();
		textreq.setBounds(30, 35, 600, 100);
		executer = new JButton("Executer");
		executer.setBounds(660, 60, 110, 30);
		executer.addActionListener(this);
		tab2.add(name2);
		tab2.add(name3);
		tab2.add(textreq);
		tab2.add(executer);
		scroll2 = new JScrollPane (tableauRes2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setBackground(Color.WHITE);
		scroll2.setBounds(30,170, 600, 600);
		tab2.add(scroll2);
		onglets.addTab("Requette Personnalisee", tab2); 	
		/*
		 * Ajout des onglets au JTabbedPane ;
		 */
		onglets.setOpaque(true);
		this.add(onglets);
		this.setVisible(true);
		
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
			System.exit(0);
			}
			};
		this.addWindowListener(l);
		}
	@Override
	public void actionPerformed(ActionEvent a) {
		 Object source = a.getSource(); 
		 if(source==list){
			 	int item = list.getSelectedIndex();
			 	model.Query_Select(item);
		 }
		 if(source==executer){
			 String query = textreq.getText();
			 model.Query_Select(query);
		 }	 
	}
}

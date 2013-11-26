package algo_ratp.IHM;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;

import Launcher.AutoCompleteModel;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7740513522896625655L;
	
	public Frame(){
		AutoCompleteModel acm = new AutoCompleteModel();
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("Bonjour");
		ar.add("Ok");
		ar.add("SDFKOISDF");
		ar.add("sdf");
		ar.add("dsklfjsdfklsdf");
		ar.add("sf");
		ar.add("uikhj");
		ar.add("hjgh");
		ar.add("gjkhjk");
		acm.addAll(ar);
		setTitle("Imprimer");
		setSize(400,300);
		setLocationRelativeTo(null); // mettre au centre la page
		setResizable(false); // interdir le redimensionnement
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame.DISPOSE_ON_CLOSE pour effacer la fenêtre mais l'appli continue
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// bouton Fermer
		setVisible(true); // toujours en dernière

		Container c = getContentPane();
		c.add(new Search(acm));
		pack();
	}

}

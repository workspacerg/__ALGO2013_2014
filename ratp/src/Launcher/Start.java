package Launcher;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SwingUtilities;

import algo_ratp.Correspondance;
import algo_ratp.DALProvider;
import algo_ratp.Ligne;
import algo_ratp.Plan;
import algo_ratp.Station;
import algo_ratp.IHM.Frame;
import algo_ratp.IHM.IHM_home;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Pour initialiser les identifiants de la base, à mettre dans une IHM après
		DALProvider.getInstance().initIdentifiers("root", "");
		// Création du plan (il est crée mais pas utilisé pour l'instant, ca met environ 5-10 secondes
		Plan.getInstance().getPlan();
		System.out.println("Plan crée");
		Runnable r = new Runnable(){
			public void run(){
				new IHM_home();
			}
			
		};
		
		SwingUtilities.invokeLater(r);
		
	}
	

}

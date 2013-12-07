package Launcher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;


import algo_ratp.IHM.IHM_search;

import algo_ratp.DALProvider;
import algo_ratp.Dijkstra;
import algo_ratp.Ligne;
import algo_ratp.Plan;
import algo_ratp.Relation;
import algo_ratp.Station;

public class Start {

	/*private static String getCorresp(Station st){
		StringBuilder sb = new StringBuilder();
		for(Ligne li : Correspondance.getInstance().getMapLigne().get(st).GetLignes())
			sb.append(li.getShort_name()+",");
		
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}*/
	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		// Pour initialiser les identifiants de la base, à mettre dans une IHM après
		DALProvider.getInstance().initIdentifiers("root", "");
		System.out.println("Plan crée");
		
		
		Map<Ligne,ArrayList<Station>> map = Plan.getInstance().getPlan();
		Map<Ligne,ArrayList<Station>> map2 = new HashMap<Ligne, ArrayList<Station>>(map);
		
		/*for(Relation r : Plan.getInstance().getRelations().get(map2.get(Plan.getInstance().GetLigneByShortName("7B").get(0)).get(1)))
			System.out.println(r.getTarget().getName() + "--->" + r.getWeight() + "--->" + r.getLigne().getShort_name());*/

		
		//System.out.println(map2.get(Plan.getInstance().GetLigneByShortName("A").get(0)).get(15).getName());
		Dijkstra.execute(map2.get(Plan.getInstance().GetLigneByShortName("14").get(0)).get(7));
		LinkedList<Relation> lst = Dijkstra.getPath(map2.get(Plan.getInstance().GetLigneByShortName("7B").get(0)).get(3));
		/*System.out.println(Plan.getInstance().GetLigneByShortName("B").get(0).getId_route());
		int i = 0;
		System.out.println("Début chemin\n");
		for(Relation s : lst){
			System.out.println(s.getTarget().getName() + "-->" + s.getLigne().getShort_name() + "-->" + s.getWeight());
			i+= s.getWeight();
		}
		System.out.println("\nFin chemin de longueur : "+i);*/
		
		DALProvider.getInstance().connect();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try{
			DALProvider.getInstance().GetRealPathWithTime(lst,sdf.parse("08:15"));
		}
		catch(Exception exp){
			System.out.println("Problème de format de date");
		}
		
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
		{
	        if ("Nimbus".equals(info.getName())) 
	        {
	            UIManager.setLookAndFeel(info.getClassName());
	            break;
	        }
	    }
		
		Runnable r = new Runnable()
		{
			public void run()
			{
				new IHM_search();
			}
			
		};		
		SwingUtilities.invokeLater(r);
		
	}
	

}

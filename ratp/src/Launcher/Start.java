package Launcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import algo_ratp.Correspondance;
import algo_ratp.DALProvider;
import algo_ratp.Ligne;
import algo_ratp.Plan;
import algo_ratp.Station;
import algo_ratp.IHM.IHM_home;

public class Start {

	private static String getCorresp(Station st){
		StringBuilder sb = new StringBuilder();
		for(Ligne li : Correspondance.getInstance().getMapLigne().get(st).GetLignes())
			sb.append(li.getShort_name()+",");
		
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
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
		

		for(Entry<Ligne,ArrayList<Station>> ent : Plan.getInstance().getPlan().entrySet()){
			System.out.println(ent.getKey().getShort_name()+"------------");
			for(Station st : Plan.getInstance().getPlan().get(ent.getKey()))
				System.out.println(st.getName()+"-->"+getCorresp(st));
			System.out.println("----------------------------------------");
			
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
				new IHM_home();
			}
			
		};		
		SwingUtilities.invokeLater(r);
		
	}
	

}

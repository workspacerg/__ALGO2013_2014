
package algo_ratp;

import java.util.ArrayList;

public class LigneCorrespondance {

	private ArrayList<Ligne> lignes;
	
	/**
	 * Constructeur paramétré
	 * @param li
	 */
	
	public LigneCorrespondance(ArrayList<Ligne> li){
		lignes = li;
	}
	
	/**
	 * Renvoie des correspondances lié
	 * @return
	 */
	
	public ArrayList<Ligne> GetLignes(){
		return lignes;
	}
	
	/**
	 * 
	 * @param li
	 * @return
	 */
	
	public boolean containsLine(Ligne li){
		for(Ligne l : lignes)
			if(l.getShort_name().equalsIgnoreCase(li.getShort_name()))
				return true;
		
		return false;
	}
}

package algo_ratp;

import java.util.ArrayList;

public class LigneCorrespondance {

	private ArrayList<Ligne> lignes;
	
	public LigneCorrespondance(ArrayList<Ligne> li){
		lignes = li;
	}
	
	public ArrayList<Ligne> GetLignes(){
		return lignes;
	}
	
	// Pour éviter les doublons dans la map de correspondance (on s'en fiche du sens de la ligne, une correspondance = une ligne n'import quel sens)
	public boolean containsLine(Ligne li){
		for(Ligne l : lignes)
			if(l.getShort_name().equalsIgnoreCase(li.getShort_name()))
				return true;
		
		return false;
	}
}

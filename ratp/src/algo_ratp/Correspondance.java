package algo_ratp;


import java.util.HashMap;
import java.util.Map;

public class Correspondance {
	
	private Map<Station,LigneCorrespondance> mapLigne;
	private static Correspondance INSTANCE = null;
	
	/**
	 * 
	 * Constructeur sans paramètre qui crée un HashMap de correspondance
	 * 
	 */
	
	private Correspondance(){
		mapLigne = new HashMap<Station, LigneCorrespondance>();
	}
	
	/**
	 * 
	 * @return l'instance de correpondance ou la crée si il n'y en a pas
	 */
	
	public static Correspondance getInstance(){
		if(INSTANCE == null){
			INSTANCE = new Correspondance();
		}
		
		return INSTANCE;
	}
	
	/**
	 * 
	 * @return une map des stations avec les correpondances
	 *
	 */

	public Map<Station,LigneCorrespondance> getMapLigne() {
		return mapLigne;
	}
	
}

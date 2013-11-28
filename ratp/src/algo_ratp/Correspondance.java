package algo_ratp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Correspondance {
	
	private Map<Station,ArrayList<Ligne>> mapLigne;
	private static Correspondance INSTANCE = null;
	private Correspondance(){
		mapLigne = new HashMap<Station, ArrayList<Ligne>>();
	}
	
	public static Correspondance getInstance(){
		if(INSTANCE == null){
			INSTANCE = new Correspondance();
		}
		
		return INSTANCE;
	}

	public Map<Station,ArrayList<Ligne>> getMapLigne() {
		return mapLigne;
	}
	
}

package algo_ratp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plan {

	private Map<String,Station>  maps  ;
	private Map<String,Ligne>  plan  ;
	
	public Plan() {
		super();
		this.maps = new HashMap<String,Station>();
		this.plan = new HashMap<String,Ligne>();
	}
	
	// RGL start
	public boolean addLignes(Map<String,Ligne> lignes){
		try{
			//Ajout à la carte
			this.plan.putAll(lignes);
		}
		catch(Exception e){
			return false;
		}
		
		return true;
	}
			
	public void findItinerary(String _arg1, String _arg2) {
		

	}
	
	public boolean addStations(Map<String,Station> stationList) {
		
		if (stationList == null )
			return false;
			
		try {
			maps.putAll(stationList);

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

		return true;
	}


	
}

package algo_ratp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plan {

	private Map<String,ArrayList<Station>>  maps  ;
	private Map<String,ArrayList<Ligne>>  plan  ;

	
	public Plan() {
		super();
		this.maps = new HashMap<String,ArrayList<Station>>();
		this.plan = new HashMap<String,ArrayList<Ligne>>();

	}
	
	
	public boolean addLigne(Map<String,ArrayList<Ligne>> lignes){
		
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
		
	public Map<String, ArrayList<Ligne>> getMaps() {
		return plan;
	}
	
	public boolean addStations(Map<String,ArrayList<Station>> stationList) {
		
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

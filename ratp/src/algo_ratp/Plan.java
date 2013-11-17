package algo_ratp;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Plan {

	private Map<String, Ligne>  plan  ;
	
	public Plan() {
		super();
		
		this.plan = new HashMap<String, Ligne>();
	}
	
	
	public boolean addLigne(Type tp, String sn_ligne, String ln_ligne, String rc_ligne, String id_route){
		
		
		//if ligne existe
		if (this.plan.containsKey(id_route))
			return false;
		
		try{
			// Création de la ligne
			Ligne newLigne = new Ligne(tp, sn_ligne, ln_ligne, rc_ligne, id_route);
		
			//Ajout à la carte
			this.plan.put(id_route, newLigne);
		}
		catch(Exception e){
			return false;
		}
		
		return true;
	}
	
	public boolean addStationToLigne(String id_route, Integer position,Station uneStation ){
		
		if (!this.plan.containsKey(id_route))
			return false;
		
		this.plan.get(id_route).addStation(position, uneStation);
		
		return true;
	}
	
	public void getLigne(){
		
		for ( Entry<String, Ligne> entry : plan.entrySet())
		{
		   
			System.out.println(plan.get(entry.getKey()).displayLigne());
		   
		}
		
	}
	
	public Map<String, Ligne> getMaps() {
		return plan;
	}
	
	public void findItinerary(String StationStart, String StationStop) {
		

	}
	
	
}

package algo_ratp;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Plan {

	private Map<Integer, Ligne>  plan  ;
	
	public Plan() {
		super();
		this.plan = new HashMap<Integer, Ligne>();
	}
	

	public boolean addLigne(Type tp, String sn_ligne, String ln_ligne, String rc_ligne, String id_route,Integer position){
		
		
		//if ligne existe
		if (this.plan.containsKey(id_route))
			return false;
		
		try{
			// Création de la ligne
			Ligne newLigne = new Ligne(tp, sn_ligne, ln_ligne, rc_ligne, id_route);
		
			//Ajout à la carte
			this.plan.put(position, newLigne);
		}
		catch(Exception e){
			return false;
		}
		
		
		return true;
	}
	
	public boolean addStationToLigne(Integer id_route, Integer position,Station uneStation ){
		
		if (!this.plan.containsKey(id_route))
			return false;
		
		this.plan.get(id_route).addStation(position, uneStation);
		
		//System.out.println(this.plan.get(id_route).getStation(1).displayName());
		
		return true;
	}
	
	public void getLigne(){
		
		for ( Entry<Integer, Ligne> entry : plan.entrySet())
		{
		   
			System.out.println(plan.get(entry.getKey()).displayLigne());
		   
		}
		
	}
	
	public Map<Integer, Ligne> getMaps() {
		return plan;
	}


	public void findItinerary(String _arg1, String _arg2) {
		

	}

}
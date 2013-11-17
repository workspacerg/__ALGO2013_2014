package algo_ratp;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Plan {

	private Map<String, Station>  maps  ;
	private Map<Integer, Ligne>  plan  ;
	
	public Plan() {
		super();
		this.maps = new HashMap<String, Station>();
		this.plan = new HashMap<Integer, Ligne>();
	}
	
	// RGL start
	public boolean addLigne(Type tp, String sn_ligne, String ln_ligne, String rc_ligne, Integer id_route){
		
		
		//if ligne existe
		if (this.plan.containsKey(id_route))
			return false;
		
		try{
			// Création de la ligne
			Ligne newLigne = new Ligne(tp, sn_ligne, ln_ligne, rc_ligne, id_route);
		
			System.out.println(newLigne.displayLigne());
		
			//Ajout à la carte
			this.plan.put(id_route, newLigne);
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
	
	// RGL end
	
	
	
	public Map<Integer, Ligne> getMaps() {
		return plan;
	}
	
	// Affiche l'ensemble de la maps	
	public void displayAllMaps() { 
		
		for ( Entry<String, Station> entry : maps.entrySet())
		{
		   System.out.println("		" + entry.getKey());
		   
		}

	}
	
	public void displayRelation(String maStation){
		
		
		
		 for(int i = 0 ; i < maps.get(maStation).GetCorrespondances().size(); i++){
			 	String maValeur = maps.get(maStation).GetCorrespondances().get(i).displayLigne();
			 	System.out.println("Ligne " + i + " : " + maValeur);
			 }
		
	}
	
	public void findItinerary(String _arg1, String _arg2) {
		

	}
	
	public boolean addStation(String _arg1) {
		
		if (_arg1 == null )
			return false;
		
		Station newStation = new Station(_arg1);
		
		try {
			
			if (!maps.containsKey(_arg1))
				this.maps.put(_arg1,  newStation);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean addRelationBetween(String _arg1, String _arg2, String _arg3, Relation.type _arg){
		
		if (_arg1 == null || _arg2 == null)
			return false;
		
		if (!maps.containsKey(_arg1))
			addStation(_arg1);
		
		if (!maps.containsKey(_arg2))
			addStation(_arg2);
		
		
		Station station1 = new Station(_arg1);
		Station station2 = new Station(_arg2);
		String ligne = _arg3;
		int time = 60;
		
		Relation relation = new Relation(station1, station2, time, ligne);
		
		//maps.get(_arg1).GetCorrespondances().add(relation);
		//maps.get(_arg2).GetCorrespondances().add(relation);
		
		return true;
	}

	public boolean delStation(String _arg1) {
		
		if(!this.maps.containsKey(_arg1))
			return false;
		
		this.maps.remove(_arg1);
		
		return false;
	}

	
}

package algo_ratp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Maps {

	private Map<String, Stations>  maps  ; 
	
	public Maps() {
		super();
		this.maps = new HashMap<String, Stations>();
	}
	
	public Map<String, Stations> getMaps() {
		return maps;
	}
	
	// Affiche l'ensemble de la maps	
	public void displayAllMaps() { 
		
		for ( Entry<String, Stations> entry : maps.entrySet())
		{
		   System.out.println("		" + entry.getKey());
		   
		}

	}
	
	public void displayRelation(){
		
		
		 for(int i = 0 ; i < maps.get("Republique").GetRelation().size(); i++){
			 	String maValeur = maps.get("Republique").GetRelation().get(i).display();
			 	System.out.println("Ligne " + i + " : " + maValeur);
			 }
		
	}
	
	public void findItinerary(String _arg1, String _arg2) {
		

	}
	
	public boolean addStation(String _arg1) {
		
		if (_arg1 == null )
			return false;
		
		Stations newStation = new Stations(_arg1);
		
		try {
			
			if (!maps.containsKey(_arg1))
				this.maps.put(_arg1,  newStation);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean addRelationBetween(String _arg1, String _arg2, String _arg3, Relations.type _arg){
		
		if (_arg1 == null || _arg2 == null)
			return false;
		
		if (!maps.containsKey(_arg1))
			addStation(_arg1);
		
		if (!maps.containsKey(_arg2))
			addStation(_arg2);
		
		
		Stations station1 = new Stations(_arg1);
		Stations station2 = new Stations(_arg2);
		String ligne = _arg3;
		int time = 60;
		
		Relations relation = new Relations(station1, station2, time, ligne);
		
		maps.get(_arg1).GetRelation().add(relation);
		maps.get(_arg2).GetRelation().add(relation);
		
		return true;
	}

	public boolean delStation(String _arg1) {
		
		if(!this.maps.containsKey(_arg1))
			return false;
		
		this.maps.remove(_arg1);
		
		return false;
	}

	
}

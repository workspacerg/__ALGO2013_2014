package algo_ratp;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Maps {

	private Map<String, Stations>  maps  ; 
	
	public Maps() {
		super();
		this.maps = new HashMap<String, Stations>();
	}
	
	public void displayAllMaps() {
		// TODO Auto-generated method stub
		
		for ( Entry<String, Stations> entry : maps.entrySet())
		{
		   System.out.println("		" + entry.getKey());
		   
		}

	}
	
	public void findItinerary(String _arg1, String _arg2) {
		// TODO Auto-generated method stub

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

	public boolean addRelationBetween(String _arg1, String _arg2){
		
		if (_arg1 == null || _arg2 == null)
			return false;
		
		if (!maps.containsKey(_arg1))
			addStation(_arg1);
		
		if (!maps.containsKey(_arg2))
			addStation(_arg2);
		
		
		Stations station1 = new Stations(_arg1);
		Stations station2 = new Stations(_arg2);
		int time = 60;
		String ligne = "5";
		
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

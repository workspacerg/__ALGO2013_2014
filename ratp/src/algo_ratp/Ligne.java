package algo_ratp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ligne {
	
	private Map<Integer,Station> stations;
	private Type typeTransport;
	private String short_name;
	private String long_name;
	private String route_color;
	
	public Ligne(Type _typeT,String _sn,String _ln,String _rc){
		stations = new HashMap<Integer,Station>();
		typeTransport = _typeT;
		short_name = _sn;
		long_name = _ln;
		route_color = _rc;
	}
	
	public void addStation(Integer position,Station uneStation){
		if(stations.containsKey(position)){
			Logger.getLogger("Ligne").log(Level.INFO, "Index utilisé déja existant");
			return;
		}
		
		if(position != null && uneStation != null)
			stations.put(position, uneStation);
	}
	
	public void addStations(Map<Integer,Station> uneMap){
		if(uneMap != null)
			stations.putAll(uneMap);
	}
	
	public Station getStation(int position){
		if(stations.containsKey(position)){
			return stations.get(position);
		}		
		return null;
	}
	
	public String displayLigne(){
		return String.format("shortname : %s, longname : %s, routecolor : %s, type : %s, stations : %s"
				,this.short_name,this.long_name,this.route_color,stations.toString());
	}
}

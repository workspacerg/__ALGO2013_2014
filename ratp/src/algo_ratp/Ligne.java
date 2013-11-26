package algo_ratp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ligne {
	
	private ArrayList<Station> stations;
	private Type typeTransport;
	private String short_name;
	private String long_name;
	private String route_color;
	private String id_route;
	
	public Ligne(Type _typeT,String _sn,String _ln,String _rc, String id_route2){
		stations = new ArrayList<Station>();
		typeTransport = _typeT;
		short_name = _sn;
		long_name = _ln;
		route_color = _rc;
		id_route = id_route2;
	}
	 	
	public void addStation(Station uneStation){
		if(uneStation!=null)
			stations.add(uneStation);
	}
	
	public void addStations(ArrayList<Station> listStation){
		if(listStation != null)
			stations.addAll(listStation);
	}
	
	
	public String displayLigne(){
		
		return String.format("shortname : %s, longname : %s, routecolor : %s, type : " + typeTransport  + ", stations : %s",
				this.short_name,this.long_name,this.route_color,stations.toString());
	}
}

package algo_ratp;

import java.util.ArrayList;

public class Ligne {
	
	private ArrayList<Station> stations;
	private Type typeTransport;
	private String short_name;
	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}


	private String long_name;
	private String route_color;
	private String id_route;
	
	public String getId_route() {
		return id_route;
	}

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
	
	@Override
    public int hashCode() {
        return this.short_name.hashCode();
    }
	
	@Override
	public boolean equals(Object o) {
	    if (o == null || o.getClass() != getClass()) { // << this is important
	        return false;
	    }
	    
	    return ((Ligne)o).getId_route().equals(this.id_route);
	}
}

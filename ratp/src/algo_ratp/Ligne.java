package algo_ratp;

import java.util.ArrayList;

public class Ligne {
	
	private ArrayList<Station> stations;
	private Type typeTransport;

	private String short_name;
	
	/**
	 * 
	 * Récuperation du short_name de la ligne
	 * @return short_name
	 * 
	 */
	
	public String getShort_name() {
		return short_name;
	}
	
	/**
	 * Mise à jour du short_name
	 * @param short_name
	 */

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}


	private String long_name;
	private String route_color;
	private String id_route;
	
	/**
	 * Récuperation de l'identifiant de la route
	 * @return id_route
	 */
	
	public String getId_route() {
		return id_route;
	}
	
	/**
	 * Constructeur Paramétré de l'objet ligne
	 * @param _typeT
	 * @param _sn
	 * @param _ln
	 * @param _rc
	 * @param id_route2
	 */

	public Ligne(Type _typeT,String _sn,String _ln,String _rc, String id_route2){
		stations = new ArrayList<Station>();
		typeTransport = _typeT;
		short_name = _sn;
		long_name = _ln;
		route_color = _rc;
		id_route = id_route2;
	}
	
 	/**
 	 * Constructeur vide
 	 * @deprecated
 	 */
	
	public Ligne() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * Ajout d'une station dans la ligne
	 * @see Station
	 * @param uneStation
	 * 
	 */
	public void addStation(Station uneStation){
		if(uneStation!=null)
			stations.add(uneStation);
	}
	
	/**
	 * Ajout d'une liste de Stations
	 * @see Station
	 * @param listStation
	 */
	
	public void addStations(ArrayList<Station> listStation){
		if(listStation != null)
			stations.addAll(listStation);
	}
	
	/**
	 * Récuperation du moyen de transport liée à la cible
	 * @return typeTransport
	 */
	
	public Type getTypeTransport() {
		return typeTransport;
	}
	
	/**
	 * Mise à jour du type de transport via une enumeration
	 * @see Type 
	 * @param typeTransport
	 */
	
	public void setTypeTransport(Type typeTransport) {
		this.typeTransport = typeTransport;
	}
	
	/**
	 * String contenant l'ensemble des informations
	 * @return String contenant l'ensemble des informations de la ligne
	 */
	
	public String displayLigne(){
		
		return String.format("shortname : %s, longname : %s, routecolor : %s, type : " + typeTransport  + ", stations : %s",
				this.short_name,this.long_name,this.route_color,stations.toString());
	}
	
	/**
	 * 
	 */
	
	@Override
    public int hashCode() {
        return this.short_name.hashCode();
    }
	
	/**
	 * Verifie si l'objet passer en paramètre est ou non la même ligne
	 * @param objet à vérifier
	 */
	
	@Override
	public boolean equals(Object o) {
	    if (o == null || o.getClass() != getClass()) { // << this is important
	        return false;
	    }
	    
	    return ((Ligne)o).getId_route().equals(this.id_route);
	}
}

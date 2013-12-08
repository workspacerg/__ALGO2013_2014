package algo_ratp;

import java.util.Date;

public class Station implements Comparable<Station> {
	
	private String id;
	private String name;
	private int minDistance = Integer.MAX_VALUE;
    private Station previous;
    private Date departure;
    private Date arrival;
    //private Relation[] relations;
    
    /**
     * @return le nom de la station
     */
	
	public String getName() {
		return name;
	}
	
	/**
	 * Mise à jour du nom de la station
	 * @param name
	 */

	public void setName(String name) {
		this.name = name;
	}

	private int sequence;

	/**
	 * Constructeur 1 
	 * @param _name
	 * @param id
	 */
	
	public Station(String _name,String id){
		name = _name;
	}
	
	/**
	 * Constructeur 2
	 * @param _name
	 * @param _id
	 * @param _sequence
	 */
	
	public Station(String _name,String _id,int _sequence){
		name = _name;
		setId(_id);
		sequence = _sequence;
	}
	
	/**
	 * Constructeur 3
	 * @param _name
	 * @param _id
	 * @param _sequence
	 * @param _depart
	 * @param _arrival
	 */
	
	public Station(String _name,String _id,int _sequence,Date _depart,Date _arrival){
		name = _name;
		setId(_id);
		sequence = _sequence;
		setDeparture(_depart);
		setArrival(_arrival);
	}
	
	/**
	 * Mise à jour du nom de la station
	 */
	
	public String toString(){
		return this.name;
	}
	
	/**
	 * Recupération de la sequence
	 * @return
	 */
	
	public int getSequence() {
		return sequence;
	}
	
	/**
	 * Mise à jour de la séquence
	 * @param sequence
	 */

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * Afficher le nom de la stations
	 * @return
	 */
	
	public String displayName() {
		return this.name;
	}
	
	/**
	 * @return l'id de la station
	 */

	public String getId() {
		return id;
	}

	/**
	 * Mise à jour de l'identifiand de la stations
	 * @param id
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Override
    public int hashCode() {
        return this.name.hashCode();
    }
	
	/**
	 * Compare l'objet passé en paramètre avec celle qui l'appel  
	 * @param object
	 */
	
	@Override
	public boolean equals(Object o) {
	    if (o == null || o.getClass() != getClass()) { // << this is important
	        return false;
	    }
	    
	    return ((Station)o).getName().equalsIgnoreCase(this.name);
	}
	
	/**
	 * Comparaison 
	 * @deprecated
	 */

	@Override
	public int compareTo(Station arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Return distance minimal
	 * @return
	 */

	public int getMinDistance() {
		return minDistance;
	}
	
	/**
	 * MAJ distance minimal
	 * @param minDistance
	 */

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}
	
	/**
	 * 
	 * @return la station précédente
	 */

	public Station getPrevious() {
		return previous;
	}
	
	/**
	 * MAJ station précédente
	 * @param previous
	 */

	public void setPrevious(Station previous) {
		this.previous = previous;
	}
	
	/**
	 * 
	 * @return l'heure de depart
	 */

	public Date getDeparture() {
		return departure;
	}
	
	/**
	 * MAJ l'heure de depart
	 * @param departure
	 */

	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	 
	/**
	 * 
	 * @return heure d'arrivé
	 */

	public Date getArrival() {
		return arrival;
	}
	
	/**
	 * 
	 * MAJ heure d'arrivé
	 * @param arrival
	 * 
	 */

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	/*public Relation[] getRelations() {
		return relations;
	}

	public void setRelations(Relation[] relations) {
		this.relations = relations;
	}*/
	

}

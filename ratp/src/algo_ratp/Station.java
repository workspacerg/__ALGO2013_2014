package algo_ratp;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Station implements Comparable<Station> {
	
	private String id;
	private String name;
	private int minDistance = Integer.MAX_VALUE;
    private Station previous;
    private Date departure;
    private Date arrival;
    //private Relation[] relations;
    
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int sequence;

	public Station(String _name,String id){
		name = _name;
	}
	
	public Station(String _name,String _id,int _sequence){
		name = _name;
		setId(_id);
		sequence = _sequence;
	}
	
	public Station(String _name,String _id,int _sequence,Date _depart,Date _arrival){
		name = _name;
		setId(_id);
		sequence = _sequence;
		setDeparture(_depart);
		setArrival(_arrival);
	}
	
	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public String displayName() {
		return this.name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	@Override
    public int hashCode() {
        return this.name.hashCode();
    }
	
	@Override
	public boolean equals(Object o) {
	    if (o == null || o.getClass() != getClass()) { // << this is important
	        return false;
	    }
	    
	    return ((Station)o).getName().equalsIgnoreCase(this.name);
	}

	@Override
	public int compareTo(Station arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public Station getPrevious() {
		return previous;
	}

	public void setPrevious(Station previous) {
		this.previous = previous;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Date getArrival() {
		return arrival;
	}

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

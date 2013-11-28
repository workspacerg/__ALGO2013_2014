package algo_ratp;

import java.util.ArrayList;

public class Station {
	
	private String id;
	private String name;
	
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
	    
	    return ((Station)o).getName().equalsIgnoreCase(name);
	}
	

}

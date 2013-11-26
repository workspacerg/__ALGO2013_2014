package algo_ratp;

import java.util.ArrayList;

public class Station {
	
	private ArrayList<Ligne> correspondance;
	private String id;
	private String name;
	private double latitude;
	private double longitude;
	private String parent_id;
	
	public Station(String _name,String id){
		name = _name;
	}
	
	public Station(String _name,double d,double e){
		name = _name;
		correspondance = new ArrayList<>();
		latitude = d;
		longitude = e;
	}
	
	public Station(String _id,String _name,double _latitude,double _longitude,String _parent_id){
		name = _name;
		correspondance = new ArrayList<>();
		latitude = _latitude;
		longitude = _longitude;
		id = _id;
		parent_id = _parent_id;
	}
	
	public ArrayList<Ligne> GetCorrespondances() {
		return correspondance;
	}
	
	public String displayName() {
		return this.name;
	}
	
	public boolean addCorrespondance(Ligne _arg1) {
		try{
			if(_arg1 == null)
				return false;

			return correspondance.add(_arg1);
		}
		catch(Exception exp){
			exp.printStackTrace();
			return false;
		}
	}

}

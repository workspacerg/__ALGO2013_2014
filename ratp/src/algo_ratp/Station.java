package algo_ratp;

import java.util.ArrayList;

public class Station {
	
	private ArrayList<Ligne> correspondance ;
	private String name;
	private double latitude;
	private double longitude;
	
	public Station(String _name){
		name = _name;
	}
	
	public Station(String _name,double d,double e){
		name = _name;
		correspondance = new ArrayList<>();
		latitude = d;
		longitude = e;
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

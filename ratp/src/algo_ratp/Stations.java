package algo_ratp;

import java.util.ArrayList;

public class Stations {
	
	private ArrayList<Relations> relation ;
	private String Name;
	
	public Stations(String _arg){
		relation = new ArrayList<>();
		Name = _arg;
	}
	
	public ArrayList<Relations> GetRelation() {
		return relation;
	}
	
	public String displayName() {
		
		
		
		return Name;
	}
	
	public boolean addRelation(Relations _arg1) {
		// TODO Auto-generated method stub
		
		
		return false;
	}


	public boolean delRelation() {
		// TODO Auto-generated method stub
		
		
		return false;
	}

}

package algo_ratp;

import java.util.ArrayList;

public class Station {
	
	private ArrayList<Relation> relation ;
	private String name;
	
	public Station(String _arg){
		name = _arg;
		relation = new ArrayList<>();
	}
	
	public ArrayList<Relation> GetRelation() {
		return relation;
	}
	
	public String displayName() {
		return this.name;
	}
	
	public boolean addRelation(Relation _arg1) {
		try{
			if(_arg1 == null)
				return false;

			return relation.add(_arg1);
		}
		catch(Exception exp){
			exp.printStackTrace();
			return false;
		}
	}


	public boolean delRelation() {
		// TODO Auto-generated method stub
		
		
		return false;
	}

}

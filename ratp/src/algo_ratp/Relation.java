package algo_ratp;

public class Relation {
	
	public enum type{RER, Metro, Bus };
	
	private Station start;
	private Station end;
	private int time;
	private String ligne;
	
	public Relation(Station stationStart, Station stationEnd, int _arg3, String _arg4){
		
		start 	= stationStart;
		end 	= stationEnd;
		time	= _arg3;
		ligne   = _arg4; 
		
	}
	
	public String display() {
		
		String toReturn =  "test" + start.displayName() + " " + end + "/nTemps: " + time + "/nLigne : " + ligne;
	
		return toReturn;

	}

}

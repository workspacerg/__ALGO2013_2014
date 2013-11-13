package algo_ratp;

public class Relation {
	
	public enum type{RER, Metro, Bus };
	
	private Station start;
	private Station end;
	private int time;
	private String ligne;
	private Transport typeTransport;
	
	public Relation(Station stationStart, Station stationEnd, int _arg3, String _arg4,Transport _typeTransport){
		
		start 	= stationStart;
		end 	= stationEnd;
		time	= _arg3;
		ligne   = _arg4; 
		typeTransport = _typeTransport;		
	}
	
	public Relation(Station stationStart, Station stationEnd, int _arg3, String _arg4){
		
		start 	= stationStart;
		end 	= stationEnd;
		time	= _arg3;
		ligne   = _arg4; 	
	}
	
	public String display() {
		
		
		String toReturn = "\n	" + start.displayName() + " \n	" + end.displayName() + "\n	Temps : " + time + "secondes \n	Ligne : " + ligne;

		
		return toReturn;

	}

}

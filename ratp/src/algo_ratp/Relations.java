package algo_ratp;

public class Relations {
	
	public enum type{RER, Metro, Bus };
	
	private Stations start;
	private Stations end;
	private int time;
	private String ligne;
	
	public Relations(Stations stationStart, Stations stationEnd, int _arg3, String _arg4){
		
		start 	= stationStart;
		end 	= stationEnd;
		time	= _arg3;
		ligne   = _arg4; 
		
	}
	
	public String display() {
		
		String toReturn =  "\n	" +  start.displayName() + " \n	" + end.displayName() + "\n	Temps : " + time + "secondes \n	Ligne : " + ligne;
		
	
		return toReturn;

	}

}

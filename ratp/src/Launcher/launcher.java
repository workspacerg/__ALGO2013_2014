package Launcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import algo_ratp.DALProvider;
import algo_ratp.Plan;
import algo_ratp.Station;
import algo_ratp.Type;

public class launcher {

	public static void Launch() {
		// TODO Auto-generated method stub
		
		System.out.println("Lancement du programme");
		
		try {
			System.out.println("Creation de la carte : ");
			//	Plan carte = new Plan();
			System.out.println("	====================== > Ok \n");
			
			//carte.addLignes(DALProvider.getInstance().GetLignes());
			//carte.addStations(DALProvider.getInstance().GetStations());
			//DALProvider.getInstance().close();
			/*carte.addLigne(Type.Metro, "1", "1 (CHATEAU DE VINCENNES <-> LA DEFENSE)", "FFFFFF", "918044");
			ArrayList<Station> stations = new ArrayList<Station>();
			stations.add(new Station("La defense", 0.8532843204655753, 0.8532843204655753));
			carte.addStationListToLigne("918044", stations);
			// On vide pour utiliser qu'une map (mode flemme)
			stations.clear();
			
			carte.addLigne(Type.Metro, "5", "5 (BOBIGNY - PABLO PICASSO <-> PLACE D'ITALIE)", "FFFFFF", "708924");
			stations.add(new Station("Place d'italie", 0.8532843204655753, 0.8532843204655753));
			stations.add(new Station("Place d'italie", 0.8532843204655753, 0.8532843204655753));
			carte.addStationListToLigne("708924", stations);
			stations.clear();*/
					
				
				
	
		} catch (Exception e) {
			System.out.println("fail");
		}

	}

}

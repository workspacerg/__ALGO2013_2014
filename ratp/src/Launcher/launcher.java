package Launcher;

import algo_ratp.Plan;
import algo_ratp.Station;
import algo_ratp.Type;

public class launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Lancement du programme");
		
		try {
			System.out.println("Creation de la carte : ");
				Plan carte = new Plan();
			System.out.println("	====================== > Ok \n");
			
			System.out.println("Ajout de la ligne 1 + stations");
				if(!carte.addLigne(Type.Metro, "1", "1 (CHATEAU DE VINCENNES <-> LA DEFENSE)", "FFFFFF", 918044))
					System.out.println(" 	Erreur addLigne() ");
				else{
		
					Station newStation = new Station("La defense", 0.8532843204655753, 0.8532843204655753);
					
					if(!carte.addStationToLigne(918044, 1, newStation))
						System.out.println(" 	Erreur addStation() ");
					else
						System.out.println("	====================== > Ok  :  " +  carte.getMaps().get(918044). getStation(1).displayName());
				
				}
			
			
				System.out.println("Ajout de la ligne 1 + stations");
				if(!carte.addLigne(Type.Metro, "5", "5 (BOBIGNY - PABLO PICASSO <-> PLACE D'ITALIE)", "FFFFFF", 708924))
					System.out.println(" 	Erreur addLigne() ");
				else{
		
					Station newStation2 = new Station("Place d'italie", 0.8532843204655753, 0.8532843204655753);
					
					if(!carte.addStationToLigne(708924, 1, newStation2))
						System.out.println(" 	Erreur addStation() ");
					else
						System.out.println("	====================== > Ok  :  " +  carte.getMaps().get(708924). getStation(1).displayName());
				
					System.out.println("\n");
					carte.getLigne();;
					
				}
				
	
		} catch (Exception e) {
			System.out.println("fail");
		}

	}

}

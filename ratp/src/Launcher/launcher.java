package Launcher;

import algo_ratp.Maps;

public class launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Lancement du programme");
		
		try {
			System.out.println("Creation de la carte : ");
			Maps carte = new Maps();
			System.out.println("====================== > Ok");
			
			System.out.println("Ajout de la station republique");
			carte.addStation("Republique") ; 
			carte.addStation("Nation") ; 
			
			System.out.println("====================== > Ok");
			
			System.out.println("Affichage des sations : " );
			carte.displayAllMaps();
			
			System.out.println("Ajout du lien");
			carte.addRelationBetween("Republique", "Nation");
			System.out.println("====================== > Ok");
			
			System.out.println(carte.getMaps().get("Republique").GetRelation().get(0).display());
			
	
		} catch (Exception e) {
			System.out.println("Fail");
		}

	}

}

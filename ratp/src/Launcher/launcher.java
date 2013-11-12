package Launcher;

import algo_ratp.Plan;
import algo_ratp.Relation;

public class launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Lancement du programme");
		
		try {
			System.out.println("Creation de la carte : ");
			Plan carte = new Plan();
			System.out.println("====================== > Ok");
			
			System.out.println("Ajout de la station republique");
			carte.addStation("Republique") ; 
			carte.addStation("Nation") ; 
			carte.addStation("Jaures") ; 
			
			System.out.println("====================== > Ok");
			
			System.out.println("Affichage des sations : " );
			carte.displayAllMaps();
			

			System.out.println("Ajout du lien");
			carte.addRelationBetween("Republique", "Nation", "5", Relation.type.Metro);
			carte.addRelationBetween("Nation", "Republique", "5", Relation.type.Metro);
			carte.addRelationBetween("Nation", "Jaures", "8", Relation.type.Metro);

			System.out.println("====================== > Ok");
			
			carte.displayRelation("Nation");
			
	
		} catch (Exception e) {
			System.out.println("Fail");
		}

	}

}

package algo_ratp.IHM.tools;

import java.util.ArrayList;
import java.util.List;

import algo_ratp.Station;


public class AutoCompleteModel {
	
	/**
	 * La liste dans laquelle on recherche
	 */
	private List<Station> mots;
	
	/**
	 * Constructeur du modele
	 */
	public AutoCompleteModel(){
		mots = new ArrayList<Station>();
	}
	
	/**
	 * Fonction qui permet d'ajouter un mot a la liste des recherches
	 * @param mot
	 * 	Le mot a ajouter
	 */
	public void add(Station mot){
		mots.add(mot);
	}
	
	/**
	 * Fonction qui permet d'ajouter une liste de mots à la liste des recherches
	 * @param ar
	 * 	La liste des mots à ajouter
	 */
	public void addAll(ArrayList<Station> ar){
		this.mots.addAll(ar);
	}
	
	/**
	 * Fonction qui permet de retourner la liste de chauines correspondant à un debut de mot
	 * @param debut
	 * 	Le debut de mot
	 * @return La liste des chaines correspondantes
	 */
	public List<Station> getChainesCorrespondates(String debut)
	{
		List<Station> res = new ArrayList<Station>();
		for(Station s : mots)
		{
			if(debut!=null && s.toString().length()>=debut.length() && s.toString().toLowerCase().startsWith(debut.toLowerCase()))
			{
				res.add(s);
			}
		}
		return res;
	}
	

}

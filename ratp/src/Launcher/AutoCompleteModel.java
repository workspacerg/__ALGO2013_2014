package Launcher;

import java.util.ArrayList;
import java.util.List;


public class AutoCompleteModel {
	
	/**
	 * La liste dans laquelle on recherche
	 */
	private List<String> mots;
	
	/**
	 * Constructeur du modele
	 */
	public AutoCompleteModel(){
		mots = new ArrayList<String>();
	}
	
	/**
	 * Fonction qui permet d'ajouter un mot a la liste des recherches
	 * @param mot
	 * 	Le mot a ajouter
	 */
	public void add(String mot){
		mots.add(mot);
	}
	
	/**
	 * Fonction qui permet d'ajouter une liste de mots � la liste des recherches
	 * @param mots
	 * 	La liste des mots � ajouter
	 */
	public void addAll(List<String> mots){
		this.mots.addAll(mots);
	}
	
	/**
	 * Fonction qui permet de retourner la liste de chauines correspondant � un debut de mot
	 * @param debut
	 * 	Le debut de mot
	 * @return La liste des chaines correspondantes
	 */
	public List<String> getChainesCorrespondates(String debut){
		List<String> res = new ArrayList<String>();
		for(String s : mots){
			if(debut!=null && s.length()>=debut.length() && s.toLowerCase().contains(debut.toLowerCase())){
				res.add(s);
			}
		}
		return res;
	}
	

}

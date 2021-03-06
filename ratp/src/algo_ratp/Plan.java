package algo_ratp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Plan {

	private Map<Ligne,ArrayList<Station>>  plan = null ;
	private Map<Station,ArrayList<Relation>> relations;
	private Map<Station,Station> avoidRelation;
	
	/**
	 * 
	 * @return Renvoie le plan des Lignes 
	 */
	
	public Map<Ligne, ArrayList<Station>> getPlan() {
		if(plan == null){
			plan = new HashMap<Ligne,ArrayList<Station>>();
			avoidRelation = new HashMap<Station, Station>();
			loadPlan();
			loadCorrespondances();
			loadAdjencies();
		}
		
		return plan;
	}
	
	public void backToSvg(){
		for(Entry<Ligne,ArrayList<Station>> ent : plan.entrySet()){
			for(Station s : ent.getValue())
			{
				s.setMinDistance(Integer.MAX_VALUE);
				s.setPrevious(null);
			}
		}
	}
	/**
	 * Récupère un tableau de relation assscocié aux stations
	 * @return
	 */	
	public Map<Station,ArrayList<Relation>> getRelations(){
		return relations;
	}
	
	/**
	 * Recupère les relations "interdites"
	 *
	 * */
	public Map<Station,Station> getAvoidRelations(){
		return avoidRelation;
	}
	 
	/**
	 * Mise à jour du plan
	 * @param plan
	 */

	public void setPlan(Map<Ligne, ArrayList<Station>> plan) {
		this.plan = plan;
	}

	private static Plan INSTANCE = null;
	
	/**
	 * Constructeur par default 
	 */
	
	private Plan() {
		super();
		relations = new HashMap<Station,ArrayList<Relation>>();
	}
	
	/**
	 * Récuparation de l'instance de la carte
	 * @return
	 */
	
	public static Plan getInstance(){
		if (INSTANCE == null)
		{ 
			INSTANCE = new Plan();
		}
		
		return INSTANCE;
	}
	
	/**
	 * Recupère une liste des stations 
	 * Si null 
	 * 	> Renvoie un tableau vide
	 * 	
	 * @return 
	 */
	
	public ArrayList<Station> GetStations(){
		if(this.plan == null)
			return new ArrayList<Station>();
		
		ArrayList<Station> stations = new ArrayList<Station>();
		
		for(ArrayList<Station> s : this.plan.values()){
			for(Station sta : s)
			{
				if(!stations.contains(sta))
					stations.add(sta);
			}
		}
		
		return stations;
	}
	
	public ArrayList<Ligne> GetLigneByShortName(String name){
		if(this.plan == null)
			return null;
		
		ArrayList<Ligne> lignes = new ArrayList<Ligne>();
		for(Ligne l : this.plan.keySet()){
			// Securité juste
			if(l.getShort_name() == null)
				continue;
			if(l.getShort_name().equalsIgnoreCase(name.split(" ")[0]))
				lignes.add(l);
		}
		
		return lignes;
	}
	
	/**
	 * Le comparateur de Station se fait sur le nom, 
	 * il suffit juste de créer une station avec le nom que l'on cherche ( pas besoin des autres attributs )
	 * 
	 * @param ligne
	 * @param station
	 * @return la ligne pour une station donnée en paramètre
	 * 			> Sinon renvoie null
	 */
	
	public Ligne GetLigneByNameAndStation(String ligne,String station){
		if(this.plan == null)
			return null;
		
		Station st = new Station(station,"");
		for(Ligne l : this.plan.keySet()){
			// Securité juste
			if(l.getShort_name() == null)
				continue;
			if(l.getShort_name().equalsIgnoreCase(ligne.split(" ")[0]) && this.plan.get(l).contains(st))
				return l;
		}
		
		return null;
	}
	
	/**
	 * 
	 */
	
	private void loadAdjencies(){
		for(Entry<Ligne,ArrayList<Station>> ent : this.plan.entrySet()){
			ArrayList<Station> sts = ent.getValue();
			for(int i=0;i<ent.getValue().size();i++){
				loadRelation(sts.get(i));
			}
		}
		
	}
	
	/**
	 * Charge les relations
	 * @param 
	 */
	
	private void loadRelation(Station sta){
		Map<Station,LigneCorrespondance> map = Correspondance.getInstance().getMapLigne();

		int index;
		for(Ligne l :map.get(sta).GetLignes())
		{
			index = this.plan.get(l).indexOf(sta);
			Station st = this.plan.get(l).get(index);

			if(index>0){
				Station s1 = this.plan.get(l).get(index-1);
				int first = (int)((st.getArrival().getTime() - s1.getDeparture().getTime())/60000);
				checkExist(sta,new Relation(s1,first,l));
			}
			
			if(index<this.plan.get(l).size()-1){
				Station s2 = this.plan.get(l).get(index+1);
				int second = (int)((s2.getArrival().getTime() - st.getDeparture().getTime())/60000);
				checkExist(sta,new Relation(s2,second,l));

			}
						
		}
	}
	
	/**
	 * Vérifie qu'une relation existe avec la station 
	 * @param st
	 * @param r
	 */
	
	private void checkExist(Station st,Relation r){
		if(this.avoidRelation.get(st) != null){
			if(r.getTarget().equals(this.avoidRelation.get(st))){
				return;
			}
		}
				
		if(this.relations.containsKey(st))
		{
			if(!this.relations.get(st).contains(r))
				this.relations.get(st).add(r);
		}
		else
		{
			ArrayList<Relation> rs = new ArrayList<Relation>();
			rs.add(r);
			this.relations.put(st,rs);
		}
	}
	
	/**
	 * Recupère la distance entre deux stations sur une ligne
	 * Calculer la distance dans n'importe quel sens
	 * 
	 * @param ligne
	 * @param start
	 * @param end
	 * @return distance
	 */
	
	public int getDistanceBetween(Ligne ligne,Station start,Station end){
		if(this.plan == null || this.relations == null || start.equals(end))
			return 0;
		
		int distance = 0;
		ArrayList<Station> stations = this.plan.get(ligne);
		int index = stations.indexOf(start);
		int index2 = stations.indexOf(end);
		
		if(index == -1 || index2 == -1)
			return 0;

		Station st = stations.get(index);
		for(Relation r :this.relations.get(st)){
			if(r.getLigne().equals(ligne) && Math.abs((index2 - stations.indexOf(r.getTarget()))) < Math.abs((index2 - index))){
				distance += r.getWeight();
				distance += getDistanceBetween(ligne,r.getTarget(),end);
			}
		}
		
		return Math.abs(distance);
		
	}
	
	/**
	 * 
	 * Charger liste des correspondances
	 * 
	 */
				
	private void loadCorrespondances(){
		Map<Station,LigneCorrespondance> mapCorresp = new HashMap<Station,LigneCorrespondance>();
		ArrayList<Ligne> lis;
		for(Entry<Ligne,ArrayList<Station>> ent : this.plan.entrySet()){
			for(Station s : ent.getValue()){
				if(mapCorresp.containsKey(s)){
					if(!mapCorresp.get(s).GetLignes().contains(ent.getKey()))
						mapCorresp.get(s).GetLignes().add(ent.getKey());
				}
				else{
					lis = new ArrayList<Ligne>();
					lis.add(ent.getKey());
					LigneCorrespondance lc = new LigneCorrespondance(lis);
					mapCorresp.put(s, lc);
				}
			}
		}
		
		Correspondance.getInstance().getMapLigne().putAll(mapCorresp);
	}
	
	/**
	 * Charge le plan 
	 * @see DALProvider
	 */
	
	private void loadPlan(){
		DALProvider.getInstance().connect();
		plan.putAll(DALProvider.getInstance().GetPlan());
		avoidRelation.putAll(DALProvider.getInstance().GetInterdicitons());
		DALProvider.getInstance().close();
	}
		
}

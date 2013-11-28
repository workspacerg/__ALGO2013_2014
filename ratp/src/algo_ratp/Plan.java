package algo_ratp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Plan {

	private Map<Ligne,ArrayList<Station>>  plan = null ;
	
	public Map<Ligne, ArrayList<Station>> getPlan() {
		if(plan == null){
			plan = new HashMap<Ligne,ArrayList<Station>>();
			loadPlan();
			loadCorrespondances();
		}
		
		return plan;
	}

	public void setPlan(Map<Ligne, ArrayList<Station>> plan) {
		this.plan = plan;
	}

	private static Plan INSTANCE = null;
	
	private Plan() {
		super();

	}
	
	public static Plan getInstance(){
		if (INSTANCE == null)
		{ 
			INSTANCE = new Plan();
		}
		
		return INSTANCE;
	}
		
	public Ligne GetLigneByShortName(String name){
		if(this.plan == null)
			return null;
		
		for(Ligne l : this.plan.keySet()){
			// Securité juste
			if(l.getShort_name() == null)
				continue;
			if(l.getShort_name().equalsIgnoreCase(name.split(" ")[0]))
				return l;
		}
		
		return null;
	}
			
	public void findItinerary(String _arg1, String _arg2) {
		
	}
	
	private void loadCorrespondances(){
		Map<Station,ArrayList<Ligne>> mapCorresp = new HashMap<Station, ArrayList<Ligne>>();
		ArrayList<Ligne> lis;
		for(Entry<Ligne,ArrayList<Station>> ent : this.plan.entrySet()){
			for(Station s : ent.getValue()){
				if(mapCorresp.containsKey(s)){
					if(!mapCorresp.get(s).contains(ent.getKey()))
						mapCorresp.get(s).add(ent.getKey());
				}
				else{
					lis = new ArrayList<Ligne>();
					lis.add(ent.getKey());
					mapCorresp.put(s, lis);
				}
			}
		}
		
		Correspondance.getInstance().getMapLigne().putAll(mapCorresp);
	}
	
	private void loadPlan(){
		DALProvider.getInstance().connect();
		ArrayList<Ligne> lignes = DALProvider.getInstance().GetLignes();
		plan.putAll(DALProvider.getInstance().GetPlan(lignes));
		DALProvider.getInstance().close();
	}
		
}

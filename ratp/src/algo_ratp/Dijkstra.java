package algo_ratp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Dijkstra {
	
    private static Set<Relation> settledNodes;
    private static Set<Relation> unSettledNodes;
        
	public static void execute(Station source) {
	    settledNodes = new HashSet<Relation>();
	    unSettledNodes = new HashSet<Relation>();
	    source.setMinDistance(0);
	    // Initialisation du départ
	    Relation node = new Relation(source,0,null);
	    unSettledNodes.add(node);
	    
	    while (unSettledNodes.size() > 0) {
	      node = getMinimum(unSettledNodes);
	      settledNodes.add(node);
	      unSettledNodes.remove(node);
	      findMinimalDistances(node);
	    }
	  }
	
	private static void findMinimalDistances(Relation node) {
	    ArrayList<Relation> adjacentNodes = getNeighbors(node);
	    for (Relation target : adjacentNodes) {
	    	
	      if (target.getTarget().getMinDistance() > node.getTarget().getMinDistance()
	          + getDistance(node, target)) 
		      {
		        target.getTarget().setMinDistance(node.getTarget().getMinDistance() + getDistance(node, target));
		        target.getTarget().setPrevious(node.getTarget());
		        unSettledNodes.add(target);
		      }
	    }

	  }
	
	private static ArrayList<Relation> getNeighbors(Relation node) {
	    ArrayList<Relation> neighbors = new ArrayList<Relation>();
	    
	    for (Relation r : Plan.getInstance().getRelations().get(node.getTarget())) {
	      if (!isSettled(r))
	    	  neighbors.add(r);	      
	      }
	    
	    return neighbors;
	 }
	
	public static LinkedList<Relation> getRealPath(LinkedList<Relation> relations){
		try{
			if(relations == null)
				return new LinkedList<Relation>();
						
			LinkedList<Relation> rel = new LinkedList<Relation>();
			Ligne current = new Ligne();
			int i = 0;
			
			for(Relation r : relations){
				if(i==0){
					rel.add(r);
					current = r.getLigne();
					i++;
					continue;
				}
				if(!r.getLigne().getShort_name().equalsIgnoreCase(current.getShort_name())){
					rel.add(r);
					current = r.getLigne();
					if(r.getTarget().equals(relations.getLast().getTarget()))
							break;
				}
				else if(r.getTarget().equals(relations.getLast().getTarget())){
					rel.add(r);
					break;
				}
			}
			
			return rel;
		}
		catch(Exception exp){
			exp.printStackTrace();
			return null;
		}
	}
	
    public static LinkedList<Relation> getPath(Station target) {
	    LinkedList<Relation> path = new LinkedList<Relation>();
	    Station step = target;
	    if (step.getPrevious() == null) 
	      return null;
	    
	    path.add(new Relation(step,0,GetRelationFromSource(step, step.getPrevious()).getLigne()));
	    
	    Relation r = new Relation(step,0,null);
	    while (step.getPrevious() != null) {
	      r = GetRelationFromSource(step, step.getPrevious());
	      path.add(r);
	      step = step.getPrevious();
	    }
	    Collections.reverse(path);
	    return path;
	  }
	
    private static Relation GetRelationFromSource(Station source,Station target){
    	if(Plan.getInstance().getAvoidRelations().containsKey(target)){
    		for(Relation r : Plan.getInstance().getRelations().get(target)){
        		if(r.getTarget().equals(source))
        			return r;
        	}
    	}
    	
    	for(Relation r : Plan.getInstance().getRelations().get(source)){
    		if(r.getTarget().equals(target))
    			return r;
    	}
    	// On est pas censé arrivé jusqu'ici
    	return null;
    }
    
	private static int getDistance(Relation node, Relation target){
		for(Relation r : Plan.getInstance().getRelations().get(node.getTarget())){
			if(r.getTarget().equals(target.getTarget())){
				if(node.getLigne() != null){
				 	if(!node.getLigne().getShort_name().equalsIgnoreCase(r.getLigne().getShort_name())){
				 		// + temps de marche 
				 		return r.getLigne().getTypeTransport().equals(Type.Metro) ? r.getWeight() + 4 : r.getWeight() + 7;
				 	}
				}
					return r.getWeight();
			}
		}
		
		return 0;
	}
	private static Relation getMinimum(Set<Relation> relations) {
		Relation minimum = null;
	    for (Relation s : relations) {
	      if (minimum == null) 
	        minimum = s;
	      else if (s.getTarget().getMinDistance() < minimum.getTarget().getMinDistance())        
	          minimum = s;
	      
	    }
	    return minimum;
	  }
	
	private static boolean isSettled(Relation r) {
	    return settledNodes.contains(r);
	 }

}

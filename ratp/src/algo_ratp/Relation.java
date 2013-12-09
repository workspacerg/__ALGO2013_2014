package algo_ratp;

public class Relation {
	private Station target;
    private int weight;
    private Ligne ligne;
    
    public Relation(Station argTarget, int argWeight,Ligne _li)
    { target = argTarget; weight = argWeight; ligne = _li; }
	public Station getTarget() {
		return target;
	}
	public int getWeight() {
		return weight;
	}
	
	public void setLigne(Ligne _l){
		ligne = _l;
	}
	
	public void setWeight(int _wei){
		weight = _wei;
	}
	public Ligne getLigne() {
		return ligne;
	}
	@Override
    public int hashCode() {
		if(ligne != null)
			return this.ligne.hashCode() + this.target.hashCode();
		else
			return this.target.hashCode() + 31;
    }
	
	@Override
	public boolean equals(Object o) {
	    if (o == null || o.getClass() != getClass()) { 
	        return false;
	    }
	    
	    return ((Relation)o).getLigne().getShort_name().equals(this.ligne.getShort_name()) && ((Relation)o).getTarget().equals(this.target);
	}

}

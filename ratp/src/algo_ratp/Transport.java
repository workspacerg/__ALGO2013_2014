package algo_ratp;

public class Transport {

	private Type type;
	private String name;
	
	public Transport(Type _type,String _name){
		setType(_type);
		setName(_name);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

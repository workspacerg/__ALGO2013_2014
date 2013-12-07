package algo_ratp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DALProvider {
	
private DALProvider()
{
	
}
 
private static DALProvider INSTANCE = null;
private final static String DBPath = "localhost/dataratp";
private long ONE_MINUTE_IN_MILLIS = 60000;
private String username;
private String password;
private static Connection connection;
 
/** Point d'accès pour l'instance unique du singleton */
public static DALProvider getInstance()
{	
	if (INSTANCE == null)
	{ 
		INSTANCE = new DALProvider();
	}
	return INSTANCE;
}

public void initIdentifiers(String userN,String password){
	this.username = userN;
	this.password = password;
}

public boolean connect() { 
    try {
    	if(this.username == null || this.password == null)
    	{
    		System.out.println("Veuillez entrer vos identifiants de connexion à la BDD.");
    		throw new InstantiationException();
    	}
    	
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection connect = DriverManager.getConnection("jdbc:mysql://" + this.DBPath, this.username, this.password);
        // On test la création d'une requete
        connect.createStatement();
        connection = connect;
        return true;
    } catch (ClassNotFoundException notFoundException) {
        notFoundException.printStackTrace();
        System.out.println("Erreur de connexion");
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        System.out.println("Erreur de connexion");
    } catch (InstantiationException e) {
    	System.out.println("Erreur de connexion");
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		System.out.println("Erreur de connexion");
		e.printStackTrace();
	}
    
    return false;
}

public void close(){
	 try {
         connection.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
}

// Selectionne les trip_id utilisé pour la création du plan
private String GetTripIdToPlan(){
	try{
		StringBuilder sb = new StringBuilder();
		String requete = "SELECT TRIP_id from stop_time group by trip_id having count(*) > 1";
			
			
		ResultSet result = Execquery(requete);
		while(result.next())
			sb.append("'"+result.getString(1)+"',");
		
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

public Map<Station,Station> GetInterdicitons(){
	try{
		Map<Station,Station> inter = new HashMap<Station, Station>();
		String requete = "Select interdiction_stop_name,interdiction_stop_name_relation FROM INTERDICTION";
		ResultSet result = Execquery(requete);
		
		while(result.next())
			inter.put(new Station(result.getString(1),new String()), new Station(result.getString(2),new String()));
		
		return inter;
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

private Relation GetNextChange(LinkedList<Relation> relations, Relation depart){
	
	Ligne l = depart.getLigne();
	int index = relations.indexOf(depart);
	
	int i;
	for(i=index;i<relations.size();i++){
		if(!relations.get(i).getLigne().equals(l))
			return relations.get(i);
	}
	
	// Si on a fait le tour sans rien trouver c'est qu'on est à la fin 
	return relations.get(i-1);
}

// Retourne le prochain voyage pour cette station à l'heure voulue
public Date GetNextTime(Ligne l,Station s,Date departTime){
	try{
		Station depart = Plan.getInstance().getPlan().get(l).get(0);
		int distance = Plan.getInstance().getDistanceBetween(l, depart, s);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(departTime);
		
		// Le depart depuis ma station, le plus proche de l'heure entrée
		String requete = "SELECT ADDTIME(departure_time,SEC_TO_TIME("+distance+"*60)) AS date FROM stop_time st,trips t " +
				"WHERE t.trip_id = st.trip_id AND route_id = '"+l.getId_route()+"' " +
				"AND departure_time > ADDTIME(STR_TO_DATE('"+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"','%H:%i'),-SEC_TO_TIME("+distance+"*60)) " +
				"ORDER BY DEPARTURE_TIME LIMIT 1";
		ResultSet result = Execquery(requete);
		// Limit 1 donc un seul résultat, on se place dessus avec result.next()
		result.next();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.parse(result.getString(1));
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}
public LinkedHashMap<Relation,Date> GetRealPathWithTime(LinkedList<Relation> relations,Date depart){
	try{
		LinkedHashMap<Relation,Date> map = new LinkedHashMap<Relation, Date>();
		
		Date temp = depart;
		
		if(relations.getFirst().getLigne().getTypeTransport().equals(Type.Metro))
			map.put(relations.getFirst(), temp);
		else
			map.put(relations.getFirst(), GetNextTime(relations.getFirst().getLigne(),relations.getFirst().getTarget(),depart));
		
		Relation r = relations.getFirst();
		Relation r2 = relations.getFirst();
		int distance;
			
		while(!(r = GetNextChange(relations, r)).equals(relations.getLast())){
			distance = Plan.getInstance().getDistanceBetween(r2.getLigne(), r.getTarget(), r2.getTarget());
			System.out.println(temp);
			if(r.getLigne().getTypeTransport().equals(Type.Metro)){
				map.put(r, (temp = new Date(temp.getTime() + (distance+4) * ONE_MINUTE_IN_MILLIS)));
			}
			else{
				map.put(r, GetNextTime(r.getLigne(),r.getTarget(),(temp = new Date(temp.getTime() + (distance+7) * ONE_MINUTE_IN_MILLIS))));
			}
			
			r2 = r;
		}
			
		map.put(r,GetNextTime(r.getLigne(),r.getTarget(),depart));
		
		for(Entry<Relation,Date> ent: map.entrySet())
			System.out.println(ent.getKey().getTarget().getName() + " -->" + ent.getKey().getLigne().getShort_name()+"--->" + ent.getValue());
		
		return map;
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

// Charge le plan
public Map<Ligne,ArrayList<Station>> GetPlan(){
	try{
			
			Map<String,ArrayList<Station>> map = new HashMap<String, ArrayList<Station>>();		
			String requete = String.format("SELECT s.stop_id,s.stop_name,stop_sequence,departure_time,arrival_time,trip_id FROM STOP_TIME st,STOPS s WHERE st.stop_id = s.stop_id AND trip_id IN (%s)",GetTripIdToPlan());
			
			
			ResultSet result = Execquery(requete);
			Station st;
			String id;
			String name;
			Date departure;
			Date arrival;
			int position;
			String trip_id;
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			
			while(result.next()){
				id = result.getString(1);
				name = result.getString(2);
				position = Integer.parseInt(result.getString(3));
				departure = df.parse(result.getString(4));
				arrival = df.parse(result.getString(5));
				trip_id = result.getString(6);
				st = new Station(name,id,position,departure,arrival);
				
				if(map.containsKey(trip_id))
					map.get(trip_id).add(st);
				else
				{
					ArrayList<Station> arrst = new ArrayList<Station>();
					arrst.add(st);
					map.put(trip_id, arrst);
				}
			}
			
		return GetPlanToTripMap(map);
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

// Change la map passé en paramètre en une map reliant Ligne à des Stations
private Map<Ligne,ArrayList<Station>> GetPlanToTripMap(Map<String,ArrayList<Station>> map){
	if(map == null)
		return null;
	
	Map<Ligne,ArrayList<Station>> mapSt = new HashMap<Ligne,ArrayList<Station>>();
	Map<String,Ligne> mapLi = GetRoutesByTripsId(map.keySet());
	ComparatorStation comparator = new ComparatorStation();

	for(Entry<String,ArrayList<Station>> ent : map.entrySet()){
		ArrayList<Station> arrSt = ent.getValue();
		Collections.sort(arrSt,comparator);
		mapSt.put(mapLi.get(ent.getKey()),arrSt);
	}
	
	return mapSt;
}

private Map<String,Ligne> GetRoutesByTripsId(Set<String> trip_id){
	try{
		StringBuilder sb = new StringBuilder();
		for(String s : trip_id)
			sb.append("'"+s+"',");
		
		// Supprime la derniere virgule
		sb.deleteCharAt(sb.length()-1);
		
		Map<String,Ligne> mapLi = new HashMap<String,Ligne>();
		
		String requete = "SELECT r.route_id,route_short_name,route_long_name,route_type,route_color,t.trip_id from trips t,route r " +
				" WHERE r.route_id = t.route_id AND t.trip_id IN ("+sb.toString()+")";
						
		ResultSet result = Execquery(requete);
		String id;
		String sname;
		String lname;
		Type type;
		String color;
		while(result.next()){
		     id = result.getString(1);
			 sname = result.getString(2);
			 lname = result.getString(3);
			 type = Type.values()[result.getInt(4)];
			 color = result.getString(5);
			 if(sname != null && id != null)
			   mapLi.put(result.getString(6),new Ligne(type,sname,lname,color,id));
		}
		
		return mapLi;
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

private static ResultSet Execquery(String request) {
    ResultSet resultat = null;
    try {
    	Statement statement = connection.createStatement();
        resultat = statement.executeQuery(request);
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erreur dans la requete : " + request);
    }
    return resultat;

}

}

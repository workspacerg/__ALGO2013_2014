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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DALProvider {
	
/**
 * Constructeur par default	
 */
	
private DALProvider()
{
	
}
 
private static DALProvider INSTANCE = null;
private final static long ONE_MINUTE_IN_MILLIS = 60000;

private String DBPath;
private String username;
private String password;
private static Connection connection;
private boolean isAuthentified = false;
 


/** 
 * Point d'accés pour l'instance unique du singleton 
 */

public static DALProvider getInstance()
{	
	if (INSTANCE == null)
	{ 
		INSTANCE = new DALProvider();
	}
	return INSTANCE;
}

/**
 * 
 * @return Chemin d'accès de la bdd 
 */

public String getDbPath(){
	return DBPath;
}

/**
 * Savoir si l'utilisateur est authentifié.
 * @return
 */

public boolean isAuth(){
	return this.isAuthentified;
}

/**
 * Connection à la base avec les identifiants
 * @param userN
 * @param password
 * @param dbPath
 * @return
 */

public boolean initIdentifiers(String userN,String password,String dbPath){
	return (isAuthentified = tryConnect(userN,password,dbPath));
}

/**
 * Appel de la méthode connection + gestion de l'erreur de connection 
 * > appeler en priorité initIdentifiers()
 * @see initIdentifiers
 * 
 * @param user
 * @param passwd
 * @param db
 * 
 * @return
 */

public boolean tryConnect(String user,String passwd,String db){
	this.username = user;
	this.password = passwd;
	this.DBPath = db;
	if(!connect()){
		this.username = this.password = this.DBPath = null;
		return false;
	}
	return true;
	
}

/**
 * Connection effective
 * @return
 */

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
/**
 * Fermeture de la connection à la BDD.
 */
public void close(){
	 try {
         connection.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
}

/**
 * Selectionne les trip_id utilisé pour la création du plan
 * @return
 */

private String GetTripIdToPlan(){
	try{
		StringBuilder sb = new StringBuilder();
		String requete = "SELECT trip_id from STOP_TIME group by trip_id having count(*) > 1";
			
			
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

/**
 * Gestion des cas particulier
 * exemple boucle unidirectionnel
 * @return
 */

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

/**
 * Regerder le prochain changement
 * 
 * Si l'ensemble est parcouru => on est à la fin de la ligne
 * 
 * @param relations
 * @param depart
 * @return
 */

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

private Relation getPreviousChange(LinkedList<Relation> relations, Relation arrivee){
	
	int index = relations.indexOf(arrivee);
	Ligne l = relations.get(index-1).getLigne();
	
	int i;
	Relation prev = arrivee;
	for(i=index;i>0;i--){
		if(!relations.get(i).getLigne().equals(l))
			return prev;
		prev = relations.get(i);
	}
	
	// Si on a fait le tour sans rien trouver c'est qu'on est au début 
	return relations.get(0);
}

/**
 * Retourne le prochain voyage pour cette station à l'heure voulue
 * 
 * @param l
 * @param s
 * @param departTime
 * @return
 */

public Date GetNextTime(Ligne l,Station s,Date departTime){
	try{
		Station depart = Plan.getInstance().getPlan().get(l).get(0);
		int distance = Plan.getInstance().getDistanceBetween(l, depart, s);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(departTime);
		
		// Le depart depuis ma station, le plus proche de l'heure entrée
		String requete = "SELECT ADDTIME(departure_time,SEC_TO_TIME("+distance+"*60)) AS date FROM STOP_TIME st,TRIPS t " +
				"WHERE t.trip_id = st.trip_id AND route_id = '"+l.getId_route()+"' " +
				"AND departure_time > ADDTIME(STR_TO_DATE('"+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"','%H:%i'),-SEC_TO_TIME("+distance+"*60)) " +
				"ORDER BY departure_time LIMIT 1";
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

private Date getPreviousTime(Ligne l,Station s,Date arrivalTime){
	try{
		Station depart = Plan.getInstance().getPlan().get(l).get(0);
		int distance = Plan.getInstance().getDistanceBetween(l, depart, s);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(arrivalTime);
		
		String requete = "SELECT ADDTIME(departure_time,SEC_TO_TIME("+distance+"*60)) FROM STOP_TIME st,TRIPS t " +
				"WHERE t.trip_id = st.trip_id AND route_id = '"+l.getId_route()+"' " +
				"AND departure_time < ADDTIME(STR_TO_DATE('"+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+"','%H:%i'),-SEC_TO_TIME("+distance+"*60)) " +
				"ORDER BY departure_time LIMIT 1";
		
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



public LinkedHashMap<Relation,Date> GetRealPathWithArrival(LinkedList<Relation> relations,Date arrivee){
	try{
		if(relations == null)
			return new LinkedHashMap<Relation,Date>();
			
		LinkedHashMap<Relation, Date> map = new LinkedHashMap<Relation, Date>();
		relations = Dijkstra.getRealPath(relations);
		
		Date temp = null;	
		
		int distance;
		Relation r;
		for(int i = relations.size() - 1;i>0;i--){
			r = relations.get(i);
			distance = Plan.getInstance().getDistanceBetween(relations.get(i-1).getLigne(), r.getTarget(), relations.get(i-1).getTarget());
			
			if(temp == null)
				temp = arrivee;
			else
				distance += r.getLigne().getTypeTransport().equals(Type.Metro) ? 4 : 7;
			
			if(r.getLigne().getTypeTransport().equals(Type.Metro))
				map.put(r, (temp = new Date(temp.getTime() - distance * ONE_MINUTE_IN_MILLIS)));
			else
				map.put(r, getPreviousTime(r.getLigne(),r.getTarget(),(temp = new Date(temp.getTime() - distance * ONE_MINUTE_IN_MILLIS))));
						
		}
			
		map.put(relations.get(0), new Date(temp.getTime() - ((Plan.getInstance().getDistanceBetween(relations.get(0).getLigne(), relations.get(0).getTarget(), relations.get(1).getTarget()))*  ONE_MINUTE_IN_MILLIS)));
		
		ArrayList<Entry<Relation,Date>> changeOrder = new ArrayList<Map.Entry<Relation,Date>>();
		changeOrder.addAll(map.entrySet());
		Collections.reverse(changeOrder);
		
		map.clear();
				
		for(Entry<Relation,Date> ent : changeOrder)
			map.put(ent.getKey(), ent.getValue());
		
		return map;
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
	
}

/**
 * Récupère le chemin avec la durée
 * @param relations
 * @param depart
 * @return
 */

public LinkedHashMap<Relation,Date> GetRealPathWithTime(LinkedList<Relation> relations,Date depart){
	try{
		LinkedHashMap<Relation,Date> map = new LinkedHashMap<Relation, Date>();
		
		Date temp = null;
		
		if(relations.getFirst().getLigne().getTypeTransport().equals(Type.Metro))
			map.put(relations.getFirst(), depart);
		else
			map.put(relations.getFirst(), GetNextTime(relations.getFirst().getLigne(),relations.getFirst().getTarget(),depart));
		
		Relation current = relations.getFirst();
		Relation last = relations.getFirst();
		int distance;
			
		while(!(current = GetNextChange(relations, current)).equals(relations.getLast())){
			distance = Plan.getInstance().getDistanceBetween(last.getLigne(), current.getTarget(), last.getTarget());
			if(temp == null)
				temp = depart;
			else
				distance += current.getLigne().getTypeTransport().equals(Type.Metro) ? 4 : 7;
			
			if(current.getLigne().getTypeTransport().equals(Type.Metro))
				map.put(current, (temp = new Date(temp.getTime() + (distance * ONE_MINUTE_IN_MILLIS))));
			else
				map.put(current, GetNextTime(current.getLigne(),current.getTarget(),(temp = new Date(temp.getTime() + (distance * ONE_MINUTE_IN_MILLIS)))));
						
			last = current;
		}
			
		int walk = current.getLigne().getTypeTransport().equals(Type.Metro) ? 4 : 7;
		map.put(current, new Date(temp.getTime() + ((Plan.getInstance().getDistanceBetween(last.getLigne(), current.getTarget(), last.getTarget())+walk)*  ONE_MINUTE_IN_MILLIS)));
				
		return map;
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

/**
 * Charge le plan
 * @return
 */

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

/**
 * Change la map passé en paramétre en une map reliant Ligne à des Stations
 * @param map
 * @return
 */

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

/**
 * Récupère route par son identifiant de voyage
 * @param trip_id
 * @return
 */

private Map<String,Ligne> GetRoutesByTripsId(Set<String> trip_id){
	try{
		StringBuilder sb = new StringBuilder();
		for(String s : trip_id)
			sb.append("'"+s+"',");
		
		// Supprime la derniere virgule
		sb.deleteCharAt(sb.length()-1);
		
		Map<String,Ligne> mapLi = new HashMap<String,Ligne>();
		
		String requete = "SELECT r.route_id,route_short_name,route_long_name,route_type,route_color,t.trip_id from TRIPS t,ROUTE r " +
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

/**
 * Exécution des requet sql
 * @param request
 * @return
 */

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
